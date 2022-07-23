import Vue from 'vue'
import Router from 'vue-router'
import user from './modules/user.js'
import company from './modules/company.js'
import admin from './modules/admin.js'
const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
const Layout = _import('layout/index')
const Home = _import('layout/home/index')
export default new Router({
  linkActiveClass: 'active',
  routes: [
    ...user,
    ...company,
    ...admin,
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '/',
          name: 'home',
          component: Home,
          meta: { title: '首页'}
        },
        {
          path: '/mine',
          name: 'mine',
          component: _import('layout/mine/index'),
          meta: { title: '我的'},
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: _import('login/index'),
      meta: { title: '登录'},
    },
    {
      path: '/personal',
      name: 'personal',
      component: _import('personal/index'),
      meta: { title: '个人信息'},
    }
  ]
})