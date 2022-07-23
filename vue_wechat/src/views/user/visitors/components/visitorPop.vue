<template>
  <div class="visitorPop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :close-on-click-overlay="false" :style="{ height: '70%',width: '100%' }" @close="close" @click-close-icon ="close">
        <div class="popupTitle">编辑访客姓名</div>
        <div class="popupScroll">
            <div v-for="(model,index) in modelList" :key="index" class="cardPopup-content">
              <div class="cardPopup-content-left">
                <van-uploader class="upload" :after-read="afterRead" :name="index" :max-count="1"></van-uploader>
                <van-image fit="cover" :src="model.imgurl" />
              </div>
              <div class="cardPopup-content-right">
                <div class="slotlabel"><span class="requiredstar">*</span>访客姓名</div>
                <van-field v-model="model.name"  name="访客姓名" placeholder="请输入访客姓名" :rules="[{ required: true, message: '' }]" />
                <div class="slotlabel">访客人员身份证号</div>
                <van-field v-model="model.IDnumber"  name="访客人员身份证号" placeholder="请输入访客人员身份证号" :rules="[{ required: false, message: '' }]" />
              </div>
            </div>
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
  name: 'visitorPop',
  props: {
    popShow: {
      type: Boolean
    },
    close: {
      type: Function
    },
    value: {
      type: Object
    }
  },
  data () {
    return {
      modelList: this.initModel()
    }
  },
  watch: {
    value(val, oldVal) {
      if(val != oldVal) {
        if(this.value) {
          this.modelList = []
          this.modelList.push(this.value)
        } else {
          this.modelList = this.initModel()
        }
      }
    },
  },
  mounted () {
    // 初始值
    if(this.value) {
      this.modelList = []
      this.modelList.push(this.value)
    }
  },
  methods: {
    afterRead(file,nameKey) {
      this.modelList[nameKey.name].imgurl = file.content
    },
    onSubmit() {
      this.close()
    },
    initModel(){
      return [
        {'name': '', 'IDnumber': '', 'imgurl': null},
      ]
    },
  }
}
</script>

<style scoped lang="scss">
</style>