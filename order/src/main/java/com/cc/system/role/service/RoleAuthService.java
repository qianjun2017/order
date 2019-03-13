/**
 * 
 */
package com.cc.system.role.service;

import java.util.List;

import com.cc.system.role.bean.RoleAuthBean;

/**
 * @author Administrator
 *
 */
public interface RoleAuthService {

	/**
	 * 修改角色权限分配
	 * @param roleId
	 * @param authIdList
	 */
	void updateRoleAuth(Long roleId, List<Long> authIdList);
	
	/**
	 * 查询角色对应的权限关联关系
	 * @param roleList
	 * @return
	 */
	List<RoleAuthBean> queryRoleAuthBeanList(List<Long> roleList);
}
