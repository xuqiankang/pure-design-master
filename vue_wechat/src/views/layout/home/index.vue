<template>
  <div class="homepage">
    <Loading :show="loading" color="#1989fa"/>
    <div class="page-container">
      <div class="headerFiex">
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
          <van-swipe-item v-for="image in images" :key="image">
            <img :src="image.imgUrl" fit="cover"  width="100%" style="object-fit: cover;" />
          </van-swipe-item>
        </van-swipe>
      </div>

      <div class="scoll">
        <div class="swipeBox">
          <van-swipe style="height: 100%;" :autoplay="10000" vertical>
            <van-swipe-item>
              <van-icon name="bell" size="24" style="padding-left: 8px" color="#2a67fe" />
              <span>您有新的消息，请点击查看！</span>
              </van-swipe-item>
          </van-swipe>
        </div>
        <div class="myCard-not">
          <van-row gutter="16">
            <van-col :span="12">
              <router-link to="/visitors">
                <div class="myCardlink-not" :style="{background: '#2A67FE'}">访客预约</div>
              </router-link>
            </van-col>

              <van-col :span="12">
                <router-link :to="{path:'myReservation',query:{'vstPersonnalId':'953894111679987775'}}">
                  <div class="myCardlink-not" :style="{background: '#F9C95E'}">我的预约</div>
                </router-link>
              </van-col>


            <van-col :span="12" >
              <router-link to="/myVisitors">
                <div class="myCardlink-not" :style="{background: '#2bc107'}">我的来访</div>
              </router-link>
            </van-col>

            <van-col :span="12" >
              <router-link to="/myVisitors">
                <div class="myCardlink-not" :style="{background: '#ff6034'}">往期来访</div>
              </router-link>
            </van-col>
          </van-row>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
export default {
  name: 'homepage',
  extends: BaseUI,
  components: {
  },
  data () {
    return {
      images: [
        { imgUrl: require('@/assets/images/banner3.jpeg') },
        { imgUrl: require('@/assets/images/banner1.jpeg') },
        { imgUrl: require('@/assets/images/banner2.jpeg') }
      ],
      user: {
        photo: null,
        name: '老王头',
        phone: '15234343434',
        customerName: '123',
        permission: 'user',
      },
    }
  },
  created() {
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
.myCard-not {
  margin: 10px 10px 0px 10px;
  color: #fff;
  a {
    color: #fff;
  }
  .myCardlink-not {
    background: pink;
    border-radius: 10px;
    padding: 24px;
    text-align: center;
    margin-bottom: 10px;
  }
}
.swipeBox {
  height: 50px;
  background: #fff;
  margin: 6px;
  border-radius: 8px;
  /deep/ .van-swipe__indicators--vertical {
    display: none;
  }
  /deep/ .van-swipe-item {
    line-height: 55px;
  }
}
</style>
