const _import = require('../_import_' + process.env.NODE_ENV)
export default [
  {
    path: '/addAdmin',
    name: 'addAdmin',
    component: _import('admin/addAdmin'),
    meta: { title: '新建管理员'},
  },
  {
    path: '/dele',
    name: 'dele',
    component: _import('admin/dele'),
    meta: { title: '信息维护'}
  },
  {
    path: '/check',
    name: 'check',
    component: _import('admin/check'),
    meta: { title: '审核'}
  }
]