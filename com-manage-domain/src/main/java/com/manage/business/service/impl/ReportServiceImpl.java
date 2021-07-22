package com.manage.business.service.impl;

import com.manage.business.dto.OrderReportDto;
import com.manage.business.mapper.ReportMapper;
import com.manage.business.mapper.TGoodsMapper;
import com.manage.business.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Map<String, Object> getOrderReport(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<OrderReportDto> orderReportDtos = reportMapper.getOrderReport(params);


            result.put("orderReport", orderReportDtos);
            result.put("code", 200);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("errMsg", "系统异常");
        }
        return result;
    }
}
