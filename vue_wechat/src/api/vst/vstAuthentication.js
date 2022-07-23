import request from '@/utils/request'

export const getVstAuthenticationById = (id) =>
    request({
        url: '/visitor/vst/vstAuthentication/' + id,
        method: 'get'
    })

export const listVstAuthenticationPage = (search) =>
    request({
        url: '/visitor/vst/vstAuthentication/list',
        method: 'post',
        data: search
    })

export const listVstAuthenticationAll = (search) =>
    request({
        url: '/visitor/vst/vstAuthentication/listAll',
        method: 'post',
        data: search
    })


export const saveVstAuthentication = (vstAuthentication) => 
    request({
        url: '/visitor/vst/vstAuthentication/save',
        method: 'post',
        data: vstAuthentication
    })
  
export const deleteVstAuthentication = (vstAuthentication) =>
    request({
        url: '/visitor/vst/vstAuthentication/delete',
        method: 'post',
        data: vstAuthentication
    })
    
export const bulkInsertVstAuthentication = (vstAuthentications) =>
    request({
        url: '/visitor/vst/vstAuthentication/bulkInsert',
        method: 'post',
        data: vstAuthentications
    })
    
export const bulkUpdateVstAuthentication = (vstAuthentications) =>
    request({
        url: '/visitor/vst/vstAuthentication/bulkUpdate',
        method: 'post',
        data: vstAuthentications
    })

export const bulkDeleteVstAuthentication = (vstAuthentications) =>
    request({
        url: '/visitor/vst/vstAuthentication/bulkDelete',
        method: 'post',
        data: vstAuthentications
    })
    
