package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.qktemp.QuestionService;
import com.zr.service.qktemp.QuestionServiceImpl;

import net.sf.json.JSONObject;

/**
 * 获取题库
 * 
 * @author Kramer
 *
 */
public class GetQuestionAction extends HttpServlet {
	QuestionService qs = new QuestionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int typeId = -1;
		if(!"".equals(req.getParameter("questionTypeId")))
			typeId = Integer.parseInt(req.getParameter("questionTypeId"));
		int currentPage = Integer.parseInt(req.getParameter("page"));
		int pageSize = Integer.valueOf(req.getParameter("rows"));
		String key = req.getParameter("key");
		JSONObject jsonQuestion = qs.getAllQuestionJsonByTypeIdAndKey(currentPage, pageSize, typeId, key);
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(jsonQuestion.toString());
	}

}
