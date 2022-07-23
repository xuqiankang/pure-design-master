<template>
  <div class="plateNumberPop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :close-on-click-overlay="false" :style="{ height: '70%',width: '100%' }" @close="close" @click-close-icon ="close">
        <div class="popupTitle">添加车牌号</div>
        <div class="popupTitle">当前车牌号:{{modelList.length}}</div>
        <div class="popupTitle">
          <van-button type="info" plain block native-type="button" @click="addPersonnel"> 新增车牌号 </van-button>
        </div>

        <div class="popupScroll">
          <div v-for="(personnel,index) in modelList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <!-- 城市简称 -->
              <div class="slotlabel"><span class="requiredstar">*</span>车牌号{{index + 1}}</div>
              <van-field v-model="personnel.plateNumber"  name="车牌号" placeholder="请输入车牌号" :rules="[{ required: true, message: '' }]" />
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
  name: 'plateNumberPop',
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
    if(this.value) {
      this.modelList = this.value
    }
  },
  methods: {
    addPersonnel() {
      this.modelList.push( {'cityname': '', 'plateNumber': ''},)
    },
    delPersonnel(index) {
      this.modelList.splice(index, 1)
    },
    onSubmit() {
      this.close()
    },
    initModel(){
      return [
        {'cityname': '', 'plateNumber': ''},
      ]
    },
  }
}
</script>

<style scoped lang="scss">
</style>