package com.manage.business.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.manage.common.core.annotation.Excel;
import com.manage.common.core.core.domain.BaseEntity;

/**
 * 折扣对象 t_discount
 *
 * @author zhong.h
 * @date 2021-07-17
 */
public class TDiscount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 折扣名称
     */
    @Excel(name = "折扣名称")
    private String name;

    /**
     * 折扣率
     */
    @Excel(name = "折扣率")
    private Float discountRate;

    @Excel(name = "状态")
    private String status;

    /**
     * 生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectTime;

    /**
     * 失效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invalidTime;

    /**
     * $column.columnComment
     */
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDiscountRate(Float discountRate) {
        this.discountRate = discountRate;
    }

    public Float getDiscountRate() {
        return discountRate;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name", getName())
                .append("discountRate", getDiscountRate())
                .append("effectTime", getEffectTime())
                .append("invalidTime", getInvalidTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("id", getId())
                .toString();
    }
}
