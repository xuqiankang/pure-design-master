<template>
  <div class="addAdmin">
    <van-nav-bar title="新建管理员" left-arrow @click-left="goBack" />
    <van-form ref="form" @submit="onSubmit" style="background: #fff">
      <van-field required v-model="form.username" name="username" label="账号" placeholder="账号" :rules="[{ required: true, message: '请输入账号' }]"  />
      <van-field disabled required v-model="form.password" name="password" label="密码(默认)"  placeholder="密码" :rules="[{ required: true, message: '请输入原密码' }]"  type="password" />
      <!-- <van-field required v-model="form.nickname" name="nickname" label="昵称"  placeholder="昵称" :rules="[{ required: true, message: '请输入昵称' }]" /> -->
      <van-field required v-model="form.company" name="company" label="公司"  placeholder="公司" :rules="[{ required: true, message: '请输入公司' }]" />
      <van-field v-model="form.phone" type="tel" name="phone" label="电话" placeholder="电话" :rules="[{ required: false, message: '请输入电话' }]" />
      <van-field v-model="form.email" name="email" label="邮箱"  placeholder="邮箱" :rules="[{ required: false, message: '请输入邮箱' }]" />
      <van-field  v-model="form.address" name="address" label="地址"  placeholder="地址" />

      <div style="margin: 10px;">
        <van-button round block type="info" native-type="submit">确定</van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
import { saveAdmin } from '@/api/wechatApi.js'
import { getToken, setSessionToken } from "@/utils/uToken";
export default {
  name: 'addAdmin',
  extends: BaseUI,
  data() {
    return {
      form: {
        username: '',
        password: '123',
        company: '',
        role: '1',
        phone: '',
        email: '',
        address: ''
      }
    }
  },
  methods: {
    onSubmit() {
      saveAdmin(this.form).then(responseData => {
          if(responseData.code == 200) {
            this.$toast.success("新建成功,可以下发账号信息啦!")
            this.$router.go(-1)
          } else {
            this.$toast.fail(responseData.msg)
          }
          this.resetLoad()
        })
        .catch(error => {
          this.resetLoad()
      })
    }
  },
  created() {
  }
}
</script>

<style>

</style>