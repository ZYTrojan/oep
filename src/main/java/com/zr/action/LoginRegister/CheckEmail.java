package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*@author VerSion
*@time 2017年9月21日下午8:29:08
*/
@SuppressWarnings("serial")
public class CheckEmail extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if ("".equals(email)) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("0");
		}else if (!(email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$"))) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("1");
		}else if (email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$")) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("5");
		}
	}

	
}
