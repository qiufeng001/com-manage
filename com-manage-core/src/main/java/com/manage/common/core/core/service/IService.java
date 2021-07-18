package com.manage.common.core.core.service;

import com.manage.common.core.core.domain.IEntity;
import java.util.List;

/**
 * IService
 *
 * @author zhong.h
 * @date 2019/11/1
 */
public interface IService <T extends IEntity, K> {

    /**
     * 通过id查询
     *
     * @param id 折扣ID
     * @return 折扣
     */
    public T selectById(Long id);

    /**
     * 查询
     *
     * @param entity
     * @return 折扣集合
     */
    public List<T> selectList(T entity);

    /**
     * 新增
     *
     * @param entity
     * @return 结果
     */
    public int insert(T entity);

    /**
     * 修改折扣
     *
     * @param entity
     * @return 结果
     */
    public int update(T entity);

    /**
     * 批量删除
     *
     * @param ids 需要删除的ID
     * @return 结果
     */
    public int deleteByIds(Long[] ids);

    /**
     * 删除信息
     *
     * @param id ID
     * @return 结果
     */
    public int deleteById(Long id);
}