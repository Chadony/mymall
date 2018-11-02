package com.test.mymall.dao;

import com.test.mymall.vo.Member;
import com.test.mymall.commons.DBHelper;
import java.sql.*;

public class MemberDao {
	// 회원탈퇴
	public void deleteMember(Connection connection, int no) throws Exception {
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member WHERE no = ?");
		preparedStatement.setInt(1, no);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	// 회원 조회하여 수정
	public void modifyMember(Connection connection, Member member) throws Exception {
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement("UPDATE member SET pw = ?, level = ? WHERE id = ?");
		preparedStatement.setString(1, member.getPw());
		preparedStatement.setInt(2, member.getLevel());
		preparedStatement.setString(3, member.getId());
		preparedStatement.executeUpdate();
		DBHelper.close(null, preparedStatement, null);
	}
	
	// 회원 조회
	public Member selectMember(Connection connection, Member member) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Member searchMember = new Member();
		preparedStatement = connection.prepareStatement("SELECT no, id, pw, level from member WHERE id = ?");
		preparedStatement.setString(1, member.getId());
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			searchMember.setNo(resultSet.getInt(1));
			searchMember.setId(resultSet.getString(2));
			searchMember.setPw(resultSet.getString(3));
			searchMember.setLevel(resultSet.getInt(4)); 
		}
		DBHelper.close(resultSet, preparedStatement, null);
		return searchMember;
	}
	
	// 로그인 실패시 -> null
	// 로그인 성공시 -> 성공한 Member객체
	public Member login(Connection connection, Member memberCheck) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		Member loginCheck = null;
		preparedStatement = connection.prepareStatement("SELECT no, id, level FROM member WHERE id = ? AND pw = ?");
		preparedStatement.setString(1, memberCheck.getId());
		preparedStatement.setString(2, memberCheck.getPw());
		resultset = preparedStatement.executeQuery();
		if(resultset.next()) {
			loginCheck = new Member();
			loginCheck.setNo(resultset.getInt(1));
			loginCheck.setId(resultset.getString(2));
			loginCheck.setLevel(resultset.getInt(3));
		}
		DBHelper.close(resultset, preparedStatement, null);
		return loginCheck;
	}
	
	public void insertMember(Connection connection, Member member) throws Exception {
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement("INSERT INTO member(id,pw,level) VALUES(?,?,?)");
		preparedStatement.setString(1, member.getId());
		preparedStatement.setString(2, member.getPw());
		preparedStatement.setInt(3, member.getLevel());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
