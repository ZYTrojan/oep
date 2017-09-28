<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/gray/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-1.9.1.js"></script>
<!-- 引入easyui.js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<!-- 引入datagrid分类js -->
<script type="text/javascript" src="http://www.jeasyui.com/easyui/datagrid-groupview.js"></script>
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
		    onCheckAll : function(rows){
		    	getTypeData($(this));//设置题型选择框的内容
		    },
		    onCheck : function(index, row){//选择部分可用
		    	getTypeData($(this));
		    },
		    onUncheck : function(index, row){//取消设置某些考题
		    	getTypeData($(this));
		    },
		    onUncheckAll : function(rows){//取消设置全部考题
		    	getTypeData($(this));
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
		
		//设置题型选择框的内容
		function getTypeData(obj){
	    	var rows = $(obj).datagrid("getChecked");
	    	var types = [];
	    	for(var i = 0;i < rows.length;i++){
	    		var type = {
			    	typeId : rows[i].questionTypeId,
			    	typeName : rows[i].questionTypeName
		    	};
	    		types[i] = type;
	    	}
	    	$("#selectType").combobox({
				data : types
			});
		};
		//已选择的选择题
		$("#selectedQuestions").datagrid({
			title : "已选题库",
			url : '${pageContext.request.contextPath}/ems/getQuestionOfExam',
			height : 300,
		    columns:[[    
				{field:'ck',checkbox:true,align:'center'},    
				{field:'id',title:'编号',width:25,align:'center'},    
				{field:'typeId',title:'题型编号',width:50,align:'center'},
				{field:'typeName',title:'题型',width:50,align:'center'},
				{field:'content',title:'内容',width:200,align:'center'},    
				{field:'answer',title:'答案',width:50,align:'center'},
			]],
            collapsible:true,
            rownumbers:true,
            fitColumns:true,
            view : groupview,
            groupField:'typeName',
            groupFormatter:function(value,rows){
                return value + ' - ' + rows.length + ' Item(s)';
            },
			toolbar : [{
            	text:'保存',
                iconCls:'icon-save',
                //设置题
                handler:function(){
                 	var rows = $("#setQuestionType").datagrid("getRows");
                	var score = rows[index-1].questionScore;
                	var questionId = [];
                	var rows = $("#selectedQuestions").datagrid("getData").rows;
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
                }},"-",{
				text:'删除',
                iconCls:'icon-remove',
                handler:function(){	
                    var rows = $("#selectedQuestions").datagrid("getSelections");
                    var ids = [];
                    for(var i = 0;i < rows.length;i++){
                    	ids[i] = rows[i].id;
                    }
                    $.ajax({
                    	url : '${pageContext.request.contextPath}/ems/removeQuestionOfExam',
                    	type : 'post',
                    	data : {
                    		ids : ids
                    	},
                    	dataType : 'json',
                    	success : function(data){
                    		var msg; 
                    		if(data.result){
                    			msg = '删除成功';
                    		}else{
                    			msg = '删除失败';
                    		}
                            $.messager.show({
                                title:'Message',
                                msg:msg,
                                showType:'fade',
                                style:{
                                    right:'',
                                    bottom:''
                                }
                            });
                    	}
                    });
                }
			}]
		});
		//选择题查询面板
		$("#questionBank").datagrid({
			title : '题库',
			url : "${pageContext.request.contextPath}/ems/getQuestion",
			height : 308,
			queryParams : {
				questionTypeId : "",
				key :  $("#searchBox").val()
			},
			pageNumber : 1,
			pageSize : 10,
			pagination : true,
			pageList : [10,20,30,40,50],
			rownumbers : true,
			collapsible:true,
			fitColumns : true,
		    columns:[[    
				{field:'ck',checkbox:true,align:'center'},    
				{field:'id',title:'编号',width:25,align:'center'},
				{field:'typeId',title:'题型编号',width:20,align:'center'},
				{field:'typeName',title:'题型',width:50,align:'center'},
				{field:'content',title:'内容',width:200,align:'center'},    
				{field:'answer',title:'答案',width:50,align:'center'},    
			]],
	    	toolbar : '#searchtool',
		});
		$("#questionBank").datagrid("getPager").pagination({
            buttons : [{
            	text : '添加',
                iconCls : 'icon-add',
                handler : function(){
                	var tb2 = $("#questionBank");
                	var rows = tb2.datagrid("getSelections");//获取所有选择的行
                	var tb1 = $("#selectedQuestions");
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
    	            			typeId : rows[i].typeId,
    	            			typeName : rows[i].typeName,
        	        			content : rows[i].content,
            	    			answer : rows[i].answer
                			});
                		}
                	}
          			tb2.datagrid("uncheckAll");//清除勾选
                }
            }]
        }); 
		//类型选择框
		$("#selectType").combobox({  
			width : 100 ,
			valueField : 'typeId',    
		    textField : 'typeName',
		    editable : false,
		    panelHeight : 120,
		    value : '请选择题型'
		});
		//搜索输入框
		$("#searchBox").textbox({
			width : 200
		});
		//搜索按钮
		$("#searchBtn").linkbutton({
			iconCls : "icon-search",
			onClick : function(){
				var questionTypeId = $("#selectType").combobox("getValue");
				if(questionTypeId == "请选择题型")
					return;
				//重载表格
				$('#questionBank').datagrid("load",{
					questionTypeId : questionTypeId,
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
        <div data-options="region:'center'" style="width: 100%;">
       		<!-- 单选题设置面板 -->
			<div id="singleChoicePanel" title="单选题">
				<!-- 已选面板 -->
				<div>
					<table id="selectedQuestions" style="width: 100%">
					</table>
				</div>
				<!-- 题库面板 -->
				<div>
					<table id="questionBank" style="width: 100%">
					</table>
					<!-- 搜索框 -->
			   	 	<div id="searchtool" style="height: 30px;">
			    		<div style="float: right;padding:2px 5px;">
			    			<input id='selectType'/>
			    			<input id='searchBox'/>
			        		<a id="searchBtn">Search</a>
			    		</div>
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
</body>
</html>