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


@WebServlet("/SearchMemberController")
public class SearchMemberController extends HttpServlet {
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			Member searchMember = new Member();
			String id = request.getParameter("id");
			searchMember.setId(id);
			System.out.println("<--"+ searchMember.getId());
			memberService = new MemberService();
			Member member = memberService.searchMember(searchMember);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/WEB-INF/views/SearchMember.jsp").forward(request, response);
		}
	}

}
