<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="javascript:;" title="行政区划管理">行政区划管理</a></li>
					<li><a href="/dep/findFor" title="部门管理">部门管理</a></li>
					<li><a href="/employee/findFor" title="人员管理">人员管理</a></li>
					<li><a href="/fun/findFor" title="功能管理">功能管理</a></li>
					<li><a href="/role/findFor" title="角色管理">角色管理</a></li>
					<li><a href="/fun/findTreeFor" title="权限管理">权限管理</a></li>
					<li><a href="/uni/findFor" title="学校管理">学校管理</a></li>
					<li><a href="/spe/findFor" title="专业管理">专业管理</a></li>
					<li><a href="/dor/findFor" title="宿舍管理">宿舍管理</a></li>
					<li><a href="/parameter/findFor" title="系统参数">系统参数</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 学员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path%>/studentAccount/toStudentAccountList" title="学员信息管理">学员信息</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 班级管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path%>/schoolclass/show_Schoolclass.do" title="班级管理">班级信息</a></li>
					<li><a href="<%=path%>/timecard/_list.do" title="考勤管理">考勤管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 课程管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/technologyDirection/show_techdirect.do" title="技术方向">技术方向</a></li>
					<li><a href="/knowledgeSection/show_knowSection.do:;" title="知识分节">知识分节</a></li>
					<li><a href="/course/show_course.do" title="原始课程">原始课程</a></li>
					<li><a href="/knowledgePoint/show_knowpoint.do" title="知识点维护">知识点维护</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 教学管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="javascript:;" title="教学任务书">教学任务书</a></li>
					<li><a href="<%=path%>/teachArrangeMent/toTeachArrangeMentList" title="教学任务书">授课安排</a></li>
					<li><a href="<%=path%>/teacherWeekly/toTeacherWeekly" title="讲师周报">讲师周报</a></li>
					<li><a href="<%=path%>/studentWeekly/toStudentWeeklyList" title="学生周报">学生周报</a></li>
					<li><a href="/interview/show_interview.do" title="班主任访谈">班主任访谈</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-reply">
			<dt><i class="Hui-iconfont">&#xe616;</i> 答辩管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="<%=path%>/studentGraduateExam/show_graduateExam.do" title="毕业答辩">毕业答辩</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>