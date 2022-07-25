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
          <van-field disabled v-model="bizFormModel.name" label="账号" name="name" placeholder="账号"  />
          <van-field required v-model="bizFormModel.nickname" label="姓名" name="nickname" placeholder="姓名" :rules="[{ required: true, message: '请输入姓名' }]" />
          <van-field required v-model="bizFormModel.phone" type="tel" name="phone" label="电话" placeholder="电话" :rules="[{ required: true, message: '请输入电话' }]" />
          <van-field v-model="bizFormModel.company" name="company" label="公司"  placeholder="公司"  />
          <van-field v-model="bizFormModel.email" name="email" label="邮箱"  placeholder="邮箱"  />
        </van-cell-group>

        <van-cell-group title="到访信息">
          <van-field required readonly clickable label="到访公司" name="picker" :value="bizFormModel.visitCompany" placeholder="请选择被访公司" @click="showbeVisitCompany = true" />
          <van-popup v-model="showbeVisitCompany" position="bottom">
          <van-picker
              :default-index="0"
              value-key="company"
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
              value-key="username"
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
import { getInfoList,saveForm } from '@/api/wechatApi.js'

export default {
  name: 'reservationform',
  extends: BaseUI,
  components: {
  },
  data () {
    return {
      allList: [],
      showbeVisitCompany: false, // 被访公司选择弹框
      showvVsitAdmin: false, // 被访人弹框
      showTime: false, // 到访日期
      peopleList: [{name: ''}],
      articleList: [{name: ''}],
      carList: [{name: ''}],
      companyList: [],
      adminList: [
        {name: '老王', id: 1},
      ],
      bizFormModel: {},
    }
  },
  created () {
    this.getInfoLists()
    this.bizFormModel = this.initFormModel(this.currentUser)
  },
  mounted () {
  },
  methods: {
    getInfoLists() {
      getInfoList().then(responseData => {
          if(responseData.code == 200) {
            this.allList = responseData.data
            this.companyList = this.allList.filter(item => item.role == 1)
            this.companyList = this.companyList.reduce(function (tempArr, item) {
                if (tempArr.findIndex((ele) => ele.company === item.company) === -1) {
                    tempArr.push(item)
                }
                return tempArr
            }, [])
          } else {
            this.$toast.fail(responseData.msg)
          }
          this.resetLoad()
        })
        .catch(error => {
          this.resetLoad()
      })
    },
    getList(type) {
      let people = this.peopleList.map(item => item.name)
      let article = this.articleList.map(item => item.name)
      let car = this.carList.map(item => item.name)
      this.bizFormModel.people = people.join(',')
      this.bizFormModel.article = article.join(',')
      this.bizFormModel.car = car.join(',')
      this.save(this.bizFormModel,type)
    },
    // 提交
    onSubmit() {
      console.log(1);
      this.getList()
    },
    // 草稿
    addDraft(){
      console.log(0);
      this.bizFormModel.ifDraft = '0'
      this.getList('0')
    },
    save(form,type) {
      saveForm(form).then(responseData => {
        if(responseData.code == 200) {
          if (type) {
            this.$toast.success('保存成功')
          } else {
            this.$toast.success('提交成功')
          }
          this.$router.go(-1)
        } else {
          this.$toast.fail(responseData.msg)
        }
        this.resetLoad()
      })
      .catch(error => {
        this.resetLoad()
    })
    },
    // 到访公司
    onConfirmCompany(value) {
      this.bizFormModel.visitCompany = value.company
      this.adminList = this.allList.filter(item => item.company == value.company)
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
      this.bizFormModel.visitAdmin = val.username
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
      if (bo.indexOf("，") != -1) {
        bo = bo.replace(/，/ig,',')
      }
      return val.split(",")
    },
    initFormModel(This) {
      return {
        'id': '',
        'visitCompany' : '' , // 被访公司名称
        'visitAdmin': '', // 被访问公司管理员
        'name':  validatenull(This) || validatenull(This.username) ? '' : This.username, // 来访人员姓名-账号
        'nickname': validatenull(This) || validatenull(This.nickname) ? '' : This.nickname, // 来访人员昵称
        'email':  validatenull(This) || validatenull(This.email) ? '' : This.email, // 来访人邮箱
        'phone':  validatenull(This) || validatenull(This.phone) ? '' : This.phone, // 来访人电话
        'company':  validatenull(This) || validatenull(This.company) ? '' : This.company, // 来访人公司名称

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
