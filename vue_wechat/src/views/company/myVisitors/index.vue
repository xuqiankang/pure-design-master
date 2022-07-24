<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <div class="page-container">
      <div class="headerFiex">
        <van-tabs v-model="reservationActive" @change="changetabs">
          <van-tab title="待确认" name="1"></van-tab>
          <van-tab title="已确认" name="2"></van-tab>
          <van-tab title="已结束" name="3"></van-tab>
        </van-tabs>
        <van-search v-model.trim="value" placeholder="请输入搜索关键词" shape="round" @input="onSearch()" />
      </div>

       <div class="scroll">
        <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
          <van-list
            :immediate-check="false"
            v-model="loadings"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
          >
            <div v-for="(reservationItem,index) in reservationList" :key="index" class="listCard-fill">
              <div class="listCard-fill-content" @click="openQrcode(reservationItem)">


                <div class="listCard-fill-box">
                  <van-row type="flex" align="center" style="height: 100%;">
                    <van-col :span="16">
                      <div class="listCard-fill-title">租户名称/{{reservationItem.name}}</div>
                      <div style="padding-top:5px"> {{reservationItem.address}}</div>
                      <div> 到访日期:{{reservationItem.visitorDate}}</div>
                    </van-col>
                    <van-col :span="8">
                      <van-button v-if="reservationItem.state == '001' || reservationItem.state == '002'" size="mini" type="info" round color="#FFA94F">取消</van-button>
                      <van-button v-else size="mini" type="info" round color="#FFA94F"> 再次预约 </van-button>
                    </van-col>
                  </van-row>
                </div>
              </div>
            </div>
          </van-list>
        </van-pull-refresh>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'myReservation',
  components: {
  },
  data() {
    return {
      reservationActive: '1',
      value: '',
      loadings: false,
      finished: false,
      refreshing: false,
      reservationList: [],
      search: {
        params: [],    
        offset: 0,
        limit: 10,
        orderby: ''
      },
      bakList: [
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '001'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '002'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '003'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '001'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '005'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '001'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '004'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '001'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '001'},
        { name: '管理方公司', address: '园区-楼宇-楼层-房间', visitorDate: '2022-04-30', state: '006'},
      ]
    }
  },
  created() {
    this.onSearch()
  },
  methods: {
    // 切换时
    changetabs() {
      this.reservationList = []
      this.onSearch()
    },
    onRefresh() {
      this.finished = false;
      this.loadings = true;
      this.onSearch();
    },
    // 查询
    onSearch() {
      this.reservationList = []
      this.search.offset = 0
      this.currentPage = 1
      this.onLoad()
    },
    onLoad() {
      this.getreservationList();
      // this.currentPage += 1;// 页数+1
      // this.search.offset = this.currentPage * this.search.limit
    },
    onRefresh() {
      this.finished = false;
      this.loadings = true;
      this.onSearch();
    },
    getreservationList() {
      this.$nextTick(() => {

        if (this.refreshing) {
          this.reservationList = [];
          this.refreshing = false;
        }
        // 测试页面效果
        let bakList = []
        bakList = this.bakList.filter(item => {
          if (this.reservationActive == 1) {
            return item.state == '001'
          } else if (this.reservationActive == 2) {
            return item.state == '002'
          } else {
            return item.state != '002' && item.state != '001'
          }
        })

        this.reservationList = [...bakList]
        this.loadings = false;
        if (this.bakList.length < 20) {  //数据加载完成
          this.finished = true;
        } else {
          this.finished= false
        }
      })
    }

  }
}
</script>

<style lang="scss" scoped>
/deep/ .van-search {
  padding: 0.12rem 0.32rem;
}
</style>
