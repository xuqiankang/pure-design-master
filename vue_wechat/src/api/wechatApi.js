import request from '@/utils/request'

// 登录
export const login = (data) =>
  request({
      url: '/user/login',
      method: 'post',
      data
  })

// 修改密码
export const password = (data) =>
  request({
      url: '/user/password',
      method: 'post',
      data
  })
  
// 获取用户信息
export const getUser = (name) => 
  request({
    url: '/user/username/' + name,
    method: 'get',
  })
  
// 修改用户信息
export const editUser = (data) => 
  request({
    url: '/user',
    method: 'post',
    data
  })

// 新建管理员、更新管理员信息
export const saveAdmin = (data) => 
  request({
    url: '/user/username/save',
    method: 'post',
    data
  })

// 查询公司信息
export const getInfoList = () => 
  request({
    url: '/user/getInfoList',
    method: 'get'
  })

// 新增、更新预约表单
export const saveForm = (data) => 
  request({
    url: '/order/save',
    method: 'post',
    data
  })

// 公司管理员查看 预约单
export const getOrderInfo = (username) => 
  request({
    url: '/order/getOrderInfo/' + username,
    method: 'get'
  })
// 游客查看 预约单
export const getOrderInfoPer = (username) => 
  request({
    url: '/order/getOrderInfoPer/' + username,
    method: 'get'
  })

// 游客\管理员删除 预约单
export const delOrderInfoPer = (id) => 
  request({
    url: '/order/deleteOrder/' + id,
    method: 'get'
  })

// 管理员 查看认证单
export const getApply = () => 
  request({
    url: '/user/getApply',
    method: 'get'
  })







    