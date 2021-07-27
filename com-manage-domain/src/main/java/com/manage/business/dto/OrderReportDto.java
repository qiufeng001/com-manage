package com.manage.business.dto;

public class OrderReportDto {
    private String shopName;
    private String name;

    /** 总应收额 */
    private Float totalAmount;

    /** 总实际收额 */
    private Float paidInAmount;

    /** 总优惠额 */
    private Float discountAmount;

    /** 净利润 */
    private Float netProfit;

    private Integer total;

    private Float costAmount;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getPaidInAmount() {
        return paidInAmount;
    }

    public void setPaidInAmount(Float paidInAmount) {
        this.paidInAmount = paidInAmount;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Float netProfit) {
        this.netProfit = netProfit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Float costAmount) {
        this.costAmount = costAmount;
    }
}
