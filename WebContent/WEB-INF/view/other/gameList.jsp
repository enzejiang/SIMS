<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>学生列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript" src="easyui/CitySelect.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({
	   // 	var place = $("#search_game_place").textbox("getValue");
	//		var gamedate = $("#search_game_date").datebox("getValue");
	        title : '比赛信息列表', 
	        iconCls : 'icon-more',//图标 
	        border : true, 
	        collapsible : false,//是否可折叠的 
	        fit : true,//自动大小 
	        method : "post",
	        url : "GameServlet?method=getGrid&t="+new Date().getTime(),
	        idField : 'id', 
	        singleSelect : false,//是否单选 
	        pagination : false,//分页控件 
	        rownumbers : true,//行号 
	        sortName : 'id',
	        sortOrder : 'DESC', 
	        remoteSort : false,
	        columns: [[  
				/* {field:'chk',checkbox: true,width:50}, */
 		        {field:'id',title:'ID', hidden:true, sortable: true},    
 		        {field:'gameName',title:'赛事名称',width:200},    
 		        {field:'gamePlace',title:'比赛地点',width:200},
 		        {field:'gameDate',title:'比赛时间',width:200,sortable: true},
 		        {field:'gameContent',title:'参赛舞蹈',width:150},
  		        {field:'gameAddr',title:'比赛详细地址',width:200},
 		        {field:'gameResults',title:'比赛结果',width:150},
 		        /* {field:'clazz',title:'班级',width:150, 
 		        	formatter: function(value,row,index){
 						if (row.clazz){
 							return row.clazz.name;
 						} else {
 							return value;
 						}
 					}
				},
 		        {field:'grade',title:'年级',width:150, 
					formatter: function(value,row,index){
 						if (row.grade){
 							return row.grade.name;
 						} else {
 							return value;
 						}
 					}	
 		       	}, */
	 		]], 
	        toolbar: "#toolbar"
	    }); 
		
	    //设置分页控件 
	    /* var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    });  */
	    
	   
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    
	    $("#commit_btn").click(function(){
	    	//刷新表格
			$("#dataList").datagrid("reload");
			$("#dataList").datagrid("uncheckAll");
	    });
	    
	    $("#edit").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if (selectLength == 0) {
            	$.messager.alert("消息提醒", "请选择数据进行修改!", "warning");
        	}
        	else
	    		$("#editDialog").dialog("open");
	    });
	    //删除
	    $("#delete").click(function() {
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if (selectLength == 0) {
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else {
            	var idArr = [];
            	$(selectRows).each(function(i, row){
            		idArr[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除与学生相关的所有数据(包括成绩)，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "GameServlet?method=delete",
							data: {ids: idArr},
							success: function(msg) {
								if (msg == "true") {
									$.messager.alert("消息提醒", "删除成功!", "info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	    
	  	
	  	
	  	//设置添加学生窗口
	    $("#addDialog").dialog({
	    	title: "添加比赛",
	    	width: 750,
	    	height: 560,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-user_add',
					handler:function() {
						var validate = $("#addForm").form("validate");
						if (!validate) {
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else	{
							$.ajax({
								type : "post",
								url : "GameServlet?method=insert",
								data: $("#addForm").serialize(),
								success: function(data) {
									var rsp = $.parseJSON(data);
									if (rsp.status == "true") {
										$.messager.alert("消息提醒", rsp.msg, "info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#reset").trigger("click");
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
										
									} else{
										$.messager.alert("消息提醒", rsp.msg, "warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text : '重置',
					plain : true,
					iconCls : 'icon-reload',
					handler : function() {
						$("#reset").trigger("click");
					}
				},
			],
	    	onBeforeOpen: function(){
    			Add_CityChoose();
			}
	    });
	  	
	  	//修改比赛信息窗口
	    $("#editDialog").dialog({
	    	title: "修改比赛信息",
	    	width: 750,
	    	height: 560,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'修改',
					plain: true,
					iconCls:'icon-user_edit',
					handler:function() {
						var validate = $("#editForm").form("validate");
						if (!validate) {
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else	{
							$.ajax({
								type : "post",
								url : "GameServlet?method=modify",
								data: $("#editForm").serialize(),
								success: function(data) {
									var rsp = $.parseJSON(data);
									if (rsp.status == "true") {
										$.messager.alert("消息提醒", rsp.msg, "info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#reset").trigger("click");
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
										
									} else{
										$.messager.alert("消息提醒", rsp.msg, "warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text : '重置',
					plain : true,
					iconCls : 'icon-reload',
					handler : function() {
						$("#reset").trigger("click");
					}
				},
			],
	        onBeforeOpen: function(){
	        		Edit_CityChoose();
	        		var selectRow = $("#dataList").datagrid("getSelected");
            		//设置值
            		$("#edit_id").textbox('setValue', selectRow.id);
    				$("#edit_game_name").textbox('setValue', selectRow.gameName);
    				var strArr = selectRow.gamePlace.split("_");
    				$("#s1_edit").textbox('setValue', strArr[0]);
    				$("#s2_edit").textbox('setValue', strArr[1]);
    				$("#s3_edit").textbox('setValue', strArr[2]);
    				//$("#edit_game_place").textbox('setValue', selectRow.gamePlace);
    				$("#edit_game_date").textbox('setValue', selectRow.gameDate);
    				$("#edit_game_result").textbox('setValue', selectRow.gameResults);
    				$("#edit_game_content").textbox('setValue', selectRow.gameContent);
            	
			}
	    });
	   
	});
	</script>
</head>
<body onload="CityChoose()">
	<!-- 比赛列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar" >
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>	
		<div style="float: left;">比赛地点  
			 <select class="easyui-combobox" name="province" id="s1" data-options="editable: false, panelHeight: 50, width: 60, height: 30"><option value="sheng">湖南省</option></select>
			 <select class="easyui-combobox" name="city" id="s2"><option value="shi" data-options="editable: false, panelHeight: 50, width: 60, height: 30">永州市</option></select>
			 <select class="easyui-combobox" name="town" id="s3"><option value="xiang" data-options="editable: false, panelHeight: 50, width: 60, height: 30">东安县</option></select>
		</div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;">比赛舞种<select id= "search_game_kind" style="width:100px; height:22px;" class="easyui-combox" name="game_kind" ><option value="sheng">爵士舞</option> </select></div>	
		<div style="float: left;">比赛时间<input id= "search_game_date" style="width:100px; height: 22px;" class="easyui-datebox" name="game_date" data-options="required:true, missingMessage:'请填写比赛时间'" /></div>	
		<div style="float: left;" class="datagrid-btn-separator"></div>
		
	</div>
	
	<!-- 添加比赛窗口 -->
	<div id="addDialog" style="padding: 10px" >  
		<div style="float: right; margin: 20px 20px 0 0; width: 200px; border: 1px solid #EBF3FF" id="photo">
	    	<img alt="照片" style="max-width: 200px; max-height: 400px;" title="照片" src="photo/student.jpg" />
	    </div> 
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td colspan="2">
	    				<!--用来清空表单数据-->
						<input type="reset" id = "reset" style="display: none;" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>赛会名称</td>
	    			<td><input id="add_game_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写比赛名称'" /></td>
	    		</tr>
	    		<tr>    		
	    			<td>比赛地点</td>
	    			<td><select class="easyui-combobox" name="province" id="s1_add" data-options="editable: false, panelHeight: 50, width: 60, height: 30"><option value="sheng">湖南省</option></select></td>
	    			<td><select class="easyui-combobox" name="city" id="s2_add"><option value="shi" data-options="editable: false, panelHeight: 50, width: 60, height: 30">永州市</option></select></td>
	    			<td><select class="easyui-combobox" name="town" id="s3_add"><option value="xiang" data-options="editable: false, panelHeight: 50, width: 60, height: 30">东安县</option></select></td>		    			
	    		</tr>
	    		<tr>
	    			<td>比赛时间:</td>
	    			<td><input id= "add_game_date" style="width: 200px; height: 30px;" class="easyui-datebox" name="date" /></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>比赛内容:</td>
	    			<td><input id="add_game_content" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="content" data-options="required:true, missingMessage:'请填写比赛内容'" /></td>
	    		</tr>
	    		<tr>
	    			<td>比赛详细地址</td>
	    			<td><input id="add_game_addr" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="addr" data-options="required:true, missingMessage:'请填写比赛详细地址'" /></td>
	    		</tr>
	    		<tr>
	    			<td>比赛结果:</td>
	    			<td><input id="add_game_result" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="result" data-options="required:true, missingMessage:'请填写比赛地点'" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	<!-- 修改比赛窗口 -->
	<div id="editDialog" style="padding: 10px" >  
		<div style="float: right; margin: 20px 20px 0 0; width: 200px; border: 1px solid #EBF3FF" id="photo">
	    	<img alt="照片" style="max-width: 200px; max-height: 400px;" title="照片" src="photo/student.jpg" />
	    </div> 
    	<form id="editForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td colspan="2">
	    				<!--用来清空表单数据-->
						<input type="reset" id = "reset" style="display: none;" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>ID:</td>
	    			<td>
	    				<input id="edit_id" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="id"  readonly="readonly" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>赛会名称</td>
	    			<td><input id="edit_game_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写比赛名称'" /></td>
	    		</tr>
	    		<td><input id="edit_game_place" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="place" data-options="required:true, missingMessage:'请填写比赛地点'" /></td>
	    		<!--  
	    		<tr>
	    			<td>比赛地点</td>
	    			<td><select class="select" name="province" id="s1_edit" data-options="editable: false, panelHeight: 50, width: 60, height: 30"><option value="sheng">湖南省</option></select></td>
	    			<td><select class="select" name="city" id="s2_edit"><option value="shi" data-options="editable: false, panelHeight: 50, width: 60, height: 30">永州市</option></select></td>
	    			<td><select class="select" name="town" id="s3_edit"><option value="xiang" data-options="editable: false, panelHeight: 50, width: 60, height: 30">东安县</option></select></td>	
	    		</tr>
	    		-->
	    		<tr>
	    			<td>比赛时间:</td>
	    			<td><input id= "edit_game_date" style="width: 200px; height: 30px;" class="easyui-datebox" name="date" /></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>比赛内容:</td>
	    			<td><input id="edit_game_content" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="content" data-options="required:true, missingMessage:'请填写比赛内容'" /></td>
	    		</tr>
	    		<tr>
	    			<td>比赛详细地址</td>
	    			<td><input id="edit_game_addr" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="addr" data-options="required:true, missingMessage:'请填写比赛详细地址'" /></td>
	    		</tr>
	    		<tr>
	    			<td>比赛结果:</td>
	    			<td><input id="edit_game_result" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="result" data-options="required:true, missingMessage:'请填写比赛地点'" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>