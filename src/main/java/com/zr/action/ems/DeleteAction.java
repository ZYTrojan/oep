package com.zr.action.ems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONObject;

public class DeleteAction extends HttpServlet{
	ExamService es = new ExamServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码
		req.setCharacterEncoding("utf-8");
		//获取参数
		String[] stringExamIds = req.getParameterValues("examId[]");
		if(stringExamIds.length == 0)
			return;
		int[] examIds = StringArray2Int(stringExamIds);
		//业务逻辑
		//根据ids删除考试,成功则返回true
		boolean result = es.deleteExamByIds(examIds);
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", result);
		if(result){
			json.put("msg", "删除成功");
		}else {
			json.put("msg", "删除失败");
		}
		//返回json对象
		pw.write(json.toString());
	}
	private static int[] StringArray2Int(String[] strings) {
		int[] ints = new int[strings.length];
		for(int i = 0;i <strings.length;i++){
			ints[i] = Integer.valueOf(strings[i]);
		}
		return ints;
	}
	
}
