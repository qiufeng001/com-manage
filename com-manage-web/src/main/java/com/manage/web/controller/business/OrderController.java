package com.manage.web.controller.business;

import java.util.*;

import com.manage.business.domain.TDiscount;
import com.manage.business.domain.TGoods;
import com.manage.business.service.ITDiscountService;
import com.manage.business.service.ITGoodsService;
import com.manage.common.core.annotation.Log;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.core.domain.entity.TShop;
import com.manage.common.core.enums.BusinessType;
import com.manage.domain.service.ITShopService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private ITGoodsService goodsService;

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

    @Log(title = "新增数据", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addOrder")
    public AjaxResult addOrder(@RequestBody Order entity) {
        Map<String, Object> result = new HashMap<>();
        try {
            result =  service.addOrder(entity);
        }catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error((String) result.get("errMsg"));
        }
        if((Integer) result.get("code") != 200) {
            return AjaxResult.error((String) result.get("errMsg"));
        }
        return AjaxResult.success();
    }

    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("goods", goodsService.selectList(new TGoods()));
        TDiscount discount = new TDiscount();
        discount.setStatus("1");
        List<TDiscount> discounts = discountService.selectList(discount);
        result.put("discounts",  discounts == null ? new ArrayList<>() : Arrays.asList(discounts.get(0)));
        return AjaxResult.success(result);
    }
}
