package com.manage.business.domain;

import com.manage.common.core.core.domain.BaseEntity;

public class OrderDetail extends BaseEntity {

    /** 商品id */
    private Long goodsId;

    private TGoods goods;

    /**订单编号*/
    private String orderNo;

    /** 订单数量 */
    private Integer goodsNum;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public TGoods getGoods() {
        return goods;
    }

    public void setGoods(TGoods goods) {
        this.goods = goods;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}
