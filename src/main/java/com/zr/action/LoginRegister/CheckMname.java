package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zr.dao.ManagerDao_hwx;
import com.zr.dao.impl.ManagerDaoImpl_hwx;

/**
*@author VerSion
*@time 2017年9月25日上午10:57:20
*/
@SuppressWarnings("serial")
public class CheckMname extends HttpServlet{
	ManagerDao_hwx check = new ManagerDaoImpl_hwx();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mname = req.getParameter("mname");

		if (mname.equals("")) {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("2");
		}
		if (!(check.ManagerExist(mname))) {
			
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("1");
		}
	}

	
}
