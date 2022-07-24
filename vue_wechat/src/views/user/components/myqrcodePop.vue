<template>
  <div class="belongingsPop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :style="{ height: '60%',width: '100%' }" @close="close" @click-overlay="close" @click-close-icon ="close">
        <div class="popupTitle">访客二维码</div>
        <div class="popupTitle-item">租户名称/管理方公司</div>
        <div class="popupTitle-item">
          <van-row>
            <van-col :span="14">园区-楼宇-楼层-房间</van-col>
            <van-col :span="10">到访日期:2022-12-30</van-col>
          </van-row>
        </div>
        <div></div>
        <div class="popupTitle">
          <van-button type="info" plain block native-type="button"> 刷新二维码 </van-button>
        </div>

        <div class="popupScroll">
          生成二维码信息
        </div>
        <div class="submitbotton">
          <van-button type="info" block native-type="submit"> 确认 </van-button>
        </div>
      </van-popup>
    </van-form>
  </div>
</template>

<script>
export default {
  name: 'belongingsPop',
  props: {
    popShow: {
      type: Boolean
    },
    close: {
      type: Function
    }
  },
  data () {
    return {
      modelList: this.initModel(),
    }
  },
  mounted () {
  },
  methods: {
    initQrcode(val) {
      // 生成二维码信息
    },
    onSubmit() {
      this.close()
    },
    initModel(){
      return [
        {'name': '', 'amount': null},
      ]
    },
  }
}
</script>

<style scoped lang="scss">
.cardPopup-content-right {
  width: 90%;
}
</style>