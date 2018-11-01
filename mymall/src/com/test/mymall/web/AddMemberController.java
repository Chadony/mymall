package com.test.mymall.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mymall.dao.MemberDao;
import com.test.mymall.vo.Member;

@WebServlet("/AddMemberController")
public class AddMemberController extends HttpServlet {
	// 1. 라우터
	// 2. 모델 호출
	// 3. 뷰 렌더링
	private MemberDao memberDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddMemberController.doGet()");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberDao = new MemberDao();
		Member member = new Member();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int level = Integer.parseInt(request.getParameter("level"));
		member.setId(id);
		member.setPw(pw);
		member.setLevel(level);
		memberDao.insertMember(member);
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}
