package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		UserVo userVo = new UserVo("aaa", "1234", "정윤우", "male");
		

		UserDao userDao = new UserDao();
		userDao.userInsert(userVo);

	}

}
