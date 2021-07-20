package com.manage.web.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manage.business.domain.TDiscount;
import com.manage.business.service.ITDiscountService;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.core.domain.entity.TShop;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.service.IService;
import com.manage.business.domain.Order;
import com.manage.business.service.IOrderService;

/**
 * 订单Controller
 *
 * @author zhong.h
 * @date 2021-07-20
 */
@RestController
@RequestMapping("/business/order")
public class OrderController extends BaseController<Order, Long> {

    @Autowired
    private IOrderService service;

    @Autowired
    private ITShopService shopService;

    @Autowired
    private ITDiscountService discountService;

    @Override
    protected IService getService() {
        return service;
    }

    @Override
    public TableDataInfo list(Order entity) {
        startPage();
        List<Order> list = getService().selectList(entity);
        TableDataInfo tableDataInfo = getDataTable(list);
        Map<String, Object> fillData = new HashMap<>();

        fillData.put("shops", shopService.selectList(new TShop()));
        fillData.put("discounts", discountService.selectList(new TDiscount()));
        tableDataInfo.setFillData(fillData);
        return tableDataInfo;
    }


}
