package com.zr.action.ems.examQuestion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.model.Type;
import com.zr.service.qktemp.QuestionService;
import com.zr.service.qktemp.QuestionServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 获取所有题型
 * @author Kramer
 *
 */
public class GetQuestionTypeAction extends HttpServlet{
	QuestionService qs = new QuestionServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		List<Type> typeList = qs.getAllQuestionType();
		JSONArray typeJson = new JSONArray();
		for(Type tt:typeList){
			JSONObject temp = new JSONObject();
			temp.put("questionTypeId", tt.getT_id());
			temp.put("questionTypeName", tt.getT_name());
			temp.put("questionNumber", 0);
			temp.put("questionScore", 0);
			temp.put("questionTotal", 0);
			typeJson.add(temp);
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(typeJson.toString());
	}
}
