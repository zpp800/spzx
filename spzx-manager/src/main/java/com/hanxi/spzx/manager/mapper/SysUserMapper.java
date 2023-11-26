package com.hanxi.spzx.manager.mapper;

import com.hanxi.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    SysUser selectByUserName(String userName) ;

}
