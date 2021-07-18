package com.manage.common.core.core.controller.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.common.core.annotation.Log;
import com.manage.common.core.constant.HttpStatus;
import com.manage.common.core.core.controller.IController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.page.PageDomain;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.core.page.TableSupport;
import com.manage.common.core.core.domain.IEntity;
import com.manage.common.core.core.service.IService;
import com.manage.common.core.enums.BusinessType;
import com.manage.common.core.utils.DateUtils;
import com.manage.common.core.utils.Helper;
import com.manage.common.core.utils.StringUtils;
import com.manage.common.core.utils.poi.ExcelUtil;
import com.manage.common.core.utils.sql.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;


/**
 * BaseController
 *
 * @author zhong.h
 * @date 2019/11/1
 */
public abstract class BaseController<T extends IEntity, K> implements IController<T, K> {

    protected abstract IService<T, K> getService();

    private Class<T> persistentClass;
    @Autowired
    protected Environment env;

    public Class<?> getPersistentClass() {
        return this.persistentClass;
    }

    public BaseController() {
        this.persistentClass = (Class<T>) Helper.getSuperClassGenricType(getClass(), 0);
        Assert.notNull(persistentClass);
    }

    /**
     * 查询列表
     */
    @PreAuthorize("@ss.hasPermi('com:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(T entity) {
        startPage();
        List<T> list = getService().selectList(entity);
        return getDataTable(list);
    }

    /**
     * 导出列表
     */
    @PreAuthorize("@ss.hasPermi('com:manage:export')")
    @Log(title = "导出数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(T entity) {
        List<T> list = getService().selectList(entity);
        ExcelUtil<T> util = new ExcelUtil<T>(persistentClass);
        return util.exportExcel(list, "discount");
    }

    /**
     * 获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('com:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(getService().selectById(id));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('com:manage:add')")
    @Log(title = "新增数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody T entity) {
        return toAjax(getService().insert(entity));
    }

    /**
     * 修改折扣
     */
    @PreAuthorize("@ss.hasPermi('com:manage:edit')")
    @Log(title = "修改数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody T entity) {
        return toAjax(getService().update(entity));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('com:manage:remove')")
    @Log(title = "批量删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(getService().deleteByIds(ids));
    }

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}