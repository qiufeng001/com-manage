package com.manage.domain.service;

import com.manage.domain.domain.TShop;

import java.util.List;

/**
 * 门店Service接口
 *
 * @author zhong.h
 * @date 2021-07-15
 */
public interface ITShopService {
    /**
     * 查询门店
     *
     * @param id 门店ID
     * @return 门店
     */
    public TShop selectTShopById(Long id);

    /**
     * 查询门店列表
     *
     * @param tShop 门店
     * @return 门店集合
     */
    public List<TShop> selectTShopList(TShop tShop);

    /**
     * 新增门店
     *
     * @param tShop 门店
     * @return 结果
     */
    public int insertTShop(TShop tShop);

    /**
     * 修改门店
     *
     * @param tShop 门店
     * @return 结果
     */
    public int updateTShop(TShop tShop);

    /**
     * 批量删除门店
     *
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    public int deleteTShopByIds(Long[] ids);

    /**
     * 删除门店信息
     *
     * @param id 门店ID
     * @return 结果
     */
    public int deleteTShopById(Long id);
}
