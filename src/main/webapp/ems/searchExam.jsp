<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>考试管理</title>
</head>
<script type="text/javascript">
	$(function() {
		//表格
		$('#tb').datagrid({
			url : '${pageContext.request.contextPath}/ems/search',
			title : "考试查询" ,
			queryParams : {
				key : $("#searchBox").val()
			},
			pageNumber : 1,
			pageSize : 5,
			pageList : [ 1, 2 , 3, 5, 10, 15, 20 ],
			columns : [[
				{field : 'examId', title : '考试id', width : 100, align : 'center',hidden:true}, 
				{field : 'examName',title : '考试名',width : 100,align : 'center'}, 
				{field : 'examTime',title : '考试时间',width : 300,align : 'center'}, 
				{field : 'examTotal',title : '总分',width : 100,align : 'center'}, 
				{field : 'examState',title : '状态',width : 100,align : 'center'}, 
			]],
			ctrlSelect:true,
            border: true,  
			pagination : true,
			rownumbers : true,
			fitColumns : true,
	    	toolbar: '#searchtool',
	    	footer:'#ft'
	    });
		//搜索输入框
		$("#searchBox").textbox();
		//搜索按钮
		$("#searchBtn").linkbutton({
			iconCls : "icon-search",
			onClick : function(){
				//重载表格
				$('#tb').datagrid("load",{
					key : $("#searchBox").val()
				});
			}
		});
		//新增
		$("#addBtn").linkbutton({
			iconCls : "icon-add",
			plain : "true",
			text : "添加",
			onClick : function(){
				location.href = "${pageContext.request.contextPath}/ems/examDetail.jsp";
			}
		});
		//编辑
		$("#editBtn").linkbutton({
			iconCls : "icon-edit",
			plain : "true",
			text : "编辑",
			onClick : function(){
			}
		});
		
		//删除
		$("#delBtn").linkbutton({
			iconCls : "icon-remove",
			plain : "true",
			text : "删除",
			onClick : function(){
				var delrows = $('#tb').datagrid('getSelections');
				var examId = [];
				//获取所有选中的exam的id
				for(var i = 0;i < delrows.length;i++){
					examId[i] = delrows[i].examId;
				}
				//异步请求删除选中的exam
				$.ajax({
					url:'${pageContext.request.contextPath}/ems/delete',
					type:'post',
					data:{
						examId : examId
					},
					dataType : "json",
					success : function(data){
						if(data.result == true){
							//删除成功并刷新数据
							alert(data.msg);
							$('#tb').datagrid("reload");
						}else{
							alert(data.msg);
						}
					}
				});
			}
		});
	});
</script>
<body>
    <table id="tb" ></table>
    <div id="searchtool" style="height: 30px;">
    	<div style="float: right;padding:2px 5px;">
    	<input id='searchBox'/>
        <a id="searchBtn" href="#" >Search</a>
    	</div>
    </div>
    <div id="ft" style="padding:2px 5px;">
        <a id="addBtn"></a>
        <a id="editBtn"></a>
        <a id="delBtn"></a>
        <p style="float: right;font-size: 5px;margin-top: 5px;margin-bottom: 0px;">按住Ctrl可多选</p>
    </div>
</body>
</html>