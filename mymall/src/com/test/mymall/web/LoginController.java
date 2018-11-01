package com.test.mymall.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.service.MemberService;
import com.test.mymall.vo.Member;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController.doGet()");
		if(request.getSession().getAttribute("loginMember") == null) {
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("/IndexController");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController.doPost()");
		request.setCharacterEncoding("utf-8");
		// 1. ID, PASSWORD
		Member memberCheck = new Member();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		memberCheck.setId(id);
		memberCheck.setPw(pw);
		MemberService memberService = new MemberService();
		Member member = memberService.loginCheck(memberCheck);
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			response.sendRedirect(request.getContextPath()+"/IndexController");
		}else {
			response.sendRedirect(request.getContextPath()+"/LoginController");
		}
		
	}

}
