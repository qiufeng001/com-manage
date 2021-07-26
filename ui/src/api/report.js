import request from '@/utils/request';


export function netProfitReport(query) {
  return request({
    url: '/business/report/netProfitReport',
    method: 'post',
    data: query
  })
}

export function salesReport(query) {
  return request({
    url: '/business/report/salesReport',
    method: 'post',
    data: query
  })
}
