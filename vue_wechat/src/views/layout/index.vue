<template>
  <div>
    <router-view></router-view>
     <van-tabbar route safe-area-inset-bottom>
      <van-tabbar-item icon="wap-home-o" to="/">首页</van-tabbar-item>
      <van-tabbar-item icon="manager-o" to="/mine">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: 'layout'
}
</script>

<style>

</style>
