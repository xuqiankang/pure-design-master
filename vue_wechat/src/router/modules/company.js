const _import = require('../_import_' + process.env.NODE_ENV)
export default [
  {
    path: '/myVisitors',
    name: 'myVisitors',
    component: _import('company/myVisitors'),
    meta: { title: '我的来访'},
  },
  {
    path: '/previous',
    name: 'previous',
    component: _import('company/previous'),
    meta: { title: '往期来访'}
  }
]