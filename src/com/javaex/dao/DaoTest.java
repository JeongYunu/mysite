package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {
		
		/*
		UserVo userVo = new UserVo("aaa", "1234", "정윤우", "male");
		
		UserDao userDao = new UserDao();
		userDao.userInsert(userVo);
		*/
		/*
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("oa8859", "1234");
		System.out.println(userVo);
		*/
		
		//db조회
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUserInfo(1);
		//유저정보확인
		System.out.println("유저정보확인: " + userVo);
	
	}

}
