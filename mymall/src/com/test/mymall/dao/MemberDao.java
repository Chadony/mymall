package com.test.mymall.dao;

import com.test.mymall.vo.Member;
import com.test.mymall.commons.DBHelper;
import java.sql.*;

public class MemberDao {
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