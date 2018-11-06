package com.test.mymall.service;

import java.sql.*;

import org.apache.ibatis.session.SqlSession;

import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.MemberDao;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private MemberItemDao memberItemDao;
	
	// RemoveMemberController에서 memberService.removeMember()호출 트랜잭션
	public void removeMember(int no) {
		Connection connection = null;
		try {
			connection = DBHelper.getConnection();
			connection.setAutoCommit(false); // 자동 commit 막기
			// 1 기능
			memberDao = new MemberDao();
			memberDao.deleteMember(connection, no);
			// 2 기능
			memberItemDao = new MemberItemDao();
			memberItemDao.deleteMemberItem(connection, no);
			connection.commit();
		}catch(Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBHelper.close(null, null, connection);
		}
	}
	
	
	/*public void removeMember(int no) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
	}*/
	
	public void modifyMember(Member member) {
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			connection.setAutoCommit(false);
			memberDao.modifyMember(connection, member);
			connection.commit();
		}
		catch(Exception e) {
			try {
				connection.rollback();
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			DBHelper.close(null, null, connection);
		}
	}
	
	public Member searchMember(Member member) {
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			member = memberDao.selectMember(connection, member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
		return member;
	}
	
	public void addMember(Member member){
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			memberDao.insertMember(connection, member);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBHelper.close(null, null, connection);
		}
	}
	public Member loginCheck(Member member){
		Connection connection = null;
		Member memberCheck = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			memberCheck = memberDao.login(connection, member);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBHelper.close(null, null, connection);
		}
		return memberCheck;
	}
}
