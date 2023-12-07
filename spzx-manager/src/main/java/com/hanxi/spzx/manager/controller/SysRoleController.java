package com.hanxi.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.hanxi.spzx.manager.service.SysRoleService;
import com.hanxi.spzx.model.dto.system.SysRoleDto;
import com.hanxi.spzx.model.entity.system.SysRole;
import com.hanxi.spzx.model.vo.common.Result;
import com.hanxi.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage( @RequestBody  SysRoleDto sysRoleDto ,
                                                @PathVariable(value = "current") Integer current ,
                                                @PathVariable(value = "limit") Integer limit) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , current , limit) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }


}
