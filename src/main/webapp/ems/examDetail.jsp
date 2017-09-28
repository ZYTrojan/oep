<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/bootstrap/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.9.1.js"></script>
<!-- 引入easyui.js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<title>考试详细信息</title>
</head>
<script type="text/javascript">
	$(function(){
		//面板
		$("#panel").panel({
			title : "新增考试",
		});
		//表单
		$("#ff").form({
			novalidate:true
		});
		//考试名
		$("input[name='examName'").textbox({
			label : '考试名:',
			required:true
		});
		//考试开始时间
		$("input[name='startTime'").datetimebox({   
			label : "开始时间:",
			width : 245,
		});
		//考试结束时间
		$("input[name='endTime'").datetimebox({    
			label : "结束时间:",
			width : 245,
		});
		//重写日期格式 
		$.fn.datebox.defaults.formatter = function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'年'+m+'月'+d+'日';
		}
		//总分
		$("input[name='examTotal'").numberspinner({    
			label : "总分:",
		    min : 0,
		    max : 1000,
		    editable : true,
		    width : 245
		});  
		//保存试卷信息,进入考题设置界面
		$("#saveBtn").linkbutton({
			text : "保存",
			onClick : function(){
				$("#ff").form("submit",{
				    url : "${pageContext.request.contextPath}/ems/insert",
				    success : function(data){
				    	var json = eval("("+data+")");
				    	if(json.result){
				    		alert(json.msg);
				    		$('#saveBtn').linkbutton({
				    				text : "下一步",
				    				onClick : function(){
				    					location.href = json.url;
				    				}
				    		});
				    	}else{
				    		alert(json.msg);
				    	}
				    }   
				});
			}
		});
		$("#clearBtn").linkbutton({
			text : "重置",
			onClick : function(){
				$("#ff").form("clear");
			}
		});
	});
</script>
<body>
    <div id="panel" style="width:100%;max-width:620px;padding:30px 60px;">
        <form id="ff" method="post">
            <div style="margin-bottom:20px">
                <input name="examName" style="width:100%" >
            </div>
            <div style="margin-bottom:20px">
            <span>
            	<span style="width:30%">
                	<input name="startTime" >
            	</span>
            	<span style="width:30%;margin-left: 4px;">
	                <input name="endTime"  >
            	</span>
            </span>
            </div>
            <div style="margin-bottom:20px">
                <input name="examTotal">
            </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a id="saveBtn" style="width:80px"></a>
            <a id="clearBtn" style="width:80px"></a>
        </div>
    </div>
</body>
</html>