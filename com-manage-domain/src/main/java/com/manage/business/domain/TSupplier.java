package com.manage.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.manage.common.core.annotation.Excel;
import com.manage.common.core.core.domain.BaseEntity;

/**
 * 供应商对象 t_supplier
 *
 * @author zhong.h
 * @date 2021-07-17
 */
public class TSupplier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商名称")
    private String name;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String contactPeople;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 联系地址
     */
    @Excel(name = "联系地址")
    private String address;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
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
                .append("id", getId())
                .append("name", getName())
                .append("contactPeople", getContactPeople())
                .append("phone", getPhone())
                .append("address", getAddress())
                .toString();
    }
}
