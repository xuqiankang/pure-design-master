<template>
<!-- 访客预约表单 -->
  <div class="reservationform" >
    <van-form @submit="onSubmit">
    <div class="page-container">
      <div class="headerFiex">
        <van-nav-bar title="预约信息填写" left-arrow @click-left="goBack" />
      </div>
      <div class="scroll">
        <van-cell-group title="个人信息">
          <van-field required v-model="bizFormModel.name" label="姓名" name="name" placeholder="姓名" :rules="[{ required: true, message: '请输入姓名' }]" />
          <van-field required v-model="bizFormModel.phone" type="tel" name="phone" label="电话" placeholder="电话" :rules="[{ required: true, message: '请输入电话' }]" />
          <van-field v-model="bizFormModel.company" name="company" label="公司"  placeholder="公司"  />
          <van-field v-model="bizFormModel.email" name="email" label="邮箱"  placeholder="邮箱"  />
        </van-cell-group>

        <van-cell-group title="到访信息">
          <van-field required readonly clickable label="到访公司" name="picker" :value="bizFormModel.visitCompany" placeholder="请选择被访公司" @click="showbeVisitCompany = true" />
          <van-popup v-model="showbeVisitCompany" position="bottom">
          <van-picker
              :default-index="0"
              value-key="name"
              show-toolbar
              :columns="companyList"
              @confirm="onConfirmCompany"
              @cancel="showbeVisitCompany = false"
          />
          </van-popup>

          <van-field required readonly clickable label="到访人员" name="picker" :value="bizFormModel.visitAdmin" placeholder="请选择被访人员" @click="openAdmin" />
          <van-popup v-model="showvVsitAdmin" position="bottom">
            <van-picker
              :default-index="0"
              value-key="name"
              show-toolbar
              :columns="adminList"
              @confirm="onConfirmAdmin"
              @cancel="showvVsitAdmin = false"
            />
          </van-popup>

          <van-field required readonly clickable label="到访日期" name="calendar" :value="bizFormModel.timebak" placeholder="到访日期" :rules="[{ required: true, message: '点击选择到访日期' }]" @click="showTime = true"  />
          <van-calendar v-model="showTime" @confirm="onConfirmTime" />
          <van-field required v-model="bizFormModel.purpose" name="purpose" label="到访目的"  placeholder="到访目的" :rules="[{ required: true, message: '请输入姓名' }]" />
        </van-cell-group>

        <div class="addBox">
          <div class="addBox_title">
            <span>随行人员</span>
            <van-icon name="add-o" color="#1989fa" size="20" @click="addPeople(peopleList)" />
          </div>

          <div v-for="(model, index) in peopleList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field v-model="model.name"  name="随行人员" :placeholder="'随行人员' + (index + 1)" :rules="[{ required: peopleList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0" @click="delPersonnel(peopleList,index)" />
          </div>
        </div>

        <div class="addBox">
          <div class="addBox_title">
            <span>随行物品</span>
            <van-icon name="add-o" color="#1989fa" size="20" @click="addPeople(articleList)" />
          </div>

          <div v-for="(model, index) in articleList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field v-model="model.name"  name="随行物品" :placeholder="'随行物品' + (index + 1)" :rules="[{ required: articleList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0" @click="delPersonnel(articleList,index)" />
          </div>
        </div>

        <div class="addBox">
          <div class="addBox_title">
            <span>车辆信息</span>
            <van-icon name="add-o" color="#1989fa" size="20" @click="addPeople(carList)" />
          </div>

          <div v-for="(model, index) in carList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field v-model="model.name"  name="车辆" :placeholder="'车辆' + (index + 1)" :rules="[{ required: carList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0" @click="delPersonnel(carList,index)" />
          </div>
        </div>
      </div>
      <div class="submitbotton">
        <van-button type="danger" block native-type="button" @click="addDraft"> 保存草稿 </van-button>
        <van-button type="info" block native-type="submit"> 提交 </van-button>
      </div>
    </div>
    </van-form>
  </div>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
import {validatenull} from '@/utils/validate'
export default {
  name: 'reservationform',
  extends: BaseUI,
  components: {
  },
  data () {
    return {
      showbeVisitCompany: false, // 被访公司选择弹框
      showvVsitAdmin: false, // 被访人弹框
      showTime: false, // 到访日期
      peopleList: [{name: ''}],
      articleList: [{name: ''}],
      carList: [{name: ''}],
      companyList: [
        {name: 'xxx管理有限公司1', id: 11},
        {name: 'xxx管理有限公司2', id: 22},
      ],
      adminList: [
        {name: '老王', id: 1},
      ],
      bizFormModel: this.initFormModel(),
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    getList() {
      let people = this.peopleList.map(item => item.name)
      let article = this.articleList.map(item => item.name)
      let car = this.carList.map(item => item.name)
      this.bizFormModel.people = people.join(',')
      this.bizFormModel.article = article.join(',')
      this.bizFormModel.car = car.join(',')
    },
    // 提交
    onSubmit() {
      this.getList()
    },
    // 草稿
    addDraft(){
      this.getList()
    },
    // 到访公司
    onConfirmCompany(value) {
      this.bizFormModel.visitCompany = value.name
      this.showbeVisitCompany = false;
    },
    // 打开被访人弹框
    openAdmin() {
      if (validatenull(this.bizFormModel.visitCompany )) {
        return this.$toast('请先选择到访公司信息')
      } else {
        this.showvVsitAdmin = true
      }
    },
    // 到访人员
    onConfirmAdmin(val) {
      this.bizFormModel.visitAdmin = val.name
      this.showvVsitAdmin = false
    },
    // 日期选中
    onConfirmTime(date) {
      this.bizFormModel.time = date.getTime()
      this.bizFormModel.timebak = date.toISOString().slice(0,10)
      this.showTime = false;
    },
    // 添加
    addPeople(list) {
      list.push({name: ''})
    },
    // 删除
    delPersonnel(list,index) {
      list.splice(index, 1)
    },
    // 转数组
    getOptions(val) {
      // val = val.replace(/^\"|\"$/g,'')
      if (bo.indexOf("，") != -1) {
        bo = bo.replace(/，/ig,',')
      }
      return val.split(",")
    },
    initFormModel(This) {
      return {
        'visitCompany' : '' , // 被访公司名称
        'visitAdmin': '', // 被访问公司管理员
        'name': '', // 来访人员姓名
        'email': '', // 来访人邮箱
        'phone': '', // 来访人电话
        'company': '', // 来访人公司名称
        'purpose': '', // 到访目的
        'people': '', // 随行人
        'article': '', // 随行物品
        'car': '', //车辆信息
        'time': null, // 到访时间
        'timebak': null, // 到访时间
        'status': null, // 预约单状态
        'ifDraft': 1 // 是否草稿 0是 1否
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.addBox_title {
  display: flex;
  justify-content: space-between;
  padding: 0.42667rem 0.42667rem 0.21333rem;
  color: #969799;
  font-size: 0.37333rem;
  line-height: 0.42667rem;
}
</style>
