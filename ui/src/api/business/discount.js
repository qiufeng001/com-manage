import request from '@/utils/request'

// 查询折扣列表
export function listDiscount(query) {
  return request({
    url: '/business/discount/list',
    method: 'get',
    params: query
  })
}

// 查询折扣详细
export function getDiscount(id) {
  return request({
    url: '/business/discount/' + id,
    method: 'get'
  })
}

// 新增折扣
export function addDiscount(data) {
  return request({
    url: '/business/discount',
    method: 'post',
    data: data
  })
}

// 修改折扣
export function updateDiscount(data) {
  return request({
    url: '/business/discount',
    method: 'put',
    data: data
  })
}

// 删除折扣
export function delDiscount(id) {
  return request({
    url: '/business/discount/' + id,
    method: 'delete'
  })
}

// 导出折扣
export function exportDiscount(query) {
  return request({
    url: '/business/discount/export',
    method: 'get',
    params: query
  })
}


// 用户状态修改
export function changeStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/business/discount/updateStatus',
    method: 'put',
    data: data
  })
}
