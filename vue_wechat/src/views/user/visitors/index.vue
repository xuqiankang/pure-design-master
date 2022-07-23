<template>
<!-- 访客预约表单 -->
  <div class="reservationform" >
    <div class="page-container">
    <div class="scroll">
      <van-form @submit="onSubmit">
          <div class="slotlabel">被访公司</div>
          <van-field readonly clickable name="picker" :value="bizFormModel.beVisitedCompany.name" placeholder="请选择被访公司" @click="showbeVisitCompany = true" />
          <van-popup v-model="showbeVisitCompany" position="bottom">
            <van-picker
              :default-index="0"
              value-key="name"
              show-toolbar
              :columns="companyList"
              @confirm="onConfirmBeVisitedCompany"
              @cancel="showbeVisitCompany = false"
            />
          </van-popup>

          <div class="slotlabel"><span class="requiredstar">*</span>被访人员</div>
          <van-field v-model="bizFormModel.beVisitedPerson"  name="被访人员姓名" placeholder="请输入被访人员姓名" :rules="[{ required: true, message: '' }]" />

          <div class="slotlabel"><span class="requiredstar">*</span>被访人员联系电话</div>
          <van-field v-model="bizFormModel.beVisitedPhone" type="tel" name="被访人员联系电话" placeholder="请输入被访人员联系电话" :rules="[{ required: true, message: '' }]" />

          <div class="slotlabel"  ><span class="requiredstar">*</span>访客姓名</div>
          <van-field  @click="visitorPopShow = true" readonly v-model="bizFormModel.visitorinformation.name" clearable name="picker" placeholder="点击添加访客姓名" :rules="[{ required: true, message: '' }]"  >
            <template #input v-if="bizFormModel.visitorinformation">
              <van-tag type="primary" plain size="large"  v-if="bizFormModel.visitorinformation.name">{{bizFormModel.visitorinformation.name}}</van-tag>
              <span v-else style="color:#c8c9cc">点击添加访客姓名</span>
            </template>
            <template #button>
              <van-icon name="edit" color="#1989fa" size="20" @click="visitorPopShow = true" />
            </template>
          </van-field>

          <div class="slotlabel"><span class="requiredstar">*</span>访客联系电话</div>
          <van-field v-model="bizFormModel.visitorPhone" type="tel" name="访客联系电话" placeholder="请输入访客联系电话" :rules="[{ required: true, message: '' }]" />
        
          <div class="slotlabel"><span class="requiredstar">*</span>公司名称</div>
          <van-field v-model="bizFormModel.visitorCompany"  name="公司名称" placeholder="请输入公司名称" :rules="[{ required: true, message: '' }]" />

          <div class="slotlabel">随行人员</div>
          <van-field  @click="entouragePopShow = true" readonly  clearable name="picker" placeholder="点击添加随行人员" :rules="[{ required: false, message: '' }]"  >
            <template #input>
              <span v-for="(entour,index) in bizFormModel.visitorEntourageList" :key="index">
                <van-tag type="primary" plain size="large" v-if="entour.name && index <= 1">{{entour.name}} </van-tag>
              </span>
              <van-tag type="primary" plain size="large" v-if="bizFormModel.visitorEntourageList.length > 2">...</van-tag>
              <span v-if="bizFormModel.visitorEntourageList.length <= 0 " style="color:#c8c9cc">点击添加随行人员</span>
            </template>
            <template #button>
              <van-icon name="edit" color="#1989fa" size="20" @click="entouragePopShow = true" />
            </template>
          </van-field>

          <div class="slotlabel"><span class="requiredstar">*</span>到访事由</div>
          <van-field v-model="bizFormModel.visitorReason" rows="1" autosize type="textarea" name="到访事由"  placeholder="请输入到访事由" :rules="[{ required: true, message: '' }]" />

          <div class="slotlabel"><span class="requiredstar">*</span>到访日期</div>
          <van-field readonly clickable name="calendar" :value="bizFormModel.visitorDate" placeholder="点击选择日期" :rules="[{ required: true, message: '' }]" @click="showVisitorDate = true"  />
          <!-- <van-calendar v-model="showVisitorDate" @confirm="onConfirmVisitorDate" />    -->

          <div class="slotlabel">车牌号</div>
          <van-field @click="plateNumberShow = true" readonly  clearable name="picker" placeholder="点击添加车牌号" :rules="[{ required: false, message: '' }]"  >
            <template #input>
              <span v-for="(plate,index) in bizFormModel.plateNumberList" :key="index">
                <van-tag type="primary" plain size="large" v-if="plate.plateNumber && index <= 1">{{plate.plateNumber}} </van-tag>
                <span v-if="!plate.plateNumber " style="color:#c8c9cc">点击添加车牌号</span>
              </span>
              <van-tag type="primary" plain size="large" v-if="bizFormModel.plateNumberList.length > 2">...</van-tag>
              <span v-if="bizFormModel.plateNumberList.length <= 0 " style="color:#c8c9cc">点击添加车牌号</span>
            </template>
            <template #button>
              <van-icon name="edit" color="#1989fa" size="20" @click="plateNumberShow = true" />
            </template>
          </van-field>

          <div class="slotlabel">随行物品</div>
          <van-field @click="belongingsShow = true" readonly  clearable name="picker" placeholder="点击添加车牌号" :rules="[{ required: false, message: '' }]"  >
            <template #input>
              <span v-for="(plate,index) in bizFormModel.belongingsList" :key="index">
                <van-tag type="primary" plain size="large" v-if="plate.name && index <= 1">{{plate.name}} </van-tag>
              </span>
              <van-tag type="primary" plain size="large" v-if="bizFormModel.belongingsList.length > 2">...</van-tag>
              <span v-if="bizFormModel.belongingsList.length <= 0 " style="color:#c8c9cc">点击添加随行物品</span>
            </template>
            <template #button>
              <van-icon name="edit" color="#1989fa" size="20" @click="belongingsShow = true" />
            </template>
          </van-field>
          <div style="margin: 16px;">
            <van-button type="info" block native-type="submit"> 确定 </van-button>
          </div>
      </van-form>
    </div>
    <!-- <visitor-pop v-model="this.bizFormModel.visitorinformation" :popShow="visitorPopShow" :close="visitorPopClose" ref="visitorPop"></visitor-pop>
    <entourage-pop v-model="this.bizFormModel.visitorEntourageList" :popShow="entouragePopShow" :close="entouragePopClose" ref="entouragePop"></entourage-pop>
    <plate-number-pop v-model="this.bizFormModel.plateNumberList" :popShow="plateNumberShow" :close="plateNumberPopClose" ref="entouragePop"></plate-number-pop>
    <belongings-pop v-model="this.bizFormModel.belongingsList" :popShow="belongingsShow" :close="belongingsClose" ref="entouragePop"></belongings-pop> -->
    </div>
  </div>
</template>

<script>
// import visitorPop from '../../layout/visitorsForespeak/components/visitorPop.vue'
// import entouragePop from '../../layout/visitorsForespeak/components/entouragePop.vue'
// import plateNumberPop from '../../layout/visitorsForespeak/components/plateNumberPop.vue'
// import belongingsPop from '../../layout/visitorsForespeak/components/belongingsPop.vue'

export default {
  name: 'reservationform',
  components: {
    // visitorPop,
    // entouragePop,
    // plateNumberPop,
    // belongingsPop
  },
  data () {
    return {
      visitorPopShow: false,
      entouragePopShow: false,
      plateNumberShow: false,
      belongingsShow: false,
      showbeVisitCompany: false, // 被访公司选择弹框
      showVisitorDate: false, // 到访日期
      companyList: [
        {name: 'xxx管理有限公司1', id: 11},
        {name: 'xxx管理有限公司2', id: 22},
      ],
      bizFormModel: this.initFormModel(),
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    visitorPopClose() {
      this.visitorPopShow = false
    },
    entouragePopClose() {
      this.entouragePopShow = false
    },
    plateNumberPopClose() {
      this.plateNumberShow = false
    },
    belongingsClose() {
      this.belongingsShow = false
    },
    onSubmit() {
    },
    // 返回
    onClickLeft() {
      this.$emit('goback')
    },
    // 到访公司选中
    onConfirmBeVisitedCompany(value) {
      this.bizFormModel.beVisitedCompany = value
      this.showbeVisitCompany = false;
    },
    // 日期选中
    onConfirmVisitorDate(date) {
      this.bizFormModel.visitorDate = date.toISOString().slice(0,10)
      this.showVisitorDate = false;
    },
    initFormModel(This) {
      return {
        'beVisitedCompany': {     // 被访公司名称
          'id': 11,
          'name': 'xxx管理有限公司1',
        },
        'beVisitedPerson': '', // 被访问人员
        'beVisitedPhone': '', // 被访问人员电话

        'visitorinformation': {
          'name': '', // 来访人员姓名
          'IDnumber': '', 
          'imgurl': null
        },
        'visitorPhone': '', // 来访人员电话
        'visitorCompany': '', // 来访人员公司名称
        'visitorEntourageList': [], // 随访人员列表
        'visitorDate': null, // 到访日期
        'visitorReason': '', // 到访事由
        'plateNumberList': [], // 车牌
        'belongingsList': [] // 随行物品
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.reservationform {
  background: #fff;
}
</style>
