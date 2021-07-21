import request from '@/utils/request'

// 查询折扣方案
export function getInfo() {
  return request({
    url: '/business/order/getInfo',
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/business/order/addOrder',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/business/order/updateOrder',
    method: 'put',
    data: data
  })
}
