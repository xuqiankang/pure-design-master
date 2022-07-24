/**
 * @desc 守卫路由信息，目前要求都要有授权，以 token 为准
 */
import router from '../router'
import axios from 'axios'
import {Toast} from 'vant'
// const service = axios.create({
//   baseURL: process.env.BASE_API,
//   // timeout: 10000 // request timeout
//   headers: {
//     'Content-Type': 'application/json;charset=utf-8',
//   }
// })

router.beforeEach(async (to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.meta.verify) { // 是否需要登录
    if (sessionStorage.getItem('currentUser')) {
      next()
    } else {
      next({path: '/login'})
      return
    }
  } else {
    next()
  }
})

router.afterEach((to, from) => {
  // 跳转完成
})
