<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理主界面</title>
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.js"></script>
<!-- 引入chart.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/Chart.js"></script>
<!-- 引入bootstrap -->
<link type="text/css" href="${pageContext.request.contextPath}/statics/js/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">管理主界面</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/hwx/Login.jsp">退出</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container" style="margin-top: 70px">
		<div class="row">
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/statics/imgs/a.png" alt="考试管理">
					<div class="caption">
						<h3>考试管理</h3>
						<p>有发布考试，取消考试，考试查询，修改考试这4个功能</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/statics/imgs/b.png" alt="题库管理">
					<div class="caption">
						<h3>题库管理</h3>
						<p>有增加题目，删除题目，修改题目，查询题目这4个功能</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/statics/imgs/c.png" alt="用户管理">
					<div class="caption">
						<h3>用户管理</h3>
						<p>有用户查询，修改用户信息，删除用户这3个功能</p>
						<p>
							<a href="#" class="btn btn-primary" role="button">进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/statics/imgs/d.png" alt="成绩管理">
					<div class="caption">
						<h3>成绩管理</h3>
						<p>有通过用户名查成绩与通过考试名查成绩这2个功能</p>
						<p>
							<a href="${pageContext.request.contextPath}/llj/score.jsp" class="btn btn-primary" role="button">进入</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>