import request from '@/utils/request'

export const getByOpenId = (id) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/byOpenid/' + id,
        method: 'get'
    })

export const getVstPersonnalInfoById = (id) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/' + id,
        method: 'get'
    })

export const listVstPersonnalInfoPage = (search) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/list',
        method: 'post',
        data: search
    })

export const listVstPersonnalInfoAll = (search) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/listAll',
        method: 'post',
        data: search
    })


export const saveVstPersonnalInfo = (vstPersonnalInfo) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/save',
        method: 'post',
        data: vstPersonnalInfo
    })

export const deleteVstPersonnalInfo = (vstPersonnalInfo) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/delete',
        method: 'post',
        data: vstPersonnalInfo
    })

export const bulkInsertVstPersonnalInfo = (vstPersonnalInfos) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/bulkInsert',
        method: 'post',
        data: vstPersonnalInfos
    })

export const bulkUpdateVstPersonnalInfo = (vstPersonnalInfos) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/bulkUpdate',
        method: 'post',
        data: vstPersonnalInfos
    })

export const bulkDeleteVstPersonnalInfo = (vstPersonnalInfos) =>
    request({
        url: '/visitor/vst/vstPersonnalInfo/bulkDelete',
        method: 'post',
        data: vstPersonnalInfos
    })

