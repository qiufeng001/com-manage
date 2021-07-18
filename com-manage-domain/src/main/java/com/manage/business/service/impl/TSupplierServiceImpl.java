package com.manage.business.service.impl;

import java.util.List;

import com.manage.business.domain.TSupplier;
import com.manage.business.mapper.TSupplierMapper;
import com.manage.business.service.ITSupplierService;
import com.manage.common.core.core.mapper.IMapper;
import com.manage.common.core.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 供应商Service业务层处理
 *
 * @author zhong.h
 * @date 2021-07-17
 */
@Service
public class TSupplierServiceImpl extends BaseServiceImpl implements ITSupplierService {
    
    @Autowired
    private TSupplierMapper tSupplierMapper;

    @Override
    protected IMapper getMapper() {
        return tSupplierMapper;
    }
}
