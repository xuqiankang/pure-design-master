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
        <div v-for="(item, index) in list" :key="index" class="listCard-fill">
          <div class="listCard-fill-content">
            <div class="listCard-fill-box">
              <van-row type="flex" align="center" style="height: 100%;">
                <van-col :span="16">
                 <div>
                    预约单状态:
                    <van-tag v-if="active == 0" type="danger" size="medium" style="margin: 2px 4px">草稿</van-tag>
                    <van-tag v-if="item.status == 2" type="warning" size="medium" style="margin: 2px 4px">待审核</van-tag>
                    <van-tag v-if="item.status == 3" type="success" size="medium" style="margin: 2px 4px">已通过</van-tag>
                    <van-tag v-if="item.status == 4" type="danger" size="medium" style="margin: 2px 4px">未通过</van-tag>
                    <van-tag v-if="item.status == 5" type="7232dd" size="medium" style="margin: 2px 4px">已过期</van-tag>
                 </div>

                  <div>到访公司:{{item.visitCompany}}</div>
                  <div>管理员:{{item.visitAdmin}}</div>
                  <div> 到访日期:{{getTime(item)}}</div>
                </van-col>
                <van-col :span="8">
                  <van-button size="mini" type="info" round color="#FFA94F"  @click="open(item, 'user')"> 查看 </van-button>
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
import { getOrderInfoPer, delOrderInfoPer } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
import {validatenull} from '@/utils/validate'

export default {
  name: 'myReservation',
  extends: BaseUI,
  components: {
  },
  data() {
    return {
      active: '1',
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
    // 切换时
    changetabs() {
      this.getlist();
    },
    getTime(val) { 
      if (validatenull(val.time)) return 
      return val.time.slice(0,10)
    },
    getlist() {
      this.list = []
      getOrderInfoPer(this.currentUser.username).then(res => {
        if(res.data.data instanceof Array) {
          this.$nextTick(() => {
            let list = [...this.list, ...res.data.data]
            list = list.filter(item => item.ifDraft == this.active)
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
    },
    del(item) {
       delOrderInfoPer(item.id).then(res => {
        if(res.code == 200) {
          this.$toast.success('操作成功')
          this.getlist()
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
