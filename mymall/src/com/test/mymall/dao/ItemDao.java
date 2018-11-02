package com.test.mymall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.test.mymall.commons.DBHelper;
import com.test.mymall.vo.Item;

public class ItemDao {
	
	public List<Item> selectItemList2(SqlSession sqlSession){
		return sqlSession.selectList("com.test.mymall.dao.ItemMapper.selectItemList");
	}
	public int insertItem2(SqlSession sqlSession, Item item) {
		return sqlSession.insert("", item);
	}
	
	public void insertItem(Item item) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("insert into item (no, name, price) values (?,?);");
			preparedStatement.setString(1, item.getName());
			preparedStatement.setInt(2, item.getPrice());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultset, preparedStatement, connection);
		}
	}
	
	public ArrayList<Item> selectItemList(int currentPage,int rowPerPage) {
		ArrayList<Item> itemList = new ArrayList<Item>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("SELECT no, name, price FROM item WHERE no NOT IN (SELECT item_no FROM member_item) LIMIT ? , ?");
			preparedStatement.setInt(1, (currentPage - 1) * rowPerPage);
			preparedStatement.setInt(2, rowPerPage);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Item item = new Item();
				item.setNo(resultSet.getInt(1));
				item.setName(resultSet.getString(2));
				item.setPrice(resultSet.getInt(3));
				itemList.add(item);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultSet, preparedStatement, connection);
		}
		return itemList;
	}

	public int getTotalItemCount() {
		int totalCount = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("SELECT count(*) FROM item WHERE no NOT IN (SELECT item_no FROM member_item)");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				totalCount = resultSet.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultSet, preparedStatement, connection);
		}
		return totalCount;
	}
}