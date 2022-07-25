<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="我的来访" left-arrow @click-left="goBack" />
    <div class="page-container">
      <div class="headerFiex">
        <van-tabs v-model="active" @change="changetabs">
          <van-tab title="待处理" name="0"></van-tab>
          <van-tab title="已处理" name="1"></van-tab>
        </van-tabs>
      </div>

       <div class="scroll">
        <div v-for="(item, index) in list" :key="index" class="listCard-fill">
          <div class="listCard-fill-content">
            <div class="listCard-fill-box">
              <van-row type="flex" align="center" style="height: 100%;">
                <van-col :span="16">
                  <div class="listCard-fill-title">
                    <van-tag v-if="active == 0" type="danger" size="medium" style="margin: 2px 4px">草稿</van-tag>
                    预约公司:{{item.visitCompany}}
                  </div>
                  <div class="listCard-fill-title">负责人:{{item.visitAdmin}}</div>
                  <!-- <div style="padding-top:5px"> {{item.address}}</div> -->
                  <!-- <div> 到访日期:{{getTime(item)}}</div> -->
                </van-col>
                <van-col :span="8">
                  <van-button size="mini" type="info" round color="#FFA94F"> 查看 </van-button>
                  <van-button size="mini" v-if="active == 0" type="info" round color="#FFA94F" @click="del(item)">删除</van-button>
                </van-col>
              </van-row>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getOrderInfo, delOrderInfoPer } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
import {validatenull} from '@/utils/validate'

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
    this.getlist();
  },
  methods: {
    // 切换时
    changetabs() {
      this.list = []
      this.getlist();
    },
    getTime(val) { 
      // if (validatenull(val)) return 
      return val.time.slice(0,10)
    },
    getlist() {
      getOrderInfo(this.currentUser.username).then(res => {
        if(res.data.data instanceof Array) {
          this.$nextTick(() => {
            let list = [...this.list, ...res.data.data]
            list = list.filter(item => item.ifDraft == 1)
            this.list = list
            console.log(this.list);
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
    del(item) {
       delOrderInfoPer(item.id).then(res => {
        if(res.code == 200) {
         
        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      }).catch(error => {
        this.resetLoad()
        this.outputError(error)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.scroll {
  margin-bottom:  20px;
}
</style>
