<template>
    <div class="homepage">
      <div class="content-wrapper">
        <van-tabs v-model="active" @change="changetabs">
          <van-tab title="登录" name="1"></van-tab>
          <van-tab title="注册" name="0"></van-tab>
        </van-tabs>
        <van-form ref="form" @submit="onSubmit" style="background: #fff" v-if="active == 1">
          <van-cell-group>
            <van-field required v-model="loginForm.username" name="username" label="账号"  placeholder="账号" :rules="[{ required: true, message: '请设置账号' }]" />
            <van-field required v-model="loginForm.password" name="password" label="密码"  placeholder="密码" :rules="[{ required: true, message: '请输入密码' }]"  type="password" />
          </van-cell-group>
          <div style="margin: 10px;">
            <van-button round block type="info" native-type="submit">登录</van-button>
          </div>
        </van-form>

        <van-form ref="form" @submit="onSubmit" style="background: #fff" v-else>
          <van-cell-group>
            <van-field required v-model="registerForm.username" name="username" label="账号"  placeholder="账号" :rules="[{ required: true, message: '请设置账号' }]" />
            <van-field required v-model="registerForm.nickname" name="nickname" label="昵称"  placeholder="昵称" :rules="[{ required: true, message: '请输入昵称' }]"  />
            <van-field required v-model="registerForm.phone" name="phone" label="号码"  placeholder="号码" :rules="[{ required: true, message: '请输入号码' }]"  />
            <van-field required v-model="registerForm.password" name="password" label="密码"  placeholder="密码" :rules="[{ required: true, message: '请输入密码' }]"  type="password" />
            <van-field required v-model="registerForm.confirmPassword" name="password" label="确认密码"  placeholder="确认密码" :rules="[{ required: true, message: '请确认密码' }]"  type="password" />
          
          </van-cell-group>
          <div style="margin: 10px;">
            <van-button round block type="info" native-type="submit">登录</van-button>
          </div>
        </van-form>
      </div>
    </div>
</template>
<script>
import { setSessionToken, setToken } from "@/utils/uToken";
import { login ,saveAdmin} from '@/api/wechatApi.js'
import BaseUI from '@/views/components/baseUI'
export default {
    extends: BaseUI,
    name: "login",
    data() {
        return {
          active: '1',
          loginForm: this.initregisterForm(),
          registerForm: this.initregisterForm()
        }
    },
    mounted() {
    },
    methods: {
      makeregister() {
        if (this.registerForm.password !== this.registerForm.confirmPassword) {
          this.$toast.fail("2次输入的新密码不相同")
          return false
        }
        saveAdmin(this.registerForm)
          .then(responseData => {
            if(responseData.code == 200) {
              this.$dialog.alert({
                message: '注册成功,去登录',
              }).then(() => {
                this.loginForm = this.initregisterForm()
                this.registerForm = this.initregisterForm()
                this.active = 1
              });
            } else {
              this.$toast.fail(responseData.msg)
            }
            this.resetLoad()
          })
          .catch(error => {
            this.resetLoad()
        })
      },
      makelogin() {
        login(this.loginForm)
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
      },
      onSubmit() {
        this.setLoad()
        if (this.active == 1) {
          this.makelogin()
        } else {
          this.makeregister()
        }
      },
      initregisterForm() {
        return {
          'username': '',
          'password': ''
        }
      },
      initloginForm() {
        return {
          'role': '2',
          'username': '',
          'nickname': '',
          'phone':'',
          'password': '',
          'confirmPassword': '',
        }
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
