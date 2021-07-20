package com.manage.business.mapper;

import com.manage.business.domain.Order;
import com.manage.business.domain.OrderDetail;
import com.manage.common.core.core.mapper.IMapper;

/**
 * 订单Mapper接口
 *
 * @author zhong.h
 * @date 2021-07-20
 */
public interface OrderMapper extends IMapper<Order, Long> {
    int insertDetail(OrderDetail  detail);
    int deleteDetailByOrderNo(String orderNo);
}
