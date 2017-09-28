package com.zr.action.ems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Exam;
import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONObject;

public class InsertAction extends HttpServlet {
	ExamService es = new ExamServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("utf-8");
		//获取参数
		String examName = req.getParameter("examName");
		String examStartTime = req.getParameter("startTime");
		String examEndTime = req.getParameter("endTime");
		int examTotal = Integer.valueOf(req.getParameter("examTotal"));
		//新增
		int result = es.insertExam(examName, examStartTime, examEndTime, examTotal);
		resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		JSONObject json = new JSONObject();
		if(result != -1){
			//将考试信息存到session
			req.getSession().setAttribute("currentExamId", result);
			json.put("result", true);
			json.put("msg", "新增考试成功！");
			json.put("url", req.getContextPath()+"/ems/setQuestion.jsp");
		}else{
			json.put("result", false);
			json.put("msg", "新增考试失败！");
		}
		pw.write(json.toString());
	}

}
