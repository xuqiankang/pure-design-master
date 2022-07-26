<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="信息维护" left-arrow @click-left="goBack" />
    <div class="page-container">
      <div class="scroll">
        <div class="listCard-fill" v-if="list && list.lngth > 0">
          <div class="listCard-fill-content" v-for="(item, index) in list" :key="index"  >
            <div class="listCard-fill-box">
              <van-row type="flex" align="center" style="height: 100%;">
                <van-col :span="20">
                <div>
                  认证状态:
                  <van-tag v-if="item.apply == 1" type="danger" size="medium" style="margin: 2px 4px">待审核</van-tag>
                  <van-tag v-if="item.apply == 2" type="warning" size="medium" style="margin: 2px 4px">审核通过</van-tag>
                  <van-tag v-if="item.apply == 3" type="success" size="medium" style="margin: 2px 4px">已拒绝</van-tag>
                </div>
                <div>认证人账号: {{ item.username}}</div>
                <div>认证人名称: {{ item.nickname}}</div>
                <div>认证人电话: {{ item.phone}}</div>
                <div>认证人公司: {{ item.company}}</div>
                </van-col>
                <van-col :span="4">
                  <van-button @click="deleteApplys(item.id)" size="mini" type="danger" round >移除</van-button>
                </van-col>
              </van-row>
            </div>
          </div>
        </div>

        <van-empty v-else description="暂无最新数据!" />

      </div>
    </div>
  </div>
</template>

<script>
import { deleteApply, getApply } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'check',
  extends: BaseUI,
  components: {
  },
  data() {
    return {
      list: [],
    }
  },
  created() {
    this.getlist()
  },
  methods: {
    getlist() {
      let parms = {
        username: this.currentUser.username
      }
      getApply(parms).then(res => {
        this.list = []
        if(res.data instanceof Array) {
          this.$nextTick(() => {
            this.list = [...this.list, ...res.data]
            this.list = this.list.filter(item => item.apply != 1)
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
    deleteApplys(id) {
      deleteApply(id).then(res => {
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
    },
  }
}
</script>

<style lang="scss" scoped>
</style>
