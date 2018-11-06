package com.test.mymall.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.service.ItemService;
import com.test.mymall.vo.Item;



@WebServlet("/DeleteItemController")
public class DeleteItemController extends HttpServlet {
	private ItemService itemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")!=null) {
			itemService = new ItemService();
			Item item = (Item)session.getAttribute("loginMember");
			//itemService.d
		}
	}
}
