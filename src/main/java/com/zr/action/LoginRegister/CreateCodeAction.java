package com.zr.action.LoginRegister;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author VerSion
 * @time 2017年9月26日下午3:35:53
 */
@SuppressWarnings("serial")
public class CreateCodeAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		// 告诉客户端，输出的格式
		resp.setContentType("image/jpeg");

		int width = 80;
		int height = 40;
		int lines = 10;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.getGraphics();
		String x = new String();

		// 设置背景色
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);// 画背景
		// 填充指定的矩形。使用图形上下文的当前颜色填充该矩形

		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, 20));

		// 随机数字
		Date d = new Date();
		//System.out.println(d.getTime());
		Random r = new Random(d.getTime());
		for (int i = 0; i < 4; i++) {
			int a = r.nextInt(10);// 取10以内的整数[0，9]
			int y = 10 + r.nextInt(20); // 10~30范围内的一个整数，作为y坐标
			Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			g.setColor(c);
			g.drawString("" + a, 5 + i * width / 4, y);
			x += a;	//将随机生成的数拼接成字符串
		}
		System.err.println(x);
		session.setAttribute("codevalue",x);
		session.setMaxInactiveInterval(60);
		
		// 干扰线
		for (int i = 0; i < lines; i++) {
			Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			g.setColor(c);
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}

		g.dispose();// 类似于流中的close()带动flush()---把数据刷到img对象当中

		ImageIO.write(img, "JPG", resp.getOutputStream());
	}
}
