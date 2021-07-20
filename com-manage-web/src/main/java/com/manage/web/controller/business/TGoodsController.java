package com.manage.web.controller.business;

import java.util.List;

import com.manage.business.domain.TSupplier;
import com.manage.business.service.ITSupplierService;
import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.service.IService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.common.core.annotation.Log;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.enums.BusinessType;
import com.manage.business.domain.TGoods;
import com.manage.business.service.ITGoodsService;
import com.manage.common.core.utils.poi.ExcelUtil;
import com.manage.common.core.core.page.TableDataInfo;

/**
 * 商品Controller
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/business/goods")
public class TGoodsController extends BaseController<TGoods, Long> {

    @Autowired
    private ITGoodsService tGoodsService;
    @Autowired
    private ITSupplierService supplierService;

    @Override
    protected IService getService() {
        return tGoodsService;
    }

    /**
     * 获取商品详细信息
     */
    @GetMapping(value = "/listSupplier")
    public AjaxResult listSupplier() {
        return AjaxResult.success(supplierService.selectList(new TSupplier()));
    }
}
