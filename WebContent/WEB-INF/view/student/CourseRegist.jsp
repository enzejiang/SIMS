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
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({
	        title : '课程报名页面', 
	        iconCls : 'icon-more',//图标 
	        border : true, 
	        collapsible : false,//是否可折叠的 
	        fit : true,//自动大小 
	        method : "post",
	        url : "StudentServlet?method=getGrid&t="+new Date().getTime(),
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
 		        {field:'code',title:'学号',width:200, sortable: true},    
 		        {field:'name',title:'姓名',width:200},
 		        {field:'gender',title:'性别',width:100},
 		        {field:'parentPhone',title:'父母电话',width:150},
 		        {field:'rigstTime',title:'报名时间',width:150},
 		       	{field:'paymentday',title:'下次缴费时间',width:150},
 		       	{field:'graduationTime',title:'毕业时间',width:150},
 		       
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
	    $("#bao_ming").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if (selectLength == 0) {
            	$.messager.alert("消息提醒", "请选择数据进行修改!", "warning");
        	}
        	else
        		$("#bao_ming_Dialog").dialog("open");
	    });
	    
	    $("commit_btn").click(function(){
	    	
	    	$('#dataList').datagrid("reload");
	    	
	    });
	    
	  	//课程报名窗口
	    $("#bao_ming_Dialog").dialog({
	    	title: "学生课程报名",
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
						var validate = $("#bao_ming_Form").form("validate");
						if (!validate) {
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else	{
							$.ajax({
								type : "post",
								url : "StudentServlet?method=modify",
								data: $("#bao_ming_Form").serialize(),
								success: function(data) {
									var rsp = $.parseJSON(data);
									if (rsp.status == "true") {
										$.messager.alert("消息提醒", rsp.msg, "info");
										//关闭窗口
										$("#bao_ming_Dialog").dialog("close");
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
	        		var selectRow = $("#dataList").datagrid("getSelected");
            		//设置值
            		//$("#bm_id").textbox('setValue', selectRow.id);
    				$("#bm_code").textbox('setValue', selectRow.code);
    				$("#bm_name").textbox('setValue', selectRow.name);
    				$("#bm_sex").textbox('setValue', selectRow.gender);
    				$("#bm_parent_phone").textbox('setValue', selectRow.parentPhone);
    				$("#bm_parent_wechat").textbox('setValue', selectRow.parentWechat);
			}
	    });
	  	
	  //下拉框通用属性
	  	$("#chooseClass, #add_clazzList").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //不可多选
	  		editable: false, //不可编辑 
	  		method: "post",
	  	});
	  
	  	$("#chooseClass").combobox({
	  		url: "ClassesServlet?method=getGrid&t="+new Date().getTime(),
	  		onLoadSuccess: function(){
		  		//默认选择第一条数据
				var data = $(this).combobox("getData");;
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});
	   
	});
	</script>
</head>
<body>
	<!-- 学生列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	</table>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="bao_ming" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">报名</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="margin: 0 10px 0 10px">查询条件：
			<select id="search_condition" class="easyui-combobox" 
			data-options="editable: false, panelHeight: 50, width: 60, height: 20" name="condition_kind">
			<option value="学号">学号</option>
			<option value="名字">名字</option>
			</select>
			<input id="search_input" class="easyui-textbox" name="condition_value" data-options="required:true, missingMessage:'请填写学号或姓名'" />
			<button id="commit_btn"  type="button">查询</button>
		</div>
		
		<!-- <div style="float: left; margin: 0 10px 0 10px">年级：<input id="gradeList" class="easyui-textbox" name="grade" /></div>
		<div style="margin-left: 10px;">班级：<input id="clazzList" class="easyui-textbox" name="clazz" /></div> -->
	
	</div>
	
	<!-- 添加学生窗口 -->
	<div id="bao_ming_Dialog" style="padding: 10px">  
		<div style="float: right; margin: 20px 20px 0 0; width: 200px; border: 1px solid #EBF3FF" id="photo">
	    	<img alt="照片" style="max-width: 200px; max-height: 400px;" title="照片" src="photo/student.jpg" />
	    </div> 
    	<form id="bao_ming_Form" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td colspan="2">
	    				<!--用来清空表单数据-->
						<input type="reset" id = "reset" style="display: none;" />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>学号:</td>
	    			<td>
	    				<input id="bm_code" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="code"  readonly="readonly"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input id="bm_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name"  readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td><input id="bm_sex" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="gender"  readonly="readonly"/></td></td>
	    			<!--  <td><select id="bm_sex" class="easyui-combobox" data-options="editable: false, panelHeight: 50, width: 60, height: 30" name="gender">
	    			<option value="男">男</option><option value="女">女</option></select></td> -->
	    		</tr>
	    		<tr>
	    			<td>父母电话:</td>
	    			<td><input id="bm_parent_phone" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="parentPhone" validType="mobile"   readonly="readonly"/></td>
	    		</tr>
	    		<tr>
	    			<td>父母微信:</td>
	    			<td><input id="bm_parent_wechat" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="parentWechat"  readonly="readonly"/></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>报名班级:</td>
	    			<td colspan="4"><input id="chooseClass" style="width: 200px; height: 30px;" class="easyui-combobox" type="text" name="chooseClass" precision="1" data-options="editable: false"/></td>
	    		</tr>
	    		<tr>
	    			<td>下次缴费时间:</td>
	    			<td><input id="bm_paymentday" style="width: 200px; height: 30px;" class="easyui-datebox" name="paymentday" /></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
</body>
</html>