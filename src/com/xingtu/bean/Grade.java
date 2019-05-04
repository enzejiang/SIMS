package com.xingtu.bean;

import java.io.Serializable;

/**
 * 项目名称：ssms
 * 类名称：Grade
 * <br>
 * 包路径：com.xingtu.bean
 * <br>
 * 类描述：年级信息
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月22日 下午11:42:50
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月22日 下午11:42:50
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class Grade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082278206762947994L;
	
	private Integer id; // 主键ID
	
	private String name; // 名称
	
	private String createTime; // 所属年月

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
