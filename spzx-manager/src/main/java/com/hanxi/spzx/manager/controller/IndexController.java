package com.hanxi.spzx.manager.controller;

import com.hanxi.spzc.utils.AuthContextUtil;
import com.hanxi.spzx.manager.service.SysUserService;
import com.hanxi.spzx.manager.service.ValidateCodeService;
import com.hanxi.spzx.model.dto.system.LoginDto;
import com.hanxi.spzx.model.entity.system.SysUser;
import com.hanxi.spzx.model.vo.common.Result;
import com.hanxi.spzx.model.vo.common.ResultCodeEnum;
import com.hanxi.spzx.model.vo.system.LoginVo;
import com.hanxi.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
@RequiredArgsConstructor
public class IndexController {

    private final SysUserService sysUserService ;
    private final ValidateCodeService validateCodeService;
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "Authorization") String token) {
        token = StringUtils.substring(token,"Bearer ".length());
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo() {
        return Result.build(AuthContextUtil.get()  , ResultCodeEnum.SUCCESS) ;
    }
//    @GetMapping(value = "/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "Authorization") String token) {
//        token = StringUtils.substring(token,"Bearer ".length());
//        SysUser sysUser = sysUserService.getUserInfo(token) ;
//        return Result.build(sysUser , ResultCodeEnum.SUCCESS) ;
//    }

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
