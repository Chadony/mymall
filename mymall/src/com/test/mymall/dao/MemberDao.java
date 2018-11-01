package com.test.mymall.dao;

import com.test.mymall.vo.Member;
import com.test.mymall.commons.DBHelper;
import java.sql.*;

public class MemberDao {
	// 회원탈퇴
	public void deleteMember(Connection connection, int no) {
		
	}
	
	// 회원 조회하여 수정
	public void modifyMember(Member member) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE member SET pw = ?, level = ? WHERE id = ?");
			preparedStatement.setString(1, member.getPw());
			preparedStatement.setInt(2, member.getLevel());
			preparedStatement.setString(3, member.getId());
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, preparedStatement, connection);
		}	
	}
	
	// 회원 조회
	public Member selectMember(Member member) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Member searchMember = new Member();
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("SELECT no, id, pw, level from member WHERE id = ?");
			preparedStatement.setString(1, member.getId());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			searchMember.setNo(resultSet.getInt(1));
			searchMember.setId(resultSet.getString(2));
			searchMember.setPw(resultSet.getString(3));
			searchMember.setLevel(resultSet.getInt(4)); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultSet, preparedStatement, connection);
		}	
		return searchMember;
	}
	
	// 로그인 실패시 -> null
	// 로그인 성공시 -> 성공한 Member객체
	public Member login(Member memberCheck) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Member loginmember = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("SELECT id from member where id = ? and pw = ?");
			preparedStatement.setString(1, memberCheck.getId());
			preparedStatement.setString(2, memberCheck.getPw());
			resultset = preparedStatement.executeQuery();
			if(resultset.next()) {
				loginmember = new Member();
				loginmember.setId(resultset.getString("id"));
				loginmember.setPw(resultset.getString("pw"));
				loginmember.setLevel(resultset.getInt("level"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultset, preparedStatement, connection);
		}
		return loginmember;
	}
	
	public void insertMember(Member member) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = DBHelper.getConnection();
			preparedStatement = connection.prepareStatement("insert into member(id,pw,level) values(?,?,?)");
			preparedStatement.setString(1, member.getId());
			preparedStatement.setString(2, member.getPw());
			preparedStatement.setInt(3, member.getLevel());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(resultset, preparedStatement, connection);
		}
	}
}
