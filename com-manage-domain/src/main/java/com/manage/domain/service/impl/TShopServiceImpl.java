package com.manage.domain.service.impl;

import java.util.List;

import com.manage.common.core.utils.DateUtils;
import com.manage.domain.domain.TShop;
import com.manage.domain.mapper.TShopMapper;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 门店Service业务层处理
 *
 * @author zhong.h
 * @date 2021-07-15
 */
@Service
public class TShopServiceImpl implements ITShopService {
    @Autowired
    private TShopMapper tShopMapper;

    /**
     * 查询门店
     *
     * @param id 门店ID
     * @return 门店
     */
    @Override
    public TShop selectTShopById(Long id) {
        return tShopMapper.selectTShopById(id);
    }

    /**
     * 查询门店列表
     *
     * @param tShop 门店
     * @return 门店
     */
    @Override
    public List<TShop> selectTShopList(TShop tShop) {
        return tShopMapper.selectTShopList(tShop);
    }

    /**
     * 新增门店
     *
     * @param tShop 门店
     * @return 结果
     */
    @Override
    public int insertTShop(TShop tShop) {
        tShop.setCreateTime(DateUtils.getNowDate());
        return tShopMapper.insertTShop(tShop);
    }

    /**
     * 修改门店
     *
     * @param tShop 门店
     * @return 结果
     */
    @Override
    public int updateTShop(TShop tShop) {
        tShop.setUpdateTime(DateUtils.getNowDate());
        return tShopMapper.updateTShop(tShop);
    }

    /**
     * 批量删除门店
     *
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    @Override
    public int deleteTShopByIds(Long[] ids) {
        return tShopMapper.deleteTShopByIds(ids);
    }

    /**
     * 删除门店信息
     *
     * @param id 门店ID
     * @return 结果
     */
    @Override
    public int deleteTShopById(Long id) {
        return tShopMapper.deleteTShopById(id);
    }
}
