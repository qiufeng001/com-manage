package com.manage.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.manage.common.core.annotation.Excel;
import com.manage.common.core.core.domain.BaseEntity;

import java.util.List;

/**
 * 订单对象 t_order
 *
 * @author zhong.h
 * @date 2021-07-20
 */
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderNo;

    /**
     * 门店id
     */
    @Excel(name = "门店id")
    private Long shopId;

    private String shopName;

    /**
     * 订单商品总数
     */
    @Excel(name = "订单商品总数")
    private Integer total;

    /**
     * 订单实际收额
     */
    @Excel(name = "订单实际收额")
    private Float paidInAmount;

    /**
     * 订单总额
     */
    @Excel(name = "订单总额")
    private Float totalAmount;

    /**
     * 折扣方案
     */
    @Excel(name = "折扣方案")
    private Long discountId;

    private String discountName;

    /**
     * 收款人
     */
    @Excel(name = "收款人")
    private String payee;

    /**
     * 状态 1：有效订单 0：无效订单
     */
    @Excel(name = "订单状态")
    private Integer status;


    /**
     * 成本价格
     */
    @Excel(name = "成本价")
    private Float costAmount;

    /**
     * 净利润
     */
    private Float netProfit;

    /**
     * 订单明细
     */
    private List<OrderDetail> details;

    private Integer goodsNum;

    private Float preferentialAmouont;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setPaidInAmount(Float paidInAmount) {
        this.paidInAmount = paidInAmount;
    }

    public Float getPaidInAmount() {
        return paidInAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Integer getGoodsNum() {
        return this.getDetails() == null ? 0 : this.getDetails().size();
    }

    public Float getPreferentialAmouont() {
        return preferentialAmouont;
    }

    public void setPreferentialAmouont(Float preferentialAmouont) {
        this.preferentialAmouont = preferentialAmouont;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Float costAmount) {
        this.costAmount = costAmount;
    }

    public Float getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Float netProfit) {
        this.netProfit = netProfit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("shopId", getShopId())
                .append("total", getTotal())
                .append("paidInAmount", getPaidInAmount())
                .append("totalAmount", getTotalAmount())
                .append("discountId", getDiscountId())
                .append("payee", getPayee())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
