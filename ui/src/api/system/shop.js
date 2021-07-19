import request from '@/utils/request'

// 查询门店列表
export function listShop(query) {
  return request({
    url: '/system/shop/list',
    method: 'get',
    params: query
  })
}

// 查询门店详细
export function getShop(id) {
  return request({
    url: '/system/shop/' + id,
    method: 'get'
  })
}

// 新增门店
export function addShop(data) {
  return request({
    url: '/system/shop',
    method: 'post',
    data: data
  })
}

// 修改门店
export function updateShop(data) {
  return request({
    url: '/system/shop',
    method: 'put',
    data: data
  })
}

// 删除门店
export function delShop(id) {
  return request({
    url: '/web/shop/' + id,
    method: 'delete'
  })
}

// 导出门店
export function exportShop(query) {
  return request({
    url: '/system/shop/export',
    method: 'get',
    params: query
  })
}
