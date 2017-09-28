package com.zr.action.LoginRegister;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zr.dao.ManagerDao_hwx;
import com.zr.dao.impl.ManagerDaoImpl_hwx;

/**
 * @author VerSion
 * @time 2017年9月25日上午11:09:25
 */
@SuppressWarnings("serial")
public class MLoginAction extends HttpServlet {
	ManagerDao_hwx mlogin = new ManagerDaoImpl_hwx();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String managerName = req.getParameter("mname");
		String keyINpsw = req.getParameter("mpassword");
		String identifyingcode = req.getParameter("idtfcode");

		HttpSession session = req.getSession();

		String mpsw = mlogin.Getpswbymname(managerName);
		if ((keyINpsw.equals(mpsw))&&(session.getAttribute("codevalue").equals(identifyingcode))) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("314");
		}else if(!(keyINpsw.equals(mpsw))){
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("365");
		}else if ("".equals(identifyingcode)) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("391");
		}else if (!(session.getAttribute("codevalue").equals(identifyingcode))) {
			resp.setCharacterEncoding("utf8");
			PrintWriter out = resp.getWriter();
			out.print("392");
		}
	}

}
