// 获取订单报表
export const getRouters = (query) => {
  return request({
    url: '/home/orderReport',
    method: 'post',
    data: query
  })
}
