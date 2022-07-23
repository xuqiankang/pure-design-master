const _import = require('../_import_' + process.env.NODE_ENV)
export default [
  {
    path: '/login',
    name: 'login',
    component: _import('login/index'),
    meta: { title: '新建公司管理员'},
  },
  {
    path: '/login',
    name: 'login',
    component: _import('login/index'),
    meta: { title: '管理员审核'}
  },
  {
    path: '/login',
    name: 'login',
    component: _import('login/index'),
    meta: { title: '管理员信息维护'}
  }
]