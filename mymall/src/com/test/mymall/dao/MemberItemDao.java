package com.test.mymall.dao;

import java.sql.*;
import java.util.*;

public class MemberItemDao {
	public void deleteMemberItem(Connection connection, int no) {
		// PreparedStatement preparedstatement = connection.preparedStatement("");
	}
	
	// MemberItem INNER JOIN item
	public ArrayList<HashMap<String, Object>> getMemberItemList(int memberNO) {
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//String sql = "";
		/*
		select mi.no, mi.order_date, mi.item_no, i.name, i.price
		from member_item mi inner join item i
		on mi.item_no = i.no
		where mi.member_no = ?
		
		while(rs.next()) {
			// MemberItemJoinItem -> HashMap
			HashMap<String, Object> map= new HashMap<String, Object>();
			map.put("memberItemNo", rs.getInt("mi.no"));
			map.put("itemPrice", rs.getInt("i.price"));
			list.add(map);
		}
		*/
		return list;
	}
}
