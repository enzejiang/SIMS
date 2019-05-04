package com.xingtu.bean;

import java.io.Serializable;

/**
 * 项目名称：ssms
 * 类名称：Course
 * <br>
 * 包路径：com.xingtu.bean
 * <br>
 * 类描述：课程信息类
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月24日 上午2:34:27
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月24日 上午2:34:27
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6409165043133549799L;
	
	private int id; // 主键，课程ID
	
	private String name; // 名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
