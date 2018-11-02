package com.test.mymall.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.MemberItem;

public class MemberItemService {
	private MemberItemDao memberItemDao;
	public void addMemberItem(MemberItem memberItem) {
		memberItemDao = new MemberItemDao();
		Connection connection = null;
		try {
			connection = DBHelper.getConnection();
			memberItemDao.insertMemberItem(connection, memberItem);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
		
	}
	public ArrayList<HashMap<String, Object>> memberItemList(int memberNO) {
		Connection connection = null;
		ArrayList<HashMap<String, Object>> list = null;
		try {
			connection = DBHelper.getConnection();
			memberItemDao = new MemberItemDao();
			list = memberItemDao.getMemberItemList(connection, memberNO);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
		return list;
	}
}
