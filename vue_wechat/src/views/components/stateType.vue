<template>
  <span>
    <div class="triangle-topright" :class="state_Obj[row.state]"></div>
    <div class="triangle-rotate">{{state_ObjName[row.state]}}</div>
    <div class="backImgs"></div>
  </span>
</template>

<script>
export default {
  props: {
    row: {
      type: Object
    }
  },
  data () {
   return {
      state_Obj: {
        '001': 'unconfirmed',
        '002': 'confirmed',
        '003': 'expired',
        '004': 'cancele',
        '005': 'ended',
        '006': 'refused',
        '1001': 'cancele',
        '1002': 'refused',
        '2001': 'unconfirmed',
        '2002': 'confirmed',
        '2003': 'expired',
        '2004': 'ended',
        '2005': 'ended',
        '2006': 'refused',
      },
      state_ObjName: {
        '001': '待确认',
        '002': '已确认',
        '003': '已到期',
        '004': '已取消',
        '005': '已结束',
        '006': '已拒绝',
        '1001': '审核中',
        '1002': '未通过',
        '2001': '待接受',
        '2002': '已接受',
        '2003': '已到期',
        '2004': '未来访',
        '2005': '已结束',
        '2006': '已拒绝',
      },
   }
  }
}
</script>

<style lang="scss" scoped>
// 未确认
.unconfirmed {
  border-top: 1.9rem solid #5E6A78;
}
// 已确认
.confirmed {
  border-top: 1.9rem solid #2A67FE;
}
.expired {
  border-top: 1.9rem solid #485472;
}
.cancele {
  border-top: 1.9rem solid #F9C95E;
}
.ended {
  border-top: 1.9rem solid #67c23a;
}
.refused {
  border-top: 1.9rem solid #FD325C;
}
</style>