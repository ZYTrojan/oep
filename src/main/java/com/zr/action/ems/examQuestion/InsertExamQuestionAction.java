package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Exam;
import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONObject;

public class InsertExamQuestionAction extends HttpServlet {
	ExamService es = new ExamServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		/**
		 * 方便调试,记得要改！！！！
		 * 
		 * int currentExamId = req.getSession().getAttribute("currentExamId");
		 */
		
		int currentExamId = (int)req.getSession().getAttribute("currentExamId");
		//int currentExamId = 1;
		JSONObject exam = es.getExamById(currentExamId);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(exam.toString());
		
	}
}
