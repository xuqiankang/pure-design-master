import axios from 'axios'
import { getToken, setToken, removeToken ,removeSessionToken} from "./uToken";
import { Notify, Dialog } from 'vant';
// 创建axios实例
const service = axios.create({
  baseURL: "http://localhost:9090",

  // 请求超时时间
  timeout: 40000
})

// request拦截器
service.interceptors.request.use(config => {
  // Do something before request is sent
  if (getToken('token')) {
    // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
    config.headers['token'] = getToken('token')
  }
  return config
}, error => {
  // Do something with request error
  console.error(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    if (response.status==200){
      if (response.data.code == 20011){
        removeToken('token')
        removeSessionToken('user')
        Dialog.alert({
          title: '提示',
          message: '您长时间未操作，请重新授权登录',
        }).then(() => {
          //window.location.href = '/demo'
        });
      }
      return response.data;
    }
    return response.data;
  },
  error => {
    console.error(error.response ? error.response : error)
    let errorData = {
      type: 'error',
      code: error.response && error.response.status ? error.response.status: '1',
      msg: error.message ? error.message: '未知的错误',
      data: error.response && error.response.data ? error.response.data: error
    }

    // 工作流异常处理
    if(error.response && error.response.data && error.response.data.message) {
      errorData.msg = error.response.data.message
    }

    return errorData
  })

export default service
