package com.manage.domain.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.manage.common.core.annotation.Excel;
import com.manage.common.core.core.domain.BaseEntity;

/**
 * 门店对象 t_shop
 *
 * @author zhong.h
 * @date 2021-07-15
 */
public class TShop extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 店铺名称
     */
    @Excel(name = "店铺名称")
    private String shopName;

    /**
     * 负责人id
     */
    @Excel(name = "负责人id")
    private Long headId;

    /**
     * 负责人名称
     */
    @Excel(name = "负责人名称")
    private String headName;

    /**
     * 店铺联系电话
     */
    @Excel(name = "店铺联系电话 ")
    private String contactPhoneNumber;

    /**
     * 店铺地址
     */
    @Excel(name = "店铺地址")
    private String address;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }

    public Long getHeadId() {
        return headId;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadName() {
        return headName;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("shopName" , getShopName())
                .append("headId" , getHeadId())
                .append("headName" , getHeadName())
                .append("contactPhoneNumber" , getContactPhoneNumber())
                .append("address" , getAddress())
                .append("createBy" , getCreateBy())
                .append("updateBy" , getUpdateBy())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .append("remark" , getRemark())
                .toString();
    }
}
