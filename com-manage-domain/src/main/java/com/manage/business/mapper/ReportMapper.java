package com.manage.business.mapper;

import com.manage.business.dto.OrderReportDto;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

    /**
     * 获取订单报表
     * @param params
     * @return
     */
    List<OrderReportDto> getOrderReport(Map<String, Object> params);

    List<OrderReportDto> getSaleReport(Map<String, Object> params);

}
