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
	        title : '学生课程报名列表', 
	        iconCls : 'icon-more',//图标 
	        loadMsg : "正在加载数据，请稍后...",
	        border : true, 
	        collapsible : false,//是否可折叠的 
	        fit : true,//自动大小 
	        method : "post",
	        url : "SchoolTimetableServlet?method=getGrid&t=" + new Date().getTime(),
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
 		        {field:'code',title:'学号',width:200, sortable: true},    
 		        {field:'name',title:'姓名',width:200},
 		        {field:'gender',title:'性别',width:100},
 		        {field:'parentPhone',title:'父母电话',width:150},
 		        {field:'rigstTime',title:'报名时间',width:150},
 		       	{field:'paymentday',title:'下次缴费时间',width:150},
 		       	{field:'graduationTime',title:'毕业时间',width:150}
	 		]], 
	        toolbar: "#toolbar"
	    });
	    
		// 点击报名按钮
	    $("#commit_btn").click(function() {
	    	var classesId = $("#classesId").combobox("getValue");
	    	var startDate = $("#startDate").datebox("getValue");
	    	var endDate = $("#endDate").datebox("getValue");
	    	
	    	if (!classesId) {
	    		$.messager.alert("提示", "请选择报名班级！", "info");
	    		return false;
	    	}
	    	
	    	if (!startDate) {
	    		$.messager.alert("提示", "请选择课程开始日期！", "info");
	    		return false;
	    	}
	    	
	    	if (!endDate) {
	    		$.messager.alert("提示", "请选择课程结束日期！", "info");
	    		return false;
	    	}
	    	
	    	var rows = $("#dataList").datagrid("getSelections");
	    	console.log(rows.length);
	    	if (!rows || rows.length < 1) {
	    		$.messager.alert("提示", "请在列表中勾选需要报名的学生！", "info");
	    		return false;
	    	}
	    	
	    	var ids = [];
        	$(rows).each(function(i, row) {
        		ids[i] = row.id;
        	});
	    	
	    	var params = {
    			classesId : classesId,
    			startDate : startDate,
    			endDate : endDate,
    			ids : ids
	    	};
	    	
	    	$.ajax({
				type: "post",
				url: "SchoolTimetableServlet?method=insert",
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
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		},
	  		onChange : function(newValue, oldValue) {
	  			excuteQuery();
	  		}
	  	});
	   
	});
	
	function excuteQuery() {
		var classesId = $("#classesId").combobox("getValue");
    	var params = {
			classesId : classesId
    	};
    	$('#dataList').datagrid('options').queryParams = params;
    	$('#dataList').datagrid("reload");
	};
	</script>
</head>
<body>
	<!-- 待报名学生列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> </table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="margin: 0 10px 0 10px">
			报名班级：<input id="classesId" name="classesId" style="width: 200px; height: 30px;" class="easyui-combobox" type="text" data-options="editable: false"/>
			开始日期：<input id="startDate" name="startDate" style="width: 200px; height: 30px;" class="easyui-datebox" type="text" data-options="editable: false"/>
			结束日期：<input id="endDate" name="endDate" style="width: 200px; height: 30px;" class="easyui-datebox" type="text" data-options="editable: false"/>
			<button id="commit_btn"  type="button" class="easyui-linkbutton">报  名</button>
		</div>
		
		<!-- <div style="float: left; margin: 0 10px 0 10px">年级：<input id="gradeList" class="easyui-textbox" name="grade" /></div>
		<div style="margin-left: 10px;">班级：<input id="clazzList" class="easyui-textbox" name="clazz" /></div> -->
	
	</div>
</body>
</html>