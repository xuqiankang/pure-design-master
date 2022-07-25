<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="我的预约" left-arrow @click-left="goBack" />
    <div class="page-container">
      <div class="headerFiex">
        <van-tabs v-model="active" @change="changetabs">
          <van-tab title="已提交" name="1"></van-tab>
          <van-tab title="待提交" name="0"></van-tab>
        </van-tabs>
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
            <div v-for="(item, index) in list" :key="index" class="listCard-fill">
              <div class="listCard-fill-content" @click="openQrcode(item)">
                <div class="listCard-fill-box">
                  <van-row type="flex" align="center" style="height: 100%;">
                    <van-col :span="16">
                      <div class="listCard-fill-title">租户名称/{{item.name}}</div>
                      <div style="padding-top:5px"> {{item.address}}</div>
                      <div> 到访日期:{{item.visitorDate}}</div>
                    </van-col>
                    <van-col :span="8">
                      <van-button v-if="item.state == '001' || item.state == '002'" size="mini" type="info" round color="#FFA94F">取消</van-button>
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
import { getOrderInfoPer } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'myReservation',
  extends: BaseUI,
  components: {
  },
  data() {
    return {
      myqrcodePopShow: false,
      active: '1',
      value: '',
      loadings: false,
      finished: false,
      refreshing: false,
      list: [],
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
    // this.search.params = [{'columnName':'vst_personnal_id', 'queryType': '=', 'value': this.$route.query.vstPersonnalId}]
  },
  methods: {
    myqrcodePopClose() {
      this.myqrcodePopShow = false
    },
    openQrcode(item) {
      if (item.state == '002') {
        this.myqrcodePopShow = true
        this.$refs.myqrcodePop.initQrcode(item)
      }
    },
    // 切换时
    changetabs() {
      this.list = []
      this.onSearch()
    },
    onRefresh() {
      this.finished = false;
      this.loadings = true;
      this.onSearch();
    },
    // 查询
    onSearch() {
      this.list = []
      this.search.offset = 0
      this.currentPage = 1
      this.onLoad()
    },
    onLoad() {
      this.getlist();
      // this.currentPage += 1;// 页数+1
      // this.search.offset = this.currentPage * this.search.limit
    },
    onRefresh() {
      this.finished = false;
      this.loadings = true;
      this.onSearch();
    },
    getlist() {
      getOrderInfoPer(this.currentUser.username).then(res => {
        if(res.data.data instanceof Array) {
          this.$nextTick(() => {
            if (this.refreshing) {
              this.list = [];
              this.refreshing = false;
            }
            this.list = [...this.list, ...res.data.data]
            // console.log( this.list);
            // this
            // ifDraft
            this.loadings = false;
            if (res.length < 20) {  //数据全部加载完成
              this.finished = true;
            } else {
              this.finished= false
            }
          })
        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      }).catch(error => {
        this.resetLoad()
        this.outputError(error)
      })
    },

  }
}
</script>

<style lang="scss" scoped>
/deep/ .van-search {
  padding: 0.12rem 0.32rem;
}
</style>
