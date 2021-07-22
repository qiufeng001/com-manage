import request from '@/utils/request'

// 获取订单报表
export function reportData(query) {
  return request({
    url: '/business/report/orderReport',
    method: 'post',
    data: query
  })
}
