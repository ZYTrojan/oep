package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.dao.UserDao_hwx;
import com.zr.dao.impl.UserDaoImpl_hwx;

/**
*@author VerSion
*@time 2017年9月22日下午2:52:24
*/
@SuppressWarnings("serial")
public class CheckUname extends HttpServlet{
	UserDao_hwx check = new UserDaoImpl_hwx();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("uname");
		
		if (uname.equals("")) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("2");
		}
		if (!(check.UserExist(uname))) {
			
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("1");
		}
	}

}
