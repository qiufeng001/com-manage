package com.manage.business.service.impl;

import com.manage.common.core.core.mapper.IMapper;
import com.manage.common.core.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manage.business.mapper.TDiscountMapper;
import com.manage.business.service.ITDiscountService;

/**
 * 折扣Service业务层处理
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@Service
public class TDiscountServiceImpl extends BaseServiceImpl implements ITDiscountService {

    @Autowired
    private TDiscountMapper tDiscountMapper;

    @Override
    protected IMapper getMapper() {
        return tDiscountMapper;
    }
}
