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
 * @author VerSion
 * @time 2017年9月21日下午3:00:40
 */
@SuppressWarnings("serial")
public class RegisterAction extends HttpServlet {
	UserDao_hwx user = new UserDaoImpl_hwx();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String conPassword = req.getParameter("conPassword");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		
		
		
		
		boolean res = user.UserRegister(userName, password, email, telephone);
		
		resp.setCharacterEncoding("utf8");
		PrintWriter pw = resp.getWriter();
		pw.write(Boolean.toString(res));
}
	}



