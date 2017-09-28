package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author VerSion
 * @time 2017年9月21日下午5:50:24
 */
@SuppressWarnings("serial")
public class ValidatePsw extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String conPassword = req.getParameter("conPassword");
		if (password.equals("")) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("0");
		}else if (conPassword.equals("")) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("1");
		}else if (!(password.equals(conPassword))) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("2");
		}else {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("5");
		}

	}

	public boolean isValidPassword(String password, String conPassword) {
		return password == null || conPassword == null || password.length() < 6 || !password.equals(conPassword);
	}

}
