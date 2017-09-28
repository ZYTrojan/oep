package com.zr.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.service.QueryScoreService;
import com.zr.service.impl.QueryScoreServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QueryScoreAction extends HttpServlet {
	QueryScoreService qss = new QueryScoreServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("query");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		if ("1".equals(a)) {
			String uname = req.getParameter("sname");
			int page = Integer.parseInt(req.getParameter("start"));
			int size = Integer.parseInt(req.getParameter("size"));
			int uid = qss.getUidByUname(uname);
			if(uid!=0){
				JSONArray l1 = qss.getExamMsg1(uname, page, size);
				int total = qss.getUserExamNum(uname);
				JSONArray ch1 = qss.getScoreByUname(uname);
				int d;
				d = total%size == 0?total/size:total/size+1;
				JSONObject json = new JSONObject();
				json.put("flag", true);
				json.put("current", page);
				json.put("total",d);
				json.put("students", l1);
				json.put("allscore", ch1);
				//System.out.println(json.toString());
				PrintWriter pw = resp.getWriter();
				pw.write(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("flag", false);
				PrintWriter pw = resp.getWriter();
				pw.write(json.toString());
			}
		}
		if ("2".equals(a)) {
			String ename = req.getParameter("ename");
			int page = Integer.parseInt(req.getParameter("start"));
			int size = Integer.parseInt(req.getParameter("size"));
			int eid = qss.getEidByEname(ename);
			if(eid!=0){
				JSONArray l2 = qss.getExamMsg2(ename, page, size);
				//System.out.println(l2);
				int total = qss.getExamUserNum(ename);
				JSONArray ch2 = qss.getScoreByEname(ename);
				int d;
				d = total%size == 0?total/size:total/size+1;
				JSONObject json = new JSONObject();
				json.put("flag2", true);
				json.put("current", page);
				json.put("total",d);
				json.put("students", l2);
				json.put("allscore2", ch2);
				PrintWriter pw = resp.getWriter();
				pw.write(json.toString());
			}else{
				JSONObject json = new JSONObject();
				json.put("flag2", false);
				PrintWriter pw = resp.getWriter();
				pw.write(json.toString());
			}
		}
	}
}
