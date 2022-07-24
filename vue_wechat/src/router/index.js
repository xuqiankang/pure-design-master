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
          meta: { title: '首页', verify: true}
        },
        {
          path: '/mine',
          name: 'mine',
          component: _import('layout/mine/index'),
          meta: { title: '我的', verify: true},
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
      component: _import('personal/personal'),
      meta: { title: '个人信息', verify: true},
    },
    {
      path: '/password',
      name: 'password',
      component: _import('personal/password'),
      meta: { title: '修改密码', verify: true},
    },
    {
      path: '/authorized',
      name: 'authorized',
      component: _import('personal/authorized'),
      meta: { title: '认证管理员', verify: true},
    }
  ]
})