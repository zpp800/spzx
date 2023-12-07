package com.hanxi.spzx.manager.mapper;

import com.hanxi.spzx.model.dto.system.SysRoleDto;
import com.hanxi.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    public abstract List<SysRole> findByPage(SysRoleDto sysRoleDto);
}
