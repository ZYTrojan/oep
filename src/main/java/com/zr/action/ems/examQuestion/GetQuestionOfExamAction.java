package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONArray;

/**
 * 获取某门考试的试题
 * @author Kramer
 *
 */
public class GetQuestionOfExamAction extends HttpServlet{
	ExamService es = new ExamServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int currentExamId = (int)req.getSession().getAttribute("currentExamId");
		JSONArray json = es.getQuestionOfExam(currentExamId);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json.toString());
	}
	
}
