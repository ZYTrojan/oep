package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

/**
 * 设置考题
 * 获取考试id,考题id数组
 * @author Kramer
 *
 */
public class SetQuestionAction extends HttpServlet{
	ExamService es = new ExamServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//获取正在配置的考试id
		/**
		 * 方便调试,记得要改！！！！！
		 * int currentExamId = (int)req.getSession().getAttribute("currentExamId");
		 */
		int currentExamId = (int)req.getSession().getAttribute("currentExamId");
		//int currentExamId = 1;
		int score = Integer.parseInt(req.getParameter("score")); 
		String[] questionIdsString = req.getParameterValues("questionId[]");
		int[] questionIds = new int[questionIdsString.length];
		int i = 0;
		for(String ss:questionIdsString){
			questionIds[i++] = Integer.parseInt(ss);
		}
		boolean result = es.setExamQuestion(currentExamId,questionIds,score);
		resp.getWriter().write(String.valueOf(result));
	}
	
}
