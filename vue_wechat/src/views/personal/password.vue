<template>
    <div class="homepage">
      <van-nav-bar title="修改密码" left-arrow @click-left="goBack" />
      <div class="content-wrapper">
        <van-form ref="form" @submit="onSubmit" style="background: #fff">
          <van-cell-group>
            <van-field required v-model="form.password" name="password" label="原密码"  placeholder="原密码" :rules="[{ required: true, message: '请输入原密码' }]"  type="password" />
            <van-field required v-model="form.newPassword" name="password" label="新密码"  placeholder="新密码" :rules="[{ required: true, message: '请输入新密码' }]"  type="password" />
            <van-field required v-model="form.confirmPassword" name="password" label="确认新密码"  placeholder="确认新密码" :rules="[{ required: true, message: '请确认新密码' }]"  type="password" />
          </van-cell-group>

          <div style="margin: 10px;">
            <van-button round block type="info" native-type="submit">确定</van-button>
          </div>
        </van-form>
      </div>
    </div>
</template>
<script>
import { password } from '@/api/wechatApi.js'
import { removeSessionToken, removeToken } from "@/utils/uToken";
import BaseUI from '@/views/components/baseUI'
export default {
    extends: BaseUI,
    name: "password",
    data() {
        return {
          form: {
            password: '',
            newPassword: '',
            confirmPassword: '',
            username: ''
          },
        }
    },
    mounted() {
     this.form.username = this.currentUser.username
    },
    methods: {
      onSubmit() {
        if (this.form.newPassword !== this.form.confirmPassword) {
          this.$toast.fail("2次输入的新密码不相同")
          return false
        }
        password(this.form)
          .then(responseData => {
            if(responseData.code == 200) {
              this.$toast.success("修改成功,请重新登录")
              removeSessionToken('currentUser')
              removeToken('token')
              this.$router.replace('/login')
            } else {
              this.$toast.fail(responseData.msg)
            }
            this.resetLoad()
          })
          .catch(error => {
            this.resetLoad()
        })
      }
    }
}
</script>

<style scoped lang="scss">
.container {
  height: 100%;
  .content-wrapper {
    position: absolute;
    top: 40px;
    width: 100%;
  }
}
.radioField {
  padding: 0;
}
</style>
