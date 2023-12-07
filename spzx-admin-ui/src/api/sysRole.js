import request from '@/utils/request'

// 分页查询角色数据
export const GetSysRoleListByPage = (current, limit, queryDto) => {
  return request({
    url: `/admin/system/sysRole/findByPage/${current}/${limit}`,
    method: 'post',
    data: queryDto,
  })
}
