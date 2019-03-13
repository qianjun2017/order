/**
 * 
 */
package com.cc.system.user.service;

import java.util.List;

import com.cc.system.user.bean.UserRoleBean;

/**
 * @author Administrator
 *
 */
public interface UserRoleService {

	/**
	 * 修改人员角色分配
	 * @param userId
	 * @param roleIdList
	 */
	void updateUserRole(Long userId, List<Long> roleIdList);
	
	/**
	 * 获取人员角色列表
	 * @param userId
	 * @return
	 */
	List<UserRoleBean> queryUserRoleBeanList(Long userId);
}
