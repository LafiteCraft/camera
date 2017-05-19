<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'error.jsp' starting page</title>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>error/css/style.css" rel="stylesheet"
			type="text/css" media="all" />
	</head>
	<body>
		<!-----start-wrap--------->
		<div class="wrap">
			<!-----start-content--------->
			<div class="content">
				<!-----start-logo--------->
				<div class="logo">
					<h1>
						<a href="#"><img src="<%=basePath%>error/images/logo.png" />
						</a>
					</h1>
					<span><img src="<%=basePath%>error/images/signal.png" />oh'
						测试</span>
				</div>
				<!-----end-logo--------->
				<!-----start-search-bar-section--------->
				<%--<div class="buttom">--%>
					<%--<div class="seach_bar">--%>
						<%--<p>--%>
							<%--您可以回到--%>
							<%--<span><a href="#">首页</a> </span> 或者在下方查找*-*--%>
						<%--</p>--%>
						<%--<!-----start-sear-box--------->--%>
						<%--<form>--%>
							<%--<input type="text" value="Search" onfocus="this.value = '';"--%>
								<%--onblur="if (this.value == '') {this.value = 'Search';}">--%>
							<%--<input type="submit" value="">--%>
						<%--</form>--%>
					<%--</div>--%>
				<%--</div>--%>
				<!-----end-sear-bar--------->
			</div>
			<!----copy-right-------------->

		</div>

		<!---------end-wrap---------->
	</body>
</html>
