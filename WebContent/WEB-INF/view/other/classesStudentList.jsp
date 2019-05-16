<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>班级学生列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript" src="common/export-util.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({
	        title : '班级学生列表', 
	        iconCls : 'icon-more',//图标 
	        loadMsg : "正在加载数据，请稍后...",
	        border : true, 
	        collapsible : false,//是否可折叠的 
	        fit : true,//自动大小 
	        method : "post",
	        url : "SchoolTimetableServlet?method=getClassesStudentGrid&t=" + new Date().getTime(),
	        idField : 'id', 
	        singleSelect : false,//是否单选 
	        pagination : false,//分页控件 
	        rownumbers : true,//行号 
	        /* sortName : 'id',
	        sortOrder : 'DESC',  */
	        remoteSort : false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id', hidden:true},
 		       	{field:'classesName',title:'班级名称',width:150},
 		       	{field:'gradeName',title:'所属年级',width:150},
 		       	{field:'studentCode',title:'学生学号',width:200},    
		        {field:'studentName',title:'学生姓名',width:200},
		        {field:'studentGender',title:'学生性别',width:100},
		        {field:'courseName',title:'课程名称',width:150},
		       	{field:'startTime',title:'开课时间',width:150},
		       	{field:'endTime',title:'结课时间',width:150},
		       	{field:'teacherName',title:'教师姓名',width:150},
		       	{field:'autograph',title:'签名',width:150, hidden:true}
	 		]], 
	        toolbar: "#toolbar"
	    });
		
		// 点击查询按钮
	    $("#query_btn").click(function() {
	    	excuteQuery();
	    });
		
	 	// 清空查询条件
	    $("#clear_btn").click(function() {
	    	$("#query_form").get(0).reset();
	    });
	 	
	 	// 导出签到表
	    $("#export_autograph_btn").click(function() {
	    	var needPrintHiddenFilds = [];
	    	needPrintHiddenFilds.push("autograph");
	    	NS.Common.ExportUtil.exportExcelByJSONConvertor("dataList", "班级学生签到表", needPrintHiddenFilds);
	    });
	    
	  	
	  	//下拉框通用属性
	  	$("#classesId").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //不可多选
	  		editable: false, //不可编辑 
	  		method: "post",
	  	});
	  
	  	$("#classesId").combobox({
	  		url: "ClassesServlet?method=getGrid&t=" + new Date().getTime(),
	  		onLoadSuccess: function() {
		  		//默认选择第一条数据
				/* var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id); */
	  		},
	  		onChange : function(newValue, oldValue) {
	  			//excuteQuery();
	  		}
	  	});
	   
	});
	
	function excuteQuery() {
		var classesId = $("#classesId").combobox("getValue");
		var courseName = $("#courseName").val();
		var teacherName = $("#teacherName").val();
    	var params = {
			classesId : classesId,
			courseName : courseName,
			teacherName : teacherName
    	};
    	$('#dataList').datagrid('options').queryParams = params;
    	$('#dataList').datagrid("reload");
	};
	</script>
</head>
<body>
	<!-- 班级学生列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> </table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<form id="query_form" method="post" action=""  onsubmit="return false;">
			<div style="margin: 0 20px 0 20px">
				班级：<input id="classesId" name="classesId" style="width: 200px; height: 30px;" class="easyui-combobox" type="text" data-options="editable: false"/>
				课程名称：<input id="courseName" name="courseName" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="editable: true"/>
				教师姓名：<input id="teacherName" name="teacherName" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="editable: true"/>
				<button id="query_btn"  type="button" class="easyui-linkbutton">查&nbsp;&nbsp;询</button>
				<button id="clear_btn"  type="button" class="easyui-linkbutton">清&nbsp;&nbsp;空</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="export_autograph_btn"  type="button" class="easyui-linkbutton">导出签到表</button>
			</div>
		</form>
	</div>
</body>
</html>