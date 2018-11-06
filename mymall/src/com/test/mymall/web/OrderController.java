package com.test.mymall.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.service.MemberItemService;
import com.test.mymall.vo.Member;
import com.test.mymall.vo.MemberItem;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private MemberItemService memberItemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		if(member != null) {
			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			memberItemService = new MemberItemService();
			int memberNo = member.getNo();
			MemberItem memberItem = new MemberItem();
			memberItem.setItem_no(itemNo);
			memberItem.setMember_no(memberNo);
			memberItemService.addMemberItem(memberItem);
			response.sendRedirect(request.getContextPath() + "/IndexController");
		}
	}
}
