<template>
  <div class="authorized">
    <van-nav-bar title="认证管理员" left-arrow @click-left="goBack" />
    <van-form ref="form" @submit="onSubmit" style="background: #fff">
      <van-field required v-model="form.username" disabled name="username" label="账号信息" placeholder="账号信息"  />
      <van-field required v-model="form.company" name="company" label="公司信息"  placeholder="公司信息" :rules="[{ required: true, message: '请输入公司' }]" />
      <div style="margin: 10px;">
        <van-button round block type="info" native-type="submit">提交认证</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
import { getUser, saveAdmin } from '@/api/wechatApi.js'
import { getToken, setSessionToken } from "@/utils/uToken";
export default {
  name: 'authorized',
  extends: BaseUI,
  data() {
    return {
      form: {}
    }
  },
  methods: {
    onSubmit() {
      this.form.apply = '1'
      this.form.role = '1'
      saveAdmin(this.form).then(responseData => {
          if(responseData.code == 200) {
            this.$toast.success("提交成功")
            this.$router.go(-1)
          } else {
            this.$toast.fail(responseData.msg)
          }
          this.resetLoad()
        })
        .catch(error => {
          this.resetLoad()
      })
    },
    async getUser() {
      let user = await getUser(this.currentUser.username)
      return user
    },
  },
  created() {
    this.getUser().then(res => {
      this.form = res.data
    })
  }
}
</script>

<style>

</style>