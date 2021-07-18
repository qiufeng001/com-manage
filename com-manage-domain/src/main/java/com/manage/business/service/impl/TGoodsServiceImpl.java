package com.manage.business.service.impl;

import java.util.List;

import com.manage.common.core.core.mapper.IMapper;
import com.manage.common.core.core.service.impl.BaseServiceImpl;
import com.manage.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manage.business.mapper.TGoodsMapper;
import com.manage.business.domain.TGoods;
import com.manage.business.service.ITGoodsService;

/**
 * 商品Service业务层处理
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@Service
public class TGoodsServiceImpl extends BaseServiceImpl implements ITGoodsService {

    @Autowired
    private TGoodsMapper mapper;

    protected IMapper<TGoods, String> getMapper() {
        return mapper;
    }
}
