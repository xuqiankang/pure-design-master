import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'

// 设置 rem 基准值
import 'amfe-flexible';
// 引入 vant 组件库
import '@/utils/vant'
import '@/assets/main.scss'
import '@/utils/permission'
/* 注入全局脚本 */
import * as filters from './utils/filters'
import '@/components/common/index'

Vue.config.productionTip = false

/* 注入全局filter */
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})


let vm = new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})

export default vm
