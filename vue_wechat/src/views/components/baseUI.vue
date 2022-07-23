<template>
  <div  style="text-align:left" >
  </div>
</template>
<script>
export default {
  name: 'BaseUI',
  
  data() {
    return {
      currentUser: {},
      // currentUser: JSON.parse(sessionStorage.getItem('user')),  // 登录用户
      loadcount: 0,   // loading加载次数
      loading: false,  // 加载标志，用与显示加载遮罩
      isAlert: false   // 已经弹出alert对话标志
    }
  },
  created() {
     this.currentUser =  {
        photo: null,
        name: '老王头',
        phone: '15234343434',
        customerName: '123',
        permission: 'user',
      }
  },
  methods: {
    setLoad() {
      this.loadcount++
      this.loading = true
    },
    resetLoad() {
      this.loadcount--
      if(this.loadcount <= 0) {
        this.loadcount = 0
        this.loading = false
      }
    },
    getAttrValue(entity, attr, javaType) {
      let pos = attr.indexOf('.')
      if(pos == -1) {
        if(javaType == "java.math.BigDecimal") {  // 金额类型，保留两位小数，千分位
          return  (entity[attr] || 0).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
        }
        return entity[attr]
      }
      return this.getAttrValue(entity[attr.substr(0, pos)], attr.substr(pos + 1), javaType)
    },
    outputError(error) {
      this.resetLoad()
      this.$toast.fail('出错了!')
    },
    showMessage(msgData) {
      if (msgData.type == 'warning' || msgData.type == 'error') {
        this.$toast.fail(msgData.msg)
      } else {
        this.$toast.success(msgData.msg)
      }
    }
  },
  watch: {
    loadcount: function(val, oldVal) {
      if(val == 0) {
        this.isAlert = false
      }
    }
  }
}
</script>
