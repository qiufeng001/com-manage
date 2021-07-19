package com.manage.domain.service.impl;

import com.manage.common.core.core.mapper.IMapper;
import com.manage.common.core.core.service.impl.BaseServiceImpl;
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
public class TShopServiceImpl extends BaseServiceImpl implements ITShopService {

    @Autowired
    private TShopMapper tShopMapper;


    @Override
    protected IMapper getMapper() {
        return tShopMapper;
    }
}
