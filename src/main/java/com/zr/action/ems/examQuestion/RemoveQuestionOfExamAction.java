package com.zr.action.ems.examQuestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;
import com.zr.utils.FormatUtil;

import net.sf.json.JSONObject;
/**
 * 删除考试某些考题
 * @author Kramer
 *
 */
public class RemoveQuestionOfExamAction extends HttpServlet{
	ExamService es = new ExamServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int currentExamId = (int)req.getSession().getAttribute("currentExamId");
		String[] idsString = req.getParameterValues("ids[]");
		int[] ids = FormatUtil.StringArray2IntArray(idsString);
		boolean result = es.removeQuestionOfExamByIds(currentExamId,ids);
		
		resp.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		json.put("result", result);
		resp.getWriter().write(json.toString());
	}
}
