package com.xingtu.bean;

import java.io.Serializable;

/**
 * 项目名称：ssms
 * 类名称：Page
 * <br>
 * 包路径：com.xingtu.bean
 * <br>
 * 类描述：分页信息
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月23日 上午1:13:54
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月23日 上午1:13:54
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public class Page implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9138817937744906283L;

	/**
	 * 开始查询位置
	 */
	private int start; 
	
	/**
	 * 页码
	 */
	private int code; 
	
	/**
	 * 每页记录数
	 */
	private int size;
	

	public Page() {
		
	}
	
	public Page(int code, int size) {
		this.code = code;
		this.size = size;
		this.start = (code-1) * size;
	}
	
	public int getStart() {
		this.start = (code-1) * size;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
