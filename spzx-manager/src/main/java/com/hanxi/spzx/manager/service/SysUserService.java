package com.hanxi.spzx.manager.service;


import com.hanxi.spzx.model.dto.system.LoginDto;
import com.hanxi.spzx.model.vo.system.LoginVo;

public interface SysUserService {

    /**
     * 根据用户名查询用户数据
     * @return
     */
    public abstract LoginVo login(LoginDto loginDto) ;

}
