<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生课外培训信息管理系统 产品经理界面</title>
    <link rel="shortcut icon" href="favicon.ico"/>
	<link rel="bookmark" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css" />
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='easyui/js/outlook2.js'> </script>
    <script type="text/javascript">
	 var _menus = {"menus":[
					 	{"menuid":"1","icon":"","menuname":"基础信息管理",
							"menus":[
									/* {"menuid":"11","menuname":"学校信息","icon":"icon-house","url":"GradeServlet?method=toGradeListView"}, */
									{"menuid":"12","menuname":"年级信息","icon":"icon-house","url":"GradeServlet?method=toGradeListView"},
									{"menuid":"13","menuname":"班级信息","icon":"icon-house","url":"ClassesServlet?method=toClassesListView"}
								]
						},
						
						{"menuid":"2","icon":"","menuname":"学生信息管理",
							"menus":[
									{"menuid":"21","menuname":"学生基本信息","icon":"icon-user-student","url":"StudentServlet?method=toStudentListView"},
									{"menuid":"22","menuname":"学生课程报名","icon":"icon-user-student","url":"SchoolTimetableServlet?method=toStudentCourseEnrollmentListView"},
									{"menuid":"23","menuname":"学生课程续费","icon":"icon-user-student","url":"SchoolTimetableServlet?method=toCourseRenewals"}
								]
						},
						
						{"menuid":"3","icon":"","menuname":"教师信息管理",
							"menus":[
									{"menuid":"31","menuname":"教师列表","icon":"icon-user-teacher","url":"TeacherServlet?method=toTeacherListView"}
								]
						},
						
						{"menuid":"4","icon":"","menuname":"课程信息管理",
							"menus":[
									{"menuid":"41","menuname":"课程列表","icon":"icon-book-open","url":"CourseServlet?method=toCourseListView"}
								]
						},
						
						{"menuid":"5","icon":"","menuname":"成绩统计分析",
							"menus":[
									{"menuid":"51","menuname":"考试列表","icon":"icon-exam","url":"ExamServlet?method=toExamListView"}
								]
						},
						
						{"menuid":"6","icon":"","menuname":"比赛信息管理",
							"menus":[
									{"menuid":"61","menuname":"比赛列表","icon":"icon-exam","url":"GameServlet?method=toGameListView"},
									{"menuid":"62","menuname":"比赛报名","icon":"icon-exam","url":"TeacherServlet?method=toTeacherListView"},
									{"menuid":"63","menuname":"查询比赛","icon":"icon-exam","url":"TeacherServlet?method=toTeacherListView"}
								]
						},
						
						{"menuid":"7","icon":"","menuname":"考级信息管理",
							"menus":[
									{"menuid":"71","menuname":"考级列表","icon":"icon-exam","url":"TeacherServlet?method=toTeacherListView"},
									{"menuid":"72","menuname":"考级报名","icon":"icon-exam","url":"TeacherServlet?method=toTeacherListView"},
									{"menuid":"73","menuname":"查询考级","icon":"icon-exam","url":"TeacherServlet?method=toTeacherListView"}
								]
						},
						
						{"menuid":"8","icon":"","menuname":"系统管理",
							"menus":[
							        {"menuid":"81","menuname":"系统设置","icon":"icon-set","url":"SystemServlet?method=toAdminPersonalView"}
								]
						}
				]};


    </script>

</head>
<style>
<!--
	/* .tabs li.tabs-selected a.tabs-inner {
	    border-bottom: 1px solid #fff3f3d9;
	    background-color: #fff3f3d9;
	    background: linear-gradient(to bottom,#fcf2f2 0,#fcf2f2 100%);
	}
	
	.panel-body {
	    background-color: #fff3f3d9;
	    color: #000000;
	    font-size: 12px;
	}
	
	.accordion {
	    background: #fff3f3d9;
	    border-color: #95B8E7;
	} */
-->
</style>
<body class="easyui-layout" style="overflow-y: hidden; background-color: red;"  scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${user.name}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="SystemServlet?method=LoginOut" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; ">学生课外培训信息管理系统</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">Copyright &copy; Enze By POCBITCOIN</div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	<div id="nav" class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
	</div>
	
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<jsp:include page="/WEB-INF/view/productManager/welcome.jsp" />
		</div>
    </div>
	
	<!-- <iframe width=0 height=0 src="refresh.jsp"></iframe> -->
	
</body>
</html>