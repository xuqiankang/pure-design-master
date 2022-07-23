const _import = require('../_import_' + process.env.NODE_ENV)
export default [
  {
    path: '/myVisitors',
    name: 'myVisitors',
    component: _import('company/myVisitors/index'),
    meta: { title: '我的来访'},
  },
  {
    path: '/myVisitors',
    name: 'myVisitors',
    component: _import('company/myVisitors/index'),
    meta: { title: '往期来访'}
  }
]