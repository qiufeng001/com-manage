package com.manage.business.dto;

public class OrderReportDto {
    private String shopName;

    /** 总应收额 */
    private Float totalAmount;

    /** 总实际收额 */
    private Float paidInAmount;

    /** 总优惠额 */
    private Float discountAmount;

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
}
