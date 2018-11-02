package com.test.mymall.service;

import com.test.mymall.dao.ItemDao;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.Item;


public class ItemService {
	private ItemDao itemDao;
	private MemberItemDao memberItemDao;
	
	public void addItem(Item item) {
		itemDao = new ItemDao();
		itemDao.insertItem(item);
	}
}
