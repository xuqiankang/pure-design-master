import request from '@/utils/request'

export const authorizeWechat = (id) =>
    request({
        url: '/api/wechat/authorize/get/http://267f17g782.qicp.vip:29467',
        method: 'get'
    })
