package com.manage.web.controller.business;

import com.manage.business.domain.TDiscount;
import com.manage.common.core.annotation.Log;
import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.service.IService;
import com.manage.common.core.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.manage.business.service.ITDiscountService;

/**
 * 折扣Controller
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/business/discount")
public class TDiscountController extends BaseController<TDiscount, Long> {

    @Autowired
    private ITDiscountService tDiscountService;

    @Override
    protected IService getService() {
        return tDiscountService;
    }

    /**
     * 修改折扣
     */
    @Log(title = "修改数据", businessType = BusinessType.UPDATE)
    @PutMapping("updateStatus")
    public AjaxResult edit(@RequestBody TDiscount entity) {
        return toAjax(getService().update(entity));
    }
}
