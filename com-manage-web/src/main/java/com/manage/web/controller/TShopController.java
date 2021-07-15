package com.manage.web.controller;

import java.util.List;

import com.manage.common.core.utils.poi.ExcelUtil;
import com.manage.domain.domain.TShop;
import com.manage.domain.service.ITShopService;
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
import com.manage.common.core.core.controller.BaseController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.enums.BusinessType;
import com.manage.common.core.core.page.TableDataInfo;

/**
 * 门店Controller
 *
 * @author zhong.h
 * @date 2021-07-15
 */
@RestController
@RequestMapping("/web/shop")
public class TShopController extends BaseController {
    @Autowired
    private ITShopService tShopService;

    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('web:shop:list')")
    @GetMapping("/list")
    public TableDataInfo list(TShop tShop) {
        startPage();
        List<TShop> list = tShopService.selectTShopList(tShop);
        return getDataTable(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('web:shop:export')")
    @Log(title = "门店" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TShop tShop) {
        List<TShop> list = tShopService.selectTShopList(tShop);
        ExcelUtil<TShop> util = new ExcelUtil<TShop>(TShop.class);
        return util.exportExcel(list, "shop");
    }

    /**
     * 获取门店详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:shop:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(tShopService.selectTShopById(id));
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('web:shop:add')")
    @Log(title = "门店" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TShop tShop) {
        return toAjax(tShopService.insertTShop(tShop));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('web:shop:edit')")
    @Log(title = "门店" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TShop tShop) {
        return toAjax(tShopService.updateTShop(tShop));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('web:shop:remove')")
    @Log(title = "门店" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(tShopService.deleteTShopByIds(ids));
    }
}
