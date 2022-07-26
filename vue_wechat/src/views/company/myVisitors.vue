<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="我的来访" left-arrow @click-left="goBack" />
    <div class="page-container">
       <div class="scroll">
        <div v-for="(item, index) in list" :key="index" class="listCard-fill">
          <div class="listCard-fill-content">
            <div class="listCard-fill-box">
              <van-row type="flex" align="center" style="height: 100%;">
                <van-col :span="16">
                  <div>
                    预约单状态:
                    <van-tag v-if="item.status == 2" type="warning" size="medium" style="margin: 2px 4px">待审核</van-tag>
                    <van-tag v-if="item.status == 3" type="success" size="medium" style="margin: 2px 4px">已审核</van-tag>
                    <van-tag v-if="item.status == 4" type="danger" size="medium" style="margin: 2px 4px">已拒绝</van-tag>
                    <van-tag v-if="item.status == 5" type="7232dd" size="medium" style="margin: 2px 4px">已过期</van-tag>
                  </div>
                  <div>来访人名称:{{item.visitCompany}}</div>
                  <div>来访人电话:{{item.phone}}</div>
                  <div>来访事由:{{item.purpose}}</div>
                  <div>来访日期:{{getTime(item)}}</div>
                </van-col>
                <van-col :span="8">
                  <van-button size="mini" type="info" round color="#FFA94F" @click="open(item, 'company')"> 查看 </van-button>
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
import { getOrderInfo, saveForm } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
import {validatenull} from '@/utils/validate'
export default {
  name: 'myReservation',
  extends: BaseUI,
  components: {
  },
  data() {
    return {
      list: [],
    }
  },
  created() {
    this.getlist();
  },
  methods: {
    // 查看
    open(val, type) {
      this.$router.push({
        name: 'visitorsForm',
        query: {
          form: val,
          type
        }
      })
    },
    getTime(val) { 
      if (validatenull(val.time)) return 
      return val.time.slice(0,10)
    },
    getlist() {
      this.list = []
      getOrderInfo(this.currentUser.username).then(res => {
        if(res.data.data instanceof Array) {
          this.$nextTick(() => {
            let list = [...this.list, ...res.data.data]
            list = list.filter(item => item.status == 2)
            this.list = list
          })
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
