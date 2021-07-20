import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/business/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(id) {
  return request({
    url: '/business/order/' + id,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/business/order',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/business/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(id) {
  return request({
    url: '/business/order/' + id,
    method: 'delete'
  })
}

// 导出订单
export function exportOrder(query) {
  return request({
    url: '/business/order/export',
    method: 'get',
    params: query
  })
}

// 查询供应商
export function listShops() {
  return request({
    url: '/business/goods/listShops',
    method: 'get'
  })
}

// 查询折扣方案
export function listDiscounts() {
  return request({
    url: '/business/goods/listDiscounts',
    method: 'get'
  })
}
