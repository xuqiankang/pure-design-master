<template>
  <div class="belongingsPop" v-if="popShow">
    <van-form @submit="onSubmit">
      <van-popup v-model="popShow" round position="bottom" closeable class="cardPopup" :close-on-click-overlay="false" :style="{ height: '70%',width: '100%' }" @close="close" @click-close-icon ="close">
        <div class="popupTitle">添加随行物品</div>
        <div class="popupTitle">当前随行物品: {{amountSum}} </div>
        <div class="popupTitle">
          <van-button type="info" plain block native-type="button" @click="addPersonnel"> 新增随行物品 </van-button>
        </div>

        <div class="popupScroll">
          <div v-for="(personnel,index) in modelList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <div class="slotlabel"><span class="requiredstar">*</span>随行物品</div>
              <van-row>
                <van-col :span="16">
                  <van-field v-model="personnel.name"  name="随行物品" placeholder="请输入随行物品名称" :rules="[{ required: true, message: '' }]" />
                </van-col>
                <van-col :span="8">
                  <van-field v-model="personnel.amount"  name="数量" placeholder="数量" :rules="[{ required: true, message: '' }]" />
                </van-col>
              </van-row>
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
  name: 'belongingsPop',
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
      modelList: this.initModel(),
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
  computed: {
    amountSum() {
      var sum = 0
      this.modelList.map(item => {
        if (item.amount) {
          sum += parseFloat(item.amount)
        }
      })
      return sum
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
      this.modelList.push( {'name': '', 'amount': null})
    },
    delPersonnel(index) {
      this.modelList.splice(index, 1)
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