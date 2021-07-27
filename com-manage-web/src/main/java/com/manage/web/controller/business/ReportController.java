package com.manage.web.controller.business;

import com.manage.business.service.IReportService;
import com.manage.common.core.core.controller.AbstractController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.domain.entity.TShop;
import com.manage.common.core.utils.SecurityUtils;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/business/report")
public class ReportController extends AbstractController {

    @Autowired
    private IReportService reportService;
    @Autowired
    private ITShopService shopService;

    /**
     * 查询条件加载
     */
    @PostMapping("queryParams")
    public AjaxResult queryParams() {

        AjaxResult ajaxResult = new AjaxResult();
        TShop shop = new TShop();
        Long shopId = SecurityUtils.getShopId();
        if(shopId != null) {
            shop.setId(shopId);
        }
        ajaxResult.put("shops", shopService.selectList(shop));
        ajaxResult.put("code", 200);
        return ajaxResult;
    }

    /**
     * 门店净利润
     */
    @PostMapping("netProfitReport")
    public Map<String, Object> netProfitReport(@RequestBody Map<String, Object> params) {
        return reportService.getOrderReport(params);
    }

    /**
     * 利润和销量
     */
    @PostMapping("salesReport")
    public Map<String, Object> salesReport(@RequestBody Map<String, Object> params) {
        return reportService.salesReport(params);
    }

    /**
     * 报表
     */
    @PostMapping("timeReportQuery")
    public Map<String, Object> timeReportQuery(@RequestBody Map<String, Object> params) {
        return reportService.timeReportQuery(params);
    }
}
