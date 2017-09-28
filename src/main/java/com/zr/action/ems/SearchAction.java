package com.zr.action.ems;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.ExamService;
import com.zr.service.impl.ExamServiceImpl;

import net.sf.json.JSONObject;

public class SearchAction extends HttpServlet{
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
		String key = req.getParameter("key").trim();//搜索关键词
		int currenPage = Integer.valueOf(req.getParameter("page"));//当前页码
		int pageSize = Integer.valueOf(req.getParameter("rows"));//每页行数
		//业务逻辑
		//datagrid的json
		JSONObject json = es.getExamByKey(key, currenPage, pageSize);
		//返回json对象
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().println(json.toString());
	}
}
