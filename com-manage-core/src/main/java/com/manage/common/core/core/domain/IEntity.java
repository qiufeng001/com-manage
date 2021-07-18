package com.manage.common.core.core.domain;

import java.util.Date;

/**
 * IUser
 *
 * @author zhong.h
 * @date 2019/10/31
 */
public interface IEntity<K> {
    // id 主键
    K getId();

    void setId(K val);

    // 名称
    void setName(String name);

    String getName();
    // 创建时间
    Date getCreateTime();

    void setCreateTime(Date date);
    // 修改时间
    Date getUpdateTime();

    void setUpdateTime(Date date);

    String getCreateBy();

    void setCreateBy(String createUser);

    String getUpdateBy();

    void setUpdateBy(String updateUser);

}
