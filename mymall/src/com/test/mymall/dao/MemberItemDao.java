package com.test.mymall.dao;

import java.sql.*;
import java.util.*;

public class MemberItemDao {
	public void deleteMemberItem(Connection connection, int no) throws Exception {
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member_item WHERE member_no = ?");
		preparedStatement.setInt(1, no);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	// MemberItem INNER JOIN item
	public ArrayList<HashMap<String, Object>> getMemberItemList(Connection connection, int memberNO) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		preparedStatement = connection.prepareStatement("SELECT mi.no, mi.item_no, i.name, i.price, mi.order_date FROM member_item mi inner join item i ON mi.item_no = i.no WHERE mi.member_no = ?");
		preparedStatement.setInt(1, memberNO);
		resultSet =  preparedStatement.executeQuery();
		while(resultSet.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberItemNo", resultSet.getInt(1));
			map.put("itemNO", resultSet.getInt(2));
			map.put("itemName", resultSet.getString(3));
			map.put("itemPrice", resultSet.getInt(4));
			map.put("orderDate", resultSet.getString(5));
			list.add(map);
		}
		resultSet.close();
		preparedStatement.close();
		return list;
	}
}
