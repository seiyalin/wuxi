package org.wuxi.fudan.syfw.dao.user;

import org.wuxi.fudan.syfw.dao.base.BaseDao;
import org.wuxi.fudan.syfw.model.hibernate.User;

public interface UserDao extends BaseDao<User> {
	
	public String findRoleByNameAndPwd(String username, String pwd);
}
