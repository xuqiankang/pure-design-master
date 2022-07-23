<template>
  <div class="homepage">
    <Loading :show="loading" color="#1989fa"/>
    <div class="page-container">
      <div class="headerFiex">
        <div class="homepage-image">
          <div class="homepage-user">
            <van-row type="flex" align="center" style="height: 100%; ">
                <van-col :span="8">
                  <div class="avatar">
                    <div class="avatar-icon" >
                      <van-icon name="plus" size="30" color="#2A67FE"/>
                    </div>
                  </div>
                </van-col>

                <van-col :span="16">
                  <p>{{user.name}}</p>
                  <p>{{user.phone}}</p>
                  <p>{{user.customerName}}</p>
                </van-col>
            </van-row>
            <div class="homepage-qr">
              <div class="homepage-tag" v-if="user.permission == '2'">访客</div>
              <div class="homepage-tag" v-if="user.permission == '1'">公司管理员</div>
              <div class="homepage-tag" v-if="user.permission == '0'">超级管理员</div>
              <van-image fit="cover" :src="qr" />
            </div>
          </div>
        </div>
      </div>

      <div class="scoll">
         <div>
            <van-cell to="" icon="label-o" color="#6D7884" title="个人信息" is-link />
            <van-cell to="myReservation" icon="label-o" color="#2A67FE" title="我的预约" is-link />
            <van-cell to="" icon="label-o" color="#F9C95E" title="我的邀约" is-link />
            <van-cell to="" icon="label-o" color="#ee0a24" title="我的办公区域" is-link />
            <van-cell to="" icon="label-o" color="#ee0a24" title="公司员工" is-link />
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import qr from '@/assets/images/qrcode.jpeg'
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'homepage',
  extends: BaseUI,
  components: {
  },
  data () {
    return {
      qr: qr,
      images: [
        { imgUrl: require('@/assets/images/banner1.jpeg') },
        { imgUrl: require('@/assets/images/banner2.jpeg') },
      ],
      user: {
        photo: null,
        name: '老王头',
        phone: '15234343434',
        customerName: '123',
        permission: '0',
      },
    }
  },
  created() {
  },
  mounted(){
  },
  methods: {
    async getUserInfo(){
      const data = await getByOpenId('oVCKZ6Jv-D2aE6jUrb9-nJElv0RM')
      if(data.type == 'success'){
        this.user = data.data
      }else{

      }
    },

  }
}
</script>

<style lang="scss" scoped>
  .homepage-image {
    position: relative;
    width: 100%;
    height: 200px;
    background: linear-gradient(to top, #FFFFFF, transparent),url('~@/assets/images/homopage3.jpeg') no-repeat;
    opacity: 0.9;
    background-size: 100% auto;
  }
  .homepage-user {
    position: absolute; // 保证背景图片上的文字清晰
    top: 60%;
    left: 50%;
    width: 84%;
    height: 4rem;
    background: #ffffffbd;
    border-radius: 20px;
    transform: translate(-50%, -50%);
    overflow: hidden;
    padding: 0 10px;
  }

.avatar {
  width: 80px;
  height: 80px;
  border: 2px dashed #2A67FE;
  background: #2a67fe30;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
  .avatar-icon {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  .upload {
    opacity: 0;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    z-index: 9;
  }
}
.homepage-qr {
  position: absolute;
  right: 0;
  top: 10%;
  display: flex;
  flex-direction: column;
  align-items: end;
  .van-image {
    width: 30px;
    margin-right: 16px;
  }
  .homepage-tag {
    color: #fff;
    margin-bottom: 10px;
    padding: 0px 16px;
    background: #2A67FE;
    border-radius: 20px 0 0 20px;
  }
}
</style>
