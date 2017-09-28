<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩查询</title>
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
				<a class="navbar-brand" href="#">成绩管理</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul id="myTab" class="nav navbar-nav nav-tabs">
					<li><a href="#qstu" data-toggle="tab">学生成绩查询</a></li>
					<li><a href="#qexam" data-toggle="tab">考试查询</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/llj/mainadmin.jsp">返回</a></li>
					<li><a href="${pageContext.request.contextPath}/hwx/Login.jsp">退出</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="myTabContent" class="tab-content"
		style="margin-top: 30px; margin-left: 50px; margin-right: 50px">
		<div class="tab-pane fade" id="qstu">
			<div class="container">
				<div class="page-header">
					<h1>学生成绩查询</h1>
				</div>
				<div class="row">
					<div class="col-md-5">
						<div class="input-group">
							<input id="in1" type="text" class="form-control"
								placeholder="输入学生姓名"> <span class="input-group-btn">
								<button id="query1" class="btn btn-default" type="button">查询</button>
							</span>
						</div>
						<div id="d1" class="alert alert-info">未查询到此人！请重新查询</div>
						<table id="a" class="table table-striped">
		
						</table>
						<div id="b"></div>
					</div>
					<div class="col-md-1"></div>
					<div id="chdiv1" class="col-md-5">
						
					</div>
				</div>
				<!-- <div class="row">
				<div class="col-md-3">
					<div id="d1" class="alert alert-info">未查询到此人！请重新查询</div>
				</div>
				</div>
				<div class="row">
				<div class="col-md-5">
				<table id="a" class="table table-striped">
		
				</table>
				<div id="b"></div>
				</div>
				</div> -->
			</div>
		</div>
		<div class="tab-pane fade" id="qexam">
			<div class="container">
				<div class="page-header">
					<h1>考试查询</h1>
				</div>
				<div class="row">
					<div class="col-md-5">
						<div class="input-group">
							<input id="in2" type="text" class="form-control"
								placeholder="输入考试名称"> <span class="input-group-btn">
								<button id="query2" class="btn btn-default" type="button">查询</button>
							</span>
						</div>
						<div id="d2" class="alert alert-info">未查询到此考试！请重新查询</div>
						<table id="c" class="table table-striped">
		
						</table>
						<div id="d"></div>
					</div>
					<div class="col-md-1"></div>
					<div id="chdiv2" class="col-md-5">
					
					</div>
				</div>
				<!-- <div class="row">
				<div class="col-md-3">
					<div id="d2" class="alert alert-info">未查询到此考试！请重新查询</div>
				</div>
				</div>
				<div class="row">
				<div class="col-md-5">
				<table id="c" class="table table-striped">
		
				</table>
				<div id="d"></div>
				</div>
				</div> -->
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/statics/js/queryscore.js"></script>
</html>