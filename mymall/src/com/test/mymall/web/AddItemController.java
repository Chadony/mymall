package com.test.mymall.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mymall.service.ItemService;
import com.test.mymall.service.MemberService;
import com.test.mymall.vo.Item;

@WebServlet("/AddItemController")
public class AddItemController extends HttpServlet {
	private ItemService itemService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addItem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		itemService = new ItemService();
		Item item = new Item();
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		item.setName(name);
		item.setPrice(price);
		itemService.addItem(item);
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}
