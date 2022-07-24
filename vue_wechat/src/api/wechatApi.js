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






    