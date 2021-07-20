import request from '@/utils/request'

// 查询折扣方案
export function getInfo() {
  return request({
    url: '/business/order/getInfo',
    method: 'get'
  })
}
