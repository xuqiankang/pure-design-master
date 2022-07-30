<template>
<!-- 访客预约表单 -->
  <div class="reservationform" >
    <van-form @submit="onSubmit">
    <div class="page-container">
      <div class="headerFiex">
        <van-nav-bar title="预约单" left-arrow @click-left="goBack" />
      </div>
      <div class="scroll">
        <div class="addBox_title">
          <span>{{type == 'company' ? '预约人信息' : '个人信息'}}</span>
          <span v-if="bizFormModel.status">
            预约单状态:
            <van-tag v-if="bizFormModel.status == 1" type="danger" size="medium" style="margin: 2px 4px">草稿</van-tag>
            <van-tag v-if="bizFormModel.status == 2" type="warning" size="medium" style="margin: 2px 4px">待审核</van-tag>
            <van-tag v-if="bizFormModel.status == 3" type="success" size="medium" style="margin: 2px 4px">{{type == 'company' ? '已审核' : '已通过'}}</van-tag>
            <van-tag v-if="bizFormModel.status == 4" type="danger" size="medium" style="margin: 2px 4px">{{type == 'company' ? '已拒绝' : '未通过'}}</van-tag>
            <van-tag v-if="bizFormModel.status == 5" type="danger" size="medium" style="margin: 2px 4px">已过期</van-tag>
          </span>
       </div>
        <van-field :readonly="enable" disabled v-model="bizFormModel.name" label="账号" name="name" placeholder="账号"  />
        <van-field :readonly="enable" required v-model="bizFormModel.nickname" label="姓名" name="nickname" placeholder="姓名" :rules="[{ required: true, message: '请输入姓名' }]" />
        <van-field :readonly="enable" required v-model="bizFormModel.phone" type="tel" name="phone" label="电话" placeholder="电话" :rules="[{ required: true, message: '请输入电话' }]" />
        <van-field :readonly="enable" v-model="bizFormModel.company" name="company" label="公司"  placeholder="公司"  />
        <van-field :readonly="enable" v-model="bizFormModel.email" name="email" label="邮箱"  placeholder="邮箱"  />

        <van-cell-group :title="type == 'company' ? '预约人到访信息' : '到访信息'">
          <van-field required readonly clickable label="到访公司" name="picker" :value="bizFormModel.visitCompany" placeholder="请选择被访公司" @click="openCompany" />
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

          <van-field required readonly clickable label="到访人员" name="picker" :value="bizFormModel.visitNickname" placeholder="请选择被访人员" @click="openAdmin" />
          <van-popup v-model="showvVsitAdmin" position="bottom">
            <van-picker
              :default-index="0"
              value-key="nickname"
              show-toolbar
              :columns="adminList"
              @confirm="onConfirmAdmin"
              @cancel="showvVsitAdmin = false"
            />
          </van-popup>

          <van-field required readonly clickable label="到访日期" name="calendar" :value="bizFormModel.timebak" placeholder="到访日期" :rules="[{ required: true, message: '点击选择到访日期' }]" @click="openTime"  />
          <van-calendar v-model="showTime" @confirm="onConfirmTime" />
          <van-field required :readonly="enable" v-model="bizFormModel.purpose" name="purpose" label="到访目的"  placeholder="到访目的" :rules="[{ required: true, message: '请输入姓名' }]" />
        </van-cell-group>

        <div class="addBox">
          <div class="addBox_title">
            <span>随行人员</span>
            <van-icon name="add-o" color="#1989fa" size="20" v-if="!enable" @click="addPeople(peopleList)" />
          </div>

          <div v-for="(model, index) in peopleList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field :readonly="enable" v-model="model.name"  name="随行人员" :placeholder="'随行人员' + (index + 1)" :rules="[{ required: peopleList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0 && !enable" @click="delPersonnel(peopleList,index)" />
          </div>
        </div>

        <div class="addBox">
          <div class="addBox_title">
            <span>随行物品</span>
            <van-icon name="add-o" color="#1989fa" size="20" v-if="!enable" @click="addPeople(articleList)" />
          </div>

          <div v-for="(model, index) in articleList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field :readonly="enable" v-model="model.name"  name="随行物品" :placeholder="'随行物品' + (index + 1)" :rules="[{ required: articleList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0 && !enable" @click="delPersonnel(articleList,index)" />
          </div>
        </div>

        <div class="addBox">
          <div class="addBox_title">
            <span>车辆信息</span>
            <van-icon name="add-o" color="#1989fa" size="20" v-if="!enable" @click="addPeople(carList)" />
          </div>

          <div v-for="(model, index) in carList" :key="index" class="cardPopup-content">
            <div class="cardPopup-content-right">
              <van-field :readonly="enable" v-model="model.name"  name="车辆" :placeholder="'车辆' + (index + 1)" :rules="[{ required: carList.length == 1 ? false: true, message: '' }]" />
            </div>
            <van-icon name="close" size="20" color="#ee0a24" style="margin-right: 10px;" v-if="index != 0 && !enable" @click="delPersonnel(carList,index)" />
          </div>
        </div>
      </div>
      <div class="submitbotton" v-if="type == 'company'">
        <van-button type="danger" v-if="bizFormModel.status == '2'" block @click="edit('4')" native-type="button"> 拒绝 </van-button>
        <van-button type="info" v-if="bizFormModel.status == '2'" block @click="edit('3')" native-type="button"> 同意 </van-button>
        <van-button type="danger" v-if="bizFormModel.status == '4' || bizFormModel.status == '5'" block @click="deletes" native-type="button"> 删除 </van-button>
      </div>
       <div class="submitbotton" v-if="type == 'user'">
        <van-button type="danger" v-if="bizFormModel.status == '1'" block @click="deletes" native-type="button"> 删除 </van-button>
        <van-button type="info" v-if="bizFormModel.status == '1'" block native-type="submit"> 提交 </van-button>
        <!-- <van-button type="danger" v-if="bizFormModel.status == '4'" block native-type="button" @click="addDraft"> 保存草稿 </van-button> -->
        <van-button type="info" v-if="bizFormModel.status == '4'" block native-type="button" @click="subS"> 再次预约 </van-button>
      </div>

      <div class="submitbotton" v-if="type == ''">
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
import { getInfoList, saveForm, delOrderInfoPer} from '@/api/wechatApi.js'

export default {
  name: 'reservationform',
  extends: BaseUI,
  components: {
  },
  computed: {
    enable: function () {
      console.log(this.bizFormModel.status);
      if (this.bizFormModel.status == '1' || this.bizFormModel.status == '4' || this.bizFormModel.status == null) {
        return false;
      } else {
        return true;
      }
    }
  },
  data () {
    return {
      type: '',
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
    if (this.$route.query.form) {
      this.bizFormModel = this.$route.query.form
      this.type = this.$route.query.type
      let model = this.bizFormModel
      let date = model.time 
      this.bizFormModel.timebak = date ? date.slice(0,10) : null
      this.peopleList = this.getOptions(model.people)
      this.articleList=  this.getOptions(model.article)
      this.carList =  this.getOptions(model.car)
    } else {
      this.bizFormModel = this.initFormModel(this.currentUser)
    }
  },
  mounted () {
  },
  methods: {
    getInfoLists() {
      getInfoList().then(responseData => {
          if(responseData.code == 200) {
            this.allList = responseData.data
            console.log(this.companyList);
            this.companyList = this.allList.reduce(function (tempArr, item) {
                if (tempArr.findIndex((ele) => ele.company === item.company) === -1) {
                    tempArr.push(item)
                }
                return tempArr
            }, [])

            console.log(this.companyList);
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
    // 再次预约
    subS() {
      this.bizFormModel.id = null
      this.bizFormModel.ifDraft = '1'
      this.bizFormModel.status = '2'
      this.getList()
    },
    // 提交
    onSubmit() {
      this.bizFormModel.ifDraft = '1'
      this.bizFormModel.status = '2'
      this.getList()
    },
    // 草稿
    addDraft(){
      this.bizFormModel.ifDraft = '0'
      this.bizFormModel.status = '1'
      this.getList('0')
    },
    // 审核
    edit(val) {
      this.bizFormModel.status = val
      this.save(this.bizFormModel)
    },
    // 删除
    deletes() {
       delOrderInfoPer(this.bizFormModel.id).then(res => {
        if(res.code == 200) {
          this.$toast.success('操作成功')
          // this.getlist()
          this.$router.go(-1)
        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      }).catch(error => {
        this.resetLoad()
        this.outputError(error)
      })
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
    // 打开公司弹框
    openCompany() {
      if (this.enable) return
      this.showbeVisitCompany = true
    },
    // 到访公司
    onConfirmCompany(value) {
      this.bizFormModel.visitCompany = value.company
      this.adminList = this.allList.filter(item => item.company == value.company)
      this.showbeVisitCompany = false;
    },
    // 打开日历弹框
    openTime() {
      if (this.enable) return
      this.showTime = true
    },
    // 打开被访人弹框
    openAdmin() {
      if (this.enable) return
      if (validatenull(this.bizFormModel.visitCompany )) {
        return this.$toast('请先选择到访公司信息')
      } else {
        this.showvVsitAdmin = true
      }
    },
    // 到访人员
    onConfirmAdmin(val) {
      this.bizFormModel.visitAdmin = val.username
      this.bizFormModel.visitNickname = val.nickname
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
      if (val.indexOf("，") != -1) {
        val = val.replace(/，/ig,',')
      }
      let list = val.split(",")
      let newList = []
      list.forEach(item => {
        const obj = {
          name : item
        }
        newList.push(obj)
      })
      return newList
    },
    initFormModel(This) {
      return {
        'id': '',
        'visitCompany' : '' , // 被访公司名称
        'visitAdmin': '', // 被访问公司管理员
        'visitNickname': '',
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
