package com.manage.web.controller.tool;

import com.manage.common.core.core.controller.impl.BaseController;
import com.manage.common.core.core.service.IService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 *
 * @author zhong.h
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController {


    @Override
    protected IService getService() {
        return null;
    }

    @PreAuthorize("@ss.hasPermi('tool:swagger:view')")
    @GetMapping()
    public String index() {
        return redirect("/swagger-ui.html");
    }
}
