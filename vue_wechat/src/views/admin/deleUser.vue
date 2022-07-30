<template>
<!-- 我的预约 -->
  <div class="myReservation">
    <van-nav-bar title="用户信息维护" left-arrow @click-left="goBack" />
    <div class="page-container">
      <div class="scroll">
        <div class="listCard-fill" v-if="list && list.length > 0">
          <div class="listCard-fill-content" v-for="(item, index) in list" :key="index"  >
            <div class="listCard-fill-box">
              <van-row type="flex" align="center" style="height: 100%;">
                <van-col :span="18">
                <div>
                  用户权限:
                  <van-tag v-if="item.role == 1" type="danger" size="medium" style="margin: 2px 4px">管理员</van-tag>
                  <van-tag v-if="item.role == 2" type="warning" size="medium" style="margin: 2px 4px">游客</van-tag>
                </div>
                <div>用户账号: {{ item.username}}</div>
                <div>用户名称: {{ item.nickname}}</div>
                <div>用户电话: {{ item.phone}}</div>
                <div>用户公司: {{ item.company}}</div>
                </van-col>
                <van-col :span="6">
                  <van-button @click="deleteApplys( item.id)" size="mini" type="danger" round >移除</van-button>
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
import { deleteApply, getAllInfoList,updateApply } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'deleUser',
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
      getAllInfoList().then(res => {
        this.list = []
        if(res.data instanceof Array) {
          this.$nextTick(() => {
            this.list = [...this.list, ...res.data]
            this.list = this.list.filter(item => item.role == 2 )
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
