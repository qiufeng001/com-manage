package com.manage.business.service;


import com.manage.business.domain.Order;
import com.manage.business.domain.OrderDetail;
import com.manage.common.core.core.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 订单Service接口
 *
 * @author zhong.h
 * @date 2021-07-20
 */
public interface IOrderService extends IService<Order, Long> {

    public Map<String, Object> addOrder(Order entity) throws Exception;

    public Map<String, Object> updateOrder(Order entity) throws Exception;

    public Order getDetails(Order order);
}
