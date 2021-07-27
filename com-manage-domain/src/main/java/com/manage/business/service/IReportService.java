package com.manage.business.service;

import com.manage.business.dto.OrderReportDto;

import java.util.List;
import java.util.Map;

public interface IReportService {

    /**
     * 获取订单报表
     * @param params
     * @return
     */
    Map<String, Object> getOrderReport(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    Map<String, Object> salesReport(Map<String, Object> params);


    Map<String, Object> timeReportQuery(Map<String, Object> params);
}
