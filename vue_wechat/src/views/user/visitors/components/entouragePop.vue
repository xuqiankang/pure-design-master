<template>
  <div class="entouragePop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :close-on-click-overlay="false" :style="{ height: '70%',width: '100%' }" @close="close" @click-close-icon ="close">
        <div class="popupTitle">添加随行人员</div>
        <div class="popupTitle">当前随行人员:{{modelList.length}}</div>
        <div class="popupTitle">
          <van-button type="info" plain block native-type="button" @click="addPersonnel"> 新增随行人员 </van-button>
        </div>

        <div class="popupScroll">
          <div v-for="(model,index) in modelList" :key="index" class="cardPopup-content">

            <div class="cardPopup-content-left">
              <van-uploader class="upload" :after-read="afterRead" :name="index" :max-count="1"></van-uploader>
              <van-image fit="cover" :src="model.imgurl" />
            </div>

            <div class="cardPopup-content-right">
              <div class="slotlabel"><span class="requiredstar">*</span>随行人员{{index + 1}}</div>
              <van-field v-model="model.name"  name="随行人员" placeholder="请输入随行人员" :rules="[{ required: true, message: '' }]" />
              <div class="slotlabel">随行人员身份证号</div>
              <van-field v-model="model.IDnumber"  name="随行人员身份证号" placeholder="请输入随行人员身份证号" :rules="[{ required: false, message: '' }]" />
            </div>

            <van-icon name="close" size="20" color="#049ce0" @click="delPersonnel(index)" />
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
  name: 'entouragePop',
  props: {
    popShow: {
      type: Boolean
    },
    close: {
      type: Function
    },
    value: {
      type: Array
    }
  },
  data () {
    return {
      modelList: this.initModel()
    }
  },
  watch: {
    value(val, oldVal) {
      console.log(val != oldVal);
      if(val != oldVal) {
        if(this.value) {
          this.modelList = this.value
        } else {
          this.modelList = this.initModel()
        }
      }
    },
  },
  mounted () {
    // 初始值
    console.log(this.value);
    if(this.value) {
      this.modelList = this.value
    }
  },
  methods: {
    addPersonnel() {
      this.modelList.push( {'name': '', 'IDnumber': '', 'imgurl': null},)
    },
    delPersonnel(index) {
      this.modelList.splice(index, 1)
    },
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