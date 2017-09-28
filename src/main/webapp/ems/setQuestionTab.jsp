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
<title>设置考题信息</title>
</head>
<script type="text/javascript">
	$(function(){
		//获取考试基本信息
		$.ajax({
			url : "${pageContext.request.contextPath}/ems/getExamBaseInfo",
			type : "post",
			success : function(json){
				var data = eval("("+json+")");
				var infoSpan = $("span[name='examInfo']");
				infoSpan.eq(0).html(data.examName);
				infoSpan.eq(1).html(data.examTime);
				infoSpan.eq(2).html(data.examTotal);
				infoSpan.eq(3).html(data.examState);
			}
		});
		
		//设置考题的table
		$("#setQuestionType").datagrid({
		    url:'${pageContext.request.contextPath}/ems/getQuestionType',
		    width : 305,
		    columns:[[    
		        {field:'ck',checkbox:true,align:'center'},    
		        {field:'questionTypeName',title:'题型',width:100,align:'center'},    
		        {field:'questionNumber',title:'题数',width:50,align:'center',editor:{
	                type:'numberspinner',
	                options:{
	                	min : 0,
	                	max : 100,
	                	editable: true
	                }  	
		        }},    
		        {field:'questionScore',title:'每题分数',width:75,align:'center',editor:{
	                type:'numberspinner',
	                options:{
	                	min : 0,
	                	max : 100,
	                	editable: true
	                }
		        }},    
		        {field:'questionTotal',title:'总分数',width:50,align:'center'}    
		    ]],
		    border : true,
		    //编辑单元格
		    onClickCell : function(index, field, value){
				if (endEditing()){
					var tb = $('#setQuestionType');
					tb.datagrid('selectRow', index)
							.datagrid('editCell', {index:index,field:field});
					var data = tb.datagrid("getData");
					if(previousIndex != undefined){
						var total = data.rows[previousIndex].questionNumber * data.rows[previousIndex].questionScore;
						tb.datagrid("updateRow",{
							index : previousIndex,
							row : {
								questionTotal : total,
							}
						});
					}
					editIndex = index;
				}
		    },
		    onCheckAll : function(rows){//使所有tabs可用
		    	var tabs = $("#tt");
		    	tabs.tabs("select",0);
	    		tabs.tabs("enableTab",1);
	    		tabs.tabs("enableTab",2);
	    		tabs.tabs("enableTab",3);
		    },
		    onCheck : function(index, row){//选择部分可用
		    	var tabs = $("#tt");
		    	if(index == 0){
		    		tabs.tabs("enableTab",1);
		    		tabs.tabs("select",1);
		    	}
		    	if(index == 1){
		    		tabs.tabs("enableTab",2);
		    		tabs.tabs("select",2);
		    	}
		    	if(index == 2){
		    		tabs.tabs("enableTab",3);
		    		tabs.tabs("select",3);
		    	}
		    },
		    onUncheck : function(index, row){//取消设置某些考题
		    	var tabs = $("#tt");
	    		tabs.tabs("select",0);
		    	if(index == 0){
		    		tabs.tabs("disableTab",1);
		    	}
		    	if(index == 1){
		    		tabs.tabs("disableTab",2);
		    	}
		    	if(index == 2){
		    		tabs.tabs("disableTab",3);
		    	}
		    },
		    onUncheckAll : function(rows){//取消设置全部考题
		    	var tabs = $("#tt");
	    		tabs.tabs("select",0);
	    		tabs.tabs("disableTab",1);
	    		tabs.tabs("disableTab",2);
	    		tabs.tabs("disableTab",3);
		    }
		    
		});
		//editCell方法
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			}
		});
		//是否结束编辑
		var editIndex = undefined;
		var previousIndex = undefined;
		function endEditing(){
			var tb = $('#setQuestionType');
			if (editIndex == undefined){return true}
			if (tb.datagrid('validateRow', editIndex)){
				tb.datagrid('endEdit', editIndex);
				//记录上一个编辑的索引
				previousIndex = editIndex;
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		};
		
		$("#tt").tabs({
			fit : true
		});
		//禁用选项卡
		/*
		$("#tt").tabs("disableTab",1);
		$("#tt").tabs("disableTab",2);
		$("#tt").tabs("disableTab",3);
		*/
		//已选择的选择题
		$("#singleChioceTB1").datagrid({
			height : 200,
			pageNumber : 1,
			pageSize : 10,
			pagination : true,
			pageList : [5,10,20,30,40,50],
			rownumbers : true,
			fitColumns : true,
		    columns:[[    
				{field:'ck',checkbox:true,align:'center'},    
				{field:'id',title:'id',width:20,align:'center'},    
				{field:'content',title:'内容',width:200,align:'center'},    
				{field:'answer',title:'答案',width:50,align:'center'},    
			]],
			toolbar:"#singleChoiceTB1header",
		});
		$("#singleChioceTB1").datagrid("getPager").pagination({
            buttons:[{
            	text:'保存',
                iconCls:'icon-save',
                //设置题
                handler:function(){
                	var rows = $("#setQuestionType").datagrid("getRows");
                	var index = $('#tt').tabs('getTabIndex',$("#tt").tabs("getSelected"));
                	var score = rows[index-1].questionScore;
                	var questionId = [];
                	var rows = $("#singleChioceTB1").datagrid("getData").rows;
                	for(var i = 0;i < rows.length;i++){
                		questionId[i] = rows[i].id;	
                	}
                	$.ajax({
                		url : '${pageContext.request.contextPath}/ems/setQuestion',
                		type : 'post',
                		data : {
                			questionId : questionId,
                			score : score
                		},
                		dataType : "json",
                		success : function(json){
                			alert(json);
                		}
                	});
                }
            },{
            	text:'删除',
                iconCls:'icon-remove',
                handler:function(){	
                    alert('remove');
                }
            }]
        }); 
		//选择题查询面板
		$("#singleChioceTB2").datagrid({
			url : "${pageContext.request.contextPath}/ems/getQuestion",
			height : 308,
			queryParams : {
				questionTypeId : 1,
				key :  $("#searchBox").val()
			},
			pageNumber : 1,
			pageSize : 10,
			pagination : true,
			pageList : [10,20,30,40,50],
			rownumbers : true,
			fitColumns : true,
		    columns:[[    
				{field:'ck',checkbox:true,align:'center'},    
				{field:'id',title:'id',width:20,align:'center'},    
				{field:'content',title:'内容',width:200,align:'center'},    
				{field:'answer',title:'答案',width:50,align:'center'},    
			]],
	    	toolbar : '#searchtool',
		});
		
		$("#singleChioceTB2").datagrid("getPager").pagination({
            buttons : [{
            	text:'添加',
                iconCls:'icon-add',
                handler:function(){
                	var tb2 = $("#singleChioceTB2");
                	var rows = tb2.datagrid("getSelections");//获取所有选择的行
                	var tb1 = $("#singleChioceTB1");
                	var selectedQuestion = tb1.datagrid("getData").rows;//获取已选全部题
                	for(var i = 0;i < rows.length;i++){
                		var mark = true;
                		//若已选,则不添加
                		for(var j = 0;j < selectedQuestion.length;j++){
                			if(selectedQuestion[j].id == rows[i].id){
                				mark = false;
                			}
                		}
                		if(mark){//添加行
	                		tb1.datagrid('appendRow',{
    	            			id : rows[i].id,
        	        			content : rows[i].content,
            	    			answer : rows[i].answer
                			});
                		}
                	}
          			tb2.datagrid("uncheckAll");//清除勾选
                }
            }]
        }); 
		//搜索输入框
		$("#searchBox").textbox({
			width : 200
		});
		//搜索按钮
		$("#searchBtn").linkbutton({
			iconCls : "icon-search",
			onClick : function(){
				//重载表格
				$('#singleChioceTB2').datagrid("load",{
					questionTypeId : 1,
					key : $("#searchBox").val()
				});
			}
		});
           
	});
</script>
<body>
  <div class="easyui-layout" style="width:100%;height:700px;">
        <div data-options="region:'north'" title="考试信息" style="height:110px">
        	<h2 style="float: right;">
        		<label>考试名:</label><span name='examInfo'></span>
        		<label>考试时间:</label><span name='examInfo'></span>
	        	<br/>
        		<label>考试总分:</label><span name='examInfo'></span>
        		<label>考试状态:</label><span name='examInfo'></span>
        	</h2>
        </div>
        <div  data-options="region:'east',split:false" title="选择考题类型" style="width: 307px;">
        	<!-- 设置考题 -->
			<table id="setQuestionType" >
			</table>
        </div>
        <div data-options="region:'center',title:'选题区'">
        	<div id="tt" class="easyui-tabs" style="width:700px;height:250px">
        		<div title="Welcome">
	        	<h1>请先选择考题</h1>
        		</div>
	       		<!-- 单选题设置面板 -->
				<div id="singleChoicePanel" title="单选题" style="padding:10px">
					<!-- 已选面板 -->
					<div>
					</div>
					<table id="singleChioceTB1">
					</table>
					<div id="singleChoiceTB1header" style="height: 30px;">
					   	<div style="float: left;">
					   		<p style="margin-top: 5px;">已选题库</p>
					   	</div>
					</div>
					<!-- 可选面板 -->
					<table id="singleChioceTB2">
					</table>
					<!-- 搜索框 -->
				    <div id="searchtool" style="height: 30px;">
				    	<div style="float: left;"><p style="margin-top: 5px;">题库</p></div>
				    	<div style="float: right;padding:2px 5px;">
				    		<input id='searchBox'/>
				        	<a id="searchBtn">Search</a>
				    	</div>
				    </div> 
				</div>
				<!-- 多选题设置面板 -->
				<div id="multipleChoicePanel" title="简答题" style="padding:10px">
				</div>
				<!-- 主观题设置面板 -->
				<div id="subjectiveItemPanel" title="计算题" style="padding:10px">
				</div>
			</div>
        </div>
    </div>
</body>
</html>