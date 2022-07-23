import request from '@/utils/request'

export const getPropertySetById = (id) =>
    request({
        url: '/sys/propertySet/' + id,
        method: 'get'
    })
export const listDictItemAll = (search) =>
    request({
        url: '/sys/dictItem/listAll',
        method: 'post',
        data: search
    })  