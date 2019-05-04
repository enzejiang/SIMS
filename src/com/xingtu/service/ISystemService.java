package com.xingtu.service;

import com.xingtu.bean.SystemInfo;
import com.xingtu.bean.User;

public interface ISystemService {
	/**
	 * 获取系统所有账号
	 * @return
	 */
	String getAccountList();
	
	/**
	 * 获取用户信息
	 * @param user
	 * @return
	 */
	User getUser(User user);
	
	/**
	 * 修改用户密码
	 * @param user
	 */
	void editPassword(User user);
	
	/**
	 * 修改系统信息
	 * @param name 修改的名称
	 * @param value 值
	 * @return 返回修改后的系统信息对象
	 */
	SystemInfo editSystemInfo(String name, String value);
	

}
