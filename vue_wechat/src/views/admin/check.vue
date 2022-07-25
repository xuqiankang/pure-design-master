<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="信息审核" left-arrow @click-left="goBack" />
    <div class="page-container">
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
                    <van-col :span="14">
                      <div class="listCard-fill-title">租户名称/{{item.name}}</div>
                      <div style="padding-top:5px"> {{item.address}}</div>
                      <div> 到访日期:{{item.visitorDate}}</div>
                    </van-col>
                    <van-col :span="10">
                      <van-button size="mini" type="info" round color="#FFA94F">取消</van-button>
                      <van-button size="mini" type="info" round color="#FFA94F">同意</van-button>
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
import { getOrderInfoPer, getApply } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'check',
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
    }
  },
  created() {
    this.onSearch()
  },
  methods: {
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
    },
    onRefresh() {
      this.finished = false;
      this.loadings = true;
      this.onSearch();
    },
    getlist() {
      let parms = {
        username: this.currentUser.username
      }
      getApply(parms).then(res => {
        if(res.data instanceof Array) {
          this.$nextTick(() => {
            if (this.refreshing) {
              this.list = [];
              this.refreshing = false;
            }
            this.list = [...this.list, ...res.data]
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
</style>
