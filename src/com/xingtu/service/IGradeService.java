package com.xingtu.service;

import java.util.List;

import com.sun.xml.internal.bind.v2.TODO;
import com.xingtu.bean.Grade;

/**
 * 项目名称：ssms
 * 类名称：IGradeService
 * <br>
 * 包路径：com.xingtu.service
 * <br>
 * 类描述：年级服务
 * <br>
 * 创建人：zml
 * 创建时间：2019年4月23日 上午12:02:36
 * <br>
 * 修改人：zml
 * 修改时间：2019年4月23日 上午12:02:36
 * <br>
 * 修改备注： TODO
 * 
 * @version V1.0
 */
public interface IGradeService {
	/**
	 * 名称：getGrades
	 * <br>
	 * 描述：获取年级列表
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午12:36:17
	 * <br>
	 * 返回值类型：String
	 * @throws TODO
	 * @return
	 */
	List<Object> getGrades();
	
	/**
	 * 名称：insert
	 * <br>
	 * 描述：新增年级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午12:21:46
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param name
	 * @param createTime
	 * @return 
	 */
	Integer insert(String name, String createTime);
	
	/**
	 * 名称：delete
	 * <br>
	 * 描述：根据ID删除年级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午12:35:08
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 名称：update
	 * <br>
	 * 描述：更新年级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月23日 上午12:37:08
	 * <br>
	 * 返回值类型：void
	 * @throws TODO
	 * @param classesId
	 * @param name
	 * @param createTime
	 */
	void update(Integer id, String name, String createTime);
	
	/**
	 * 名称：getById
	 * <br>
	 * 描述：根据ID查找年级信息
	 * <br>
	 * 创建人：zml
	 * 创建时间：2019年4月24日 上午1:58:55
	 * <br>
	 * 返回值类型：Grade
	 * @throws TODO
	 * @param id
	 * @return
	 */
	Grade getById(Integer id);

}
