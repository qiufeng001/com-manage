package com.manage.common.core.core.mapper;

import com.manage.common.core.core.domain.IEntity;

import java.util.List;

/**
 * IRepository 底层基础接口
 *
 * @author zhong.h
 * @date 2019/10/31
 */
public interface IMapper<T extends IEntity, K> {

    /**
     * 查询
     *
     * @param id ID
     * @return
     */
    public T selectById(Long id);

    /**
     * 查询列表
     *
     * @param entity
     * @return 集合
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
     * 修改
     *
     * @param entity
     * @return 结果
     */
    public int update(T entity);

    /**
     * 删除折扣
     *
     * @param id 折扣ID
     * @return 结果
     */
    public int deleteById(Long id);

    /**
     * 批量删除折扣
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteByIds(Long[] ids);
}