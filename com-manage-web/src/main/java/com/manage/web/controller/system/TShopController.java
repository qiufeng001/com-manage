package com.manage.web.controller.system;

import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.domain.entity.SysUser;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.core.service.IService;
import com.manage.common.core.core.domain.entity.TShop;
import com.manage.domain.service.ISysUserService;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ISysUserService userService;


    @Override
    protected IService getService() {
        return service;
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TShop shop) {
        startPage();
        List<TShop> list = service.selectList(shop);
        TableDataInfo tableDataInfo = getDataTable(list);
        Map<String, Object> fillDataMap = new HashMap<>();
        SysUser user = new SysUser();
        user.setStatus("0");
        fillDataMap.put("users", userService.selectUserList(user));
        tableDataInfo.setFillData(fillDataMap);
        return tableDataInfo;
    }
}
