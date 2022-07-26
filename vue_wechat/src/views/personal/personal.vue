<template>
  <div class="personal">
    <van-nav-bar title="个人信息修改" left-arrow @click-left="goBack" />
    <van-form ref="form" @submit="onSubmit" style="background: #fff">
      <van-field required v-model="form.username" disabled name="username" label="用户名" placeholder="用户名"  />
      <van-field required v-model="form.nickname" name="nickname" label="昵称"  placeholder="昵称" :rules="[{ required: true, message: '请输入昵称' }]" />
      <van-field required v-model="form.company" name="company" label="公司"  placeholder="公司" :rules="[{ required: true, message: '请输入公司' }]" />
      <van-field required v-model="form.phone" type="tel" name="phone" label="电话" placeholder="电话" :rules="[{ required: true, message: '请输入电话' }]" />
      <van-field required v-model="form.email" name="email" label="邮箱"  placeholder="邮箱" :rules="[{ required: true, message: '请输入邮箱' }]" />
      <van-field  v-model="form.address" name="address" label="地址"  placeholder="地址" />

      <div style="margin: 10px;">
        <van-button round block type="info" native-type="submit">确定</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
import { getUser, saveAdmin } from '@/api/wechatApi.js'
import { getToken, setSessionToken } from "@/utils/uToken";
export default {
  name: 'personal',
  extends: BaseUI,
  data() {
    return {
      form: {}
    }
  },
  methods: {
    onSubmit() {
      saveAdmin(this.form).then(responseData => {
          if(responseData.code == 200) {
            this.getUser().then(res => {
              res.token = getToken('token')
              setSessionToken('currentUser', res.data)
              this.$toast.success("修改成功")
              this.$router.go(-1)
            }).catch(()=> {
              this.$toast.fail("修改失败")
            })
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