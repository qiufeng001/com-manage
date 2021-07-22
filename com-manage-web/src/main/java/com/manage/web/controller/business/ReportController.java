package com.manage.web.controller.business;

import com.manage.business.service.IReportService;
import com.manage.common.core.annotation.Log;
import com.manage.common.core.core.controller.AbstractController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/business/report")
public class ReportController extends AbstractController {

    @Autowired
    private IReportService reportService;

    /**
     * 修改折扣
     */
    @Log(title = "修改数据", businessType = BusinessType.UPDATE)
    @PostMapping("orderReport")
    public Map<String, Object> orderReport(@RequestBody Map<String, Object> params) {
        return reportService.getOrderReport(params);
    }
}
