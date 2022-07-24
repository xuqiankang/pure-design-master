<template>
    <div class="homepage">
      <van-nav-bar  title="登录" left-arrow @click-left="goBack" />
      <div class="content-wrapper">
        <van-form ref="form" @submit="onSubmit" style="background: #fff">
          <van-cell-group>
            <van-field required v-model="loginForm.username" name="username" label="用户名"  placeholder="用户名" :rules="[{ required: true, message: '请输入用户名' }]" />
            <van-field required v-model="loginForm.password" name="password" label="密码"  placeholder="密码" :rules="[{ required: true, message: '请输入密码' }]"  type="password" />
          </van-cell-group>

          <van-field name="checkboxGroup" class="radioField" label="" :border="false"  :rules="[{ required: true, message: '请选择' }]">
            <template #input>
              <van-radio-group v-model="loginForm.radio" style="margin: 10px" required>
                <van-radio name="1" icon-size="14px"><span style="color: #747474;">未注册的账号验证后将自动创建账号</span></van-radio>
              </van-radio-group>
            </template>
          </van-field>

          <div style="margin: 10px;">
            <van-button round block type="info" native-type="submit">登录</van-button>
          </div>
        </van-form>
      </div>
    </div>
</template>
<script>
import { setSessionToken, setToken } from "@/utils/uToken";
import { login } from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
    extends: BaseUI,
    name: "login",
    data() {
        return {
          loginForm: {
            radio: '',
            username: '',
            password: ''
          },
        }
    },
    mounted() {
    },
    methods: {
      onSubmit() {
        let uer = {
          username: this.loginForm.username,
          password: this.loginForm.password
        }
        this.setLoad()
        login(uer)
          .then(responseData => {
            if(responseData.code == 200) {
              setSessionToken('currentUser', responseData.data)
              setToken('token', responseData.data.token)
              this.$toast.success('登录成功')
              this.$router.push('/')
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
