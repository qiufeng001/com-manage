package com.manage.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manage.business.dto.OrderReportDto;
import com.manage.business.mapper.ReportMapper;
import com.manage.business.service.IReportService;
import com.manage.common.core.utils.SecurityUtils;
import com.manage.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;
    private final String[] timeArr = {"0-2(时)", "2-4(时)", "4-6(时)", "6-8(时)", "8-10(时)",
            "10-12(时)", "12-14(时)", "14-16(时)", "16-18(时)", "18-20(时)", "20-22(时)", "22-00(时)"};

    @Override
    public Map<String, Object> getOrderReport(Map<String, Object> params) {
        Map<String, Object> query = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        try {
            // 报表类别，总的， 门店
            String reportType = this.genReportType((String) params.get("reportType"));
            // 时间聚合
            String timeType = (String) params.get("");
            String groupBy = " group by " + reportType;
            query.put("groupBy", groupBy);
            Long shopId = SecurityUtils.getShopId();
            if (shopId != null) {
                query.put("shopId", shopId);
            }
            List<OrderReportDto> orderReportDtos = reportMapper.getOrderReport(query);
            List<JSONObject> orderReports = new ArrayList<>();
            for (OrderReportDto reportDto : orderReportDtos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", reportDto.getShopName());
                jsonObject.put("value", reportDto.getNetProfit());
                orderReports.add(jsonObject);
            }
            result.put("orderReports", orderReports);
            result.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("errMsg", "系统异常");
        }
        return result;
    }

    @Override
    public Map<String, Object> salesReport(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> query = new HashMap<>();
        List<JSONObject> saleReports = new ArrayList<>();
        List<JSONObject> profitReports = new ArrayList<>();
        List<String> names = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("0.00");
        try {
            Long shopId = SecurityUtils.getShopId();
            if (shopId != null) {
                query.put("shopId", shopId);
            }
            List<OrderReportDto> orderReportDtos = reportMapper.getSaleReport(query);
            for (OrderReportDto reportDto : orderReportDtos) {
                JSONObject saleJson = new JSONObject();
                String name = reportDto.getName();
                saleJson.put("name", name);
                saleJson.put("value", reportDto.getTotal());
                JSONObject profitJson = new JSONObject();
                profitJson.put("name", name);
                profitJson.put("value", format.format(reportDto.getNetProfit()));
                saleReports.add(saleJson);
                profitReports.add(profitJson);
                names.add(name);
            }
            result.put("saleReports", saleReports);
            result.put("profitReports", profitReports);
            result.put("names", names);
            result.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("errMsg", "系统异常");
        }
        return result;
    }

    @Override
    public Map<String, Object> timeReportQuery(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        // 实收额度
        List<JSONObject> trueAmountReports = new ArrayList<>();
        Map<String, JSONObject> tempMap = new HashMap<>();
        List<String> names = new ArrayList<>();
        try {
            List<OrderReportDto> timeReportDtos = reportMapper.timeReportQuery(params);
            for (OrderReportDto reportDto : timeReportDtos) {
                JSONObject trueAmount = new JSONObject();
                String name = reportDto.getName();
                trueAmount.put("name", name);
                trueAmount.put("value", reportDto.getPaidInAmount());
                tempMap.put(name, trueAmount);
            }

            result.put("names", timeArr);
            List<JSONObject> nullList = new ArrayList<>();

            for(int i = 0;i < timeArr.length;i++) {
                String name = timeArr[i];
                if(tempMap.get(name) != null) {
                    trueAmountReports.add(tempMap.get(name));
                }else {
                    JSONObject nullObj = new JSONObject();
                    nullObj.put("name", name);
                    nullObj.put("value", 0F);
                    trueAmountReports.add(nullObj);
                }
            }
            result.put("trueAmountReports", trueAmountReports);
            result.put("timeReportDtos", timeReportDtos);
            result.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("errMsg", "系统异常");
        }
        return result;
    }

    private String genReportType(String val) {
        switch (val) {
            case "total":
                return " shop ";
            default:
                return "";
        }
    }
}
