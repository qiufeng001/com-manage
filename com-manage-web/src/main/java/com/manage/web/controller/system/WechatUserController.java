package com.manage.web.controller.system;


import com.manage.common.core.annotation.Log;
import com.manage.common.core.constant.UserConstants;
import com.manage.common.core.core.controller.AbstractController;
import com.manage.common.core.core.domain.AjaxResult;
import com.manage.common.core.core.domain.entity.*;
import com.manage.common.core.core.domain.model.LoginUser;
import com.manage.common.core.core.page.TableDataInfo;
import com.manage.common.core.enums.BusinessType;
import com.manage.common.core.utils.SecurityUtils;
import com.manage.common.core.utils.ServletUtils;
import com.manage.common.core.utils.StringUtils;
import com.manage.common.core.utils.poi.ExcelUtil;
import com.manage.domain.service.*;
import com.manage.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author zhong.h
 */
@RestController
@RequestMapping("/wechat/user")
public class WechatUserController extends AbstractController {

    @Autowired
    private IWechatUserService userService;

    @Autowired
    private IWechatRoleService roleService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(WechatUser user) {
        startPage();
        List<WechatUser> list = userService.selectUserList(user);
        TableDataInfo tableDataInfo = getDataTable(list);
        return tableDataInfo;
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(WechatUser user) {
        List<WechatUser> list = userService.selectUserList(user);
        ExcelUtil<WechatUser> util = new ExcelUtil<WechatUser>(WechatUser.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<WechatRole> roles = roleService.selectRoleAll();
        ajax.put("roles", roles);
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }
}
