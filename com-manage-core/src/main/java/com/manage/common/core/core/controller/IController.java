package com.manage.common.core.core.controller;

import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.core.domain.IEntity;

/**
 * IController
 *
 * @author zhong.h
 * @date 2019/11/1
 */
public interface IController<T extends IEntity, K> {

    /**
     *  列表接口
     */
    public TableDataInfo list(T entity);

    /**
     * 导出
     * @param entity 数据实体
     * @return 执行结果
     */
    public AjaxResult export(T entity);

    /**
     * 获取信息
     * @param id
     * @return
     */
    public AjaxResult getInfo(Long id);

    /**
     * 新增
     * @param entity
     * @return
     */
    public AjaxResult add(T entity);

    /**
     *  修改
     * @param entity
     * @return
     */
    public AjaxResult edit(T entity);

    /**
     * 删除
     * @param ids
     * @return
     */
    public AjaxResult remove(Long[] ids);


}
