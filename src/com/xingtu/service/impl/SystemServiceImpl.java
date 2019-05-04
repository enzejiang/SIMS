package com.xingtu.service.impl;

import java.util.List;

import com.xingtu.bean.SystemInfo;
import com.xingtu.bean.User;
import com.xingtu.dao.IBaseDao;
import com.xingtu.dao.impl.BaseDaoImpl;
import com.xingtu.service.ISystemService;

import net.sf.json.JSONArray;

public class SystemServiceImpl implements ISystemService {
	private IBaseDao dao = new BaseDaoImpl();
	
	public String getAccountList() {
		//获取数据
		List<String> list = dao.getColumn("SELECT account FROM user", null);
		//json化
        String result = JSONArray.fromObject(list).toString();
        
        return result;
	}

	public User getUser(User user) {
		User searchUser = (User) dao.getObject(User.class, 
				"SELECT * FROM user WHERE account=? AND password=? AND type=?", 
				new Object[]{user.getAccount(), user.getPassword(), user.getType()});
		
		return searchUser;
	}

	public void editPassword(User user) {
		dao.update("UPDATE user SET password=? WHERE account=?", 
				new Object[]{user.getPassword(),user.getAccount()});
	}
	
	public SystemInfo editSystemInfo(String name, String value) {
		//修改数据库
		dao.update("UPDATE myschool SET "+name+" = ?", new Object[]{value});
		//重新加载数据
		//获取系统初始化对象
    	SystemInfo sys = (SystemInfo) dao.getObject(SystemInfo.class, "SELECT * FROM myschool", null);
    	return sys;
	}
}
