const _import = require('../_import_' + process.env.NODE_ENV)
export default [
  {
    path: '/visitorsForm',
    name: 'visitorsForm',
    component: _import('user/visitorsForm'),
    meta: { title: '访客预约'},
  },
  {
    path: '/myReservation',
    name: 'myReservation',
    component: _import('user/myReservation'),
    meta: { title: '我的预约'},
  },
]