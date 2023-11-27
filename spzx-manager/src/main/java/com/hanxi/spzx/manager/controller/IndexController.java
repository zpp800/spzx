package com.hanxi.spzx.manager.controller;

import com.hanxi.spzx.manager.service.SysUserService;
import com.hanxi.spzx.manager.service.ValidateCodeService;
import com.hanxi.spzx.model.dto.system.LoginDto;
import com.hanxi.spzx.model.vo.common.Result;
import com.hanxi.spzx.model.vo.common.ResultCodeEnum;
import com.hanxi.spzx.model.vo.system.LoginVo;
import com.hanxi.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService ;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo , ResultCodeEnum.SUCCESS) ;
    }


    @Operation(summary = "验证码接口")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS) ;
    }

}
