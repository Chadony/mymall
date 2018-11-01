package com.test.mymall.service;

import java.sql.*;
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
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBHelper.close(null, null, connection);
		}
	}
	
	public void addMember(Member member) {
		memberDao = new MemberDao();
		memberDao.insertMember(member);
	}
	public Member loginCheck(Member member) {
		Member login = null;
		memberDao = new MemberDao();
		login = memberDao.login(member);
		return login;
	}
}
