package com.manage.web.controller.system;

import java.util.List;

import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.service.IService;
import com.manage.domain.domain.TShop;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店Controller
 *
 * @author zhong.h
 * @date 2021-07-15
 */
@RestController
@RequestMapping("/system/shop")
public class TShopController extends BaseController<TShop, Long> {

    @Autowired
    private ITShopService service;


    @Override
    protected IService getService() {
        return service;
    }
}
