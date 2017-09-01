package org.wuxi.fudan.syfw.dao.user;



import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.wuxi.fudan.syfw.dao.base.BaseDaoImpl;
import org.wuxi.fudan.syfw.model.hibernate.User;

/*@Repository: 生成bean Userdao*/
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	
	@SuppressWarnings("unchecked")
	public String findRoleByNameAndPwd(String username, String pwd){
		String hql = "FROM User u WHERE u.username = ?";
		if(StringUtils.isNotBlank(pwd)){
			hql += " AND u.password=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, username);
		if(StringUtils.isNotBlank(pwd)){
			query.setParameter(1, pwd);
		}
		List<User> list = query.list();
		
		if(list != null){
			return list.get(0).getRole();
		}
		else
			return "noUser";   //无该用户
	}

}
