package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONObject;
/**
 * 获取考试基本信息
 * @author Kramer
 *
 */
public class GetExamBaseInfoAction extends HttpServlet {
	ExamService es = new ExamServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 调试使用，记得要改！！！！！！
		 * 
		 */
		int currentExamId = (int)req.getSession().getAttribute("currentExamId");
//		int currentExamId = 1;
		req.getSession().setAttribute("currentExamId", currentExamId);
		JSONObject exam = es.getExamById(currentExamId);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(exam.toString());
	}
}
