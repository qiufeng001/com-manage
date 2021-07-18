package com.manage.web.controller.business;

import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.business.service.ITDiscountService;

/**
 * 折扣Controller
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/business/discount")
public class TDiscountController extends BaseController {

    @Autowired
    private ITDiscountService tDiscountService;

    @Override
    protected IService getService() {
        return tDiscountService;
    }
}
