<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.js"></script>
<title>在线考试平台(OEP)-登录页面</title>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		<!--关闭红字警告-->
		$("#U_Name").focusin(function() {
			$("#uname-nullerror").css("display", "none");
			$("#uname-existerror").css("display", "none");
		});
		<!--判断用户名是否为空和是否存在-->
		$("#U_Name").focusout(function() {

			$.ajax({
				url : "${pageContext.request.contextPath}/hwx/checkuname",
				type : "post",
				data : {
					uname : $("#uname").val(),
				},
				dataType : "json",
				success : function(data) {
					if (data == 21) {
						$("#uname-nullerror").css("display", "block");
					}
					if (data == 1) {
						$("#uname-existerror").css("display", "block");
					}
				},

			});
		});

		<!--登录按钮触发-->
		$("#Loginbtn").click(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/hwx/login",
				type : "post",
				data : {
					uname : $("#uname").val(),
					password : $("#upsw").val(),
					idtfcode : $("#idtfcode").val(),
				},
				dataType : "json",
				success : function(data) {
					if (data == 314) {
						alert("登录成功！")
						location.href = "usermain.jsp"
					}else if(data == 365){
						alert("登录失败！\n错误代码:365\n可能的原因是:用户名和密码不匹配")
					}else if (data == 391) {
						alert("登录失败！\n错误代码:391\n可能的原因是:验证码为空")
					}else if (data == 392) {
						alert("登录失败！\n错误代码:392\n可能的原因是:验证码不正确")
					}

				},
			})
		});
		
		$("#codeimg").click(function() {
			document.getElementById('codeimg').src = "${pageContext.request.contextPath}/hwx/crtcode?"+Math.random();
		});
		

	});
</script>
</head>


<body style="background-color: #7EB0DB">
	<div class="container">
		<div class="row">
			<div style="margin-left:90%"><a href="Login4Manager.jsp" style="color:#000099">管理员登录请点我</a></div>
			<div style="margin-top: 150px; margin-left: 35%">
				<h1 style="color: white;">在&nbsp;线&nbsp;考&nbsp;试&nbsp;平&nbsp;台</h1>
			</div>
			<div style="margin-top: 50px; margin-left: 42%">
				<h2 style="color: white;">请&nbsp;登&nbsp;录</h2>
			</div>
			<div class="col-md-3"></div>
			<div class="col-md-6" style="margin-top: 50px">
				<!-- 登陆 -->
				<div>
					<div class="form-group" id="U_Name">
						<label>用户名</label> <input type="text" class="form-control"
							id="uname" placeholder="请输入用户名" value="${cookie.tname.value}">
						<div id="uname-nullerror" style="color: red; display: none">用户名不能为空！</div>
						<div id="uname-existerror" style="color: red; display: none">用户名不存在！</div>
					</div>
					<div class="form-group">
						<label>密码</label> <input type="password" class="form-control"
							id="upsw" placeholder="请输入用户密码" value="${cookie.tpsw.value}">
						<div id="psw-nullerror" style="color: red; display: none">密码不能为空！</div>
					</div>
					<div class="form-group" id="Idtfcode">
						<label>验证码</label> <input type="text" 
							id="idtfcode" placeholder="请输入图片中的数字"/>
						<img id="codeimg" src="${pageContext.request.contextPath}/hwx/crtcode" style="width: 80px;height: 30px"/>
						
					</div>
					
					<div class="checkbox">
						<label> <input type="checkbox" id="rme">记住我
						</label>
					</div>
					<button class="btn btn-danger" id="Loginbtn"
						style="margin-left: 25%">登&nbsp;录</button>
					<a href="Register.jsp"><button class="btn btn-success"
							id="register" style="margin-left: 100px">注&nbsp;册</button></a>
				</div>
			</div>
			<div class="col-md-3"></div>

		</div>
	</div>
</body>
</html>