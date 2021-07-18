package com.manage.common.core.core.service.impl;

import com.manage.common.core.core.domain.IEntity;
import com.manage.common.core.core.domain.BaseEntity;
import com.manage.common.core.core.mapper.IMapper;
import com.manage.common.core.core.service.IService;
import com.manage.common.core.utils.SecurityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * BaseServiceImpl
 *
 * @author zhong.h
 * @date 2019/10/31
 */
public abstract class BaseServiceImpl<T extends IEntity, K> implements IService<T, K> {

    protected Log logger = LogFactory.getLog(getClass());
    protected final Class<?> idClass = null;
    protected abstract IMapper<T, K> getMapper();


    /**
     * 查询
     *
     * @param id ID
     * @return
     */
    @Override
    public T selectById(Long id) {
        return getMapper().selectById(id);
    }

    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    @Override
    public List<T> selectList(T entity) {
        return getMapper().selectList(entity);
    }

    /**
     * 新增
     *
     * @param entity
     * @return 结果
     */
    @Override
    public int insert(T entity) {
        initEntry(entity);
        return getMapper().insert(entity);
    }

    /**
     * 修改
     *
     * @param entity
     * @return 结果
     */
    @Override
    public int update(T entity) {
        entity.setUpdateBy(SecurityUtils.getUsername());
        entity.setUpdateTime(new Date());
        return getMapper().update(entity);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的ID
     * @return 结果
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return getMapper().deleteByIds(ids);
    }

    /**
     * 删除信息
     *
     * @param id ID
     * @return 结果
     */
    @Override
    public int deleteById(Long id) {
        return getMapper().deleteById(id);
    }

    protected void initEntry(T entry) {
        BaseEntity item = (BaseEntity) entry;
        Date date = new Date();
        // TODO 获取登陆用户
        if (item.getCreateTime() == null) {
            item.setCreateTime(date);
            item.setCreateBy(SecurityUtils.getUsername());
            item.setUpdateTime(date);
            item.setUpdateBy(SecurityUtils.getUsername());
        }
        if(entry.getId() != null) {
            item.setUpdateBy(SecurityUtils.getUsername());
            item.setUpdateTime(date);
        }
    }

    @SuppressWarnings("unchecked")
    protected String generateId() {
        return  UUID.randomUUID().toString();
    }
}
