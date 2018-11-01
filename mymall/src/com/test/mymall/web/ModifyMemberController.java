package com.test.mymall.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.dao.MemberDao;
import com.test.mymall.vo.Member;


@WebServlet("/ModifyMemberController")
public class ModifyMemberController extends HttpServlet {
	private MemberDao memberDao;
	//수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ModifyMemberController.doGet()");
		//로그인 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			Member searchMember = new Member();
			String id = request.getParameter("id");
			System.out.println(id);
			searchMember.setId(id);
			System.out.println(searchMember.getId());
			this.memberDao = new MemberDao();
			System.out.println("하기싫어");
			Member member = memberDao.selectMember(searchMember);
			System.out.println("하기개싫어"+member.getId()+"<--"+member.getLevel());
			request.setAttribute("member", member);
			System.out.println("하기존나개싫어");
			//forward
			request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/IndexController");
		}
		
	}
	//수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ModifyMemberController.doPost()");
		//로그인 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			this.memberDao = new MemberDao();
			Member member = new Member();
			member.setId(request.getParameter("id"));
			member.setPw(request.getParameter("pw"));
			member.setLevel(Integer.parseInt(request.getParameter("level")));
			this.memberDao.modifyMember(member);
		}	
		response.sendRedirect(request.getContextPath() + "/IndexController");
	}

}
