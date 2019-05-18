<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>待报名学生列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({
	        title : '学生课程续费列表', 
	        iconCls : 'icon-more',//图标 
	        loadMsg : "正在加载数据，请稍后...",
	        border : true, 
	        collapsible : false,//是否可折叠的 
	        fit : true,//自动大小 
	        method : "post",
	        url : "SchoolTimetableServlet?method=getCourseRenewalsGrid&t=" + new Date().getTime(),
	        idField : 'id', 
	        singleSelect : false,//是否单选 
	        pagination : false,//分页控件 
	        rownumbers : true,//行号 
	        sortName : 'id',
	        sortOrder : 'DESC', 
	        remoteSort : false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID', hidden:true, sortable: true},    
 		        {field:'studentCode',title:'学号',width:200, sortable: true},    
 		        {field:'studentName',title:'姓名',width:200},
 		        {field:'studentGender',title:'性别',width:100},
 		        {field:'classesName',title:'班级名称',width:150},
 		        {field:'courseName',title:'课程名称',width:150},
 		       	{field:'startDate',title:'课程开始日期',width:150},
 		       	{field:'endDate',title:'课程到期日期',width:150}
	 		]], 
	        toolbar: "#toolbar"
	    });
		
		// 点击查询按钮
	    $("#query_btn").click(function() {
	    	excuteQuery();
	    });
		
	 	// 清空查询条件
	    $("#clear_btn").click(function() {
	    	$("#classesId").combobox("setValue", "");
	    	$("#query_form").get(0).reset();
	    });
	    
		// 点击续费按钮
	    $("#commit_btn").click(function() {
	    	var endDate = $("#endDate").datebox("getValue");
	    	
	    	if (!endDate) {
	    		$.messager.alert("提示", "请选择续费日期！", "info");
	    		return false;
	    	}
	    	
	    	var rows = $("#dataList").datagrid("getSelections");
	    	if (!rows || rows.length < 1) {
	    		$.messager.alert("提示", "请在列表中勾选需要续费的学生课程！", "info");
	    		return false;
	    	}
	    	
	    	var ids = [];
        	$(rows).each(function(i, row) {
        		ids[i] = row.id;
        	});
	    	var params = {
    			endDate : endDate,
    			ids : ids.join(",")
	    	};
	    	
	    	$.ajax({
				type: "post",
				url: "SchoolTimetableServlet?method=updateSchoolTimetableEndDate",
				data: params,
				success: function(data) {
					var rsp = $.parseJSON(data);
					if (rsp && rsp.status == "true") {
						$.messager.alert("提示", rsp.msg, "info");
						//刷新表格
						excuteQuery();
					} else {
						$.messager.alert("提示", rsp.msg, "warning");
						return;
					}
				}
			});
	    	
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
		var studentName = $("#studentName").val();
    	var params = {
			classesId : classesId,
			courseName : courseName,
			studentName : studentName
    	};
    	$('#dataList').datagrid('options').queryParams = params;
    	$('#dataList').datagrid("reload");
	};
	</script>
</head>
<body>
	<!-- 学生待续费课程列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> </table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<form id="query_form" method="post" action=""  onsubmit="return false;">
			<div style="margin: 0 20px 0 20px">
				班级：<input id="classesId" name="classesId" style="width: 200px; height: 30px;" class="easyui-combobox" type="text" data-options="editable: false"/>
				课程名称：<input id="courseName" name="courseName" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="editable: true"/>
				学生姓名：<input id="studentName" name="studentName" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" data-options="editable: true"/>
				<button id="query_btn"  type="button" class="easyui-linkbutton">查&nbsp;&nbsp;询</button>
				<button id="clear_btn"  type="button" class="easyui-linkbutton">清&nbsp;&nbsp;空</button>&nbsp;&nbsp;&nbsp;&nbsp;
				续费日期：<input id="endDate" name="endDate" style="width: 200px; height: 30px;" class="easyui-datebox" type="text" data-options="editable: false"/>
				<button id="commit_btn"  type="button" class="easyui-linkbutton">续 &nbsp;&nbsp;费</button>
			</div>
		</form>
	</div>
</body>
</html>