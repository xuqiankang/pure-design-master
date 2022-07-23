<template>
<!-- 个人信息维护 -->
  <div class="visitorPop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :close-on-click-overlay="false" :style="{ height: '70%',width: '100%' }" @close="close" @click-close-icon ="close">
        <div class="popupTitle">个人信息维护</div>
        <div class="popupScroll">
            <div class="cardPopup-content">
              <div class="cardPopup-content-left">
                <van-uploader class="upload" :after-read="afterRead" :name="index" :max-count="1"></van-uploader>
                <van-image fit="cover" :src="modelModel.attachmentId" />
              </div>
              <div class="cardPopup-content-right">
                <div class="slotlabel"><span class="requiredstar">*</span>姓名</div>
                <van-field v-model="modelModel.name"  name="姓名" placeholder="请输入您的姓名" :rules="[{ required: false, message: '' }]" />
                <div class="slotlabel"><span class="requiredstar">*</span>公司</div>
                <van-field v-model="modelModel.customerName"  name="公司" placeholder="请输入您的公司" :rules="[{ required: false, message: '' }]" />
              </div>
            </div>
            <div class="cardPopup-bot">
              <!-- <div class="slotlabel">联系方式</div>
              <van-field v-model="modelModel.phone"  name="联系方式" placeholder="请输入联系方式" :rules="[{ required: false, message: '' }]" /> -->
              <div class="slotlabel">访客人员身份证号</div>
              <van-field v-model="modelModel.certificatenNo"  name="访客人员身份证号" placeholder="请输入访客人员身份证号" :rules="[{ required: false, message: '' }]" />
            </div>
        </div>

        <div class="submitbotton">
          <van-button type="info" block native-type="submit" @click="bulkUpdateInfo()"> 确认 </van-button>
        </div>
      </van-popup>
    </van-form>
  </div>
</template>


<script>
// import { bulkUpdateVstPersonnalInfo } from '@/api/vstPersonnalInfo.js'

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
      modelModel: this.initModel()
    }
  },
  watch: {
    value(val, oldVal) {
      if(val != oldVal) {
        if(this.value) {
          this.modelModel = this.value
        }
      }
    },
  },
  mounted () {
    // 初始值
    if(this.value) {
      this.modelModel = this.value
    }
  },
  methods: {
    afterRead(file,nameKey) {
      this.modelModel.attachmentId = file.content
    },
    onSubmit() {
      this.close()
    },
    initModel(){
      return [
        {
          'attachmentId': null,
          'name': '',
          'phone': '',
          'customerName': '',
          'permission': '',
          'certificateNo': ''
        }
      ]
    },
    async bulkUpdateInfo(){
      const update = [this.modelModel];
      console.log("111:"+JSON.stringify(update))
      // const data = await bulkUpdateVstPersonnalInfo(update)
      // if(data.type == 'success'){
        // console.log(data.type)
      // }
    }
  }
}
</script>

<style scoped lang="scss">
/deep/.van-cell {
  background: none;
}
/deep/.van-cell::after {
  border-bottom: 0.02667rem solid #c8c9cc;
}
.cardPopup-bot{
  background: #fafafa;
}
.cardPopup-content {
  margin-bottom: 0;
}
</style>
