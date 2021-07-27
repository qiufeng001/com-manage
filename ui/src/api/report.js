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

export function searchData() {
  return request({
    url: '/business/report/queryParams',
    method: 'post'
  })
}

export function timeReportQuery(query) {
  return request({
    url: '/business/report/timeReportQuery',
    method: 'post',
    data: query
  })
}
