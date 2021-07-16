package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.utill.WebUtill;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//텍스트 인코딩
		request.setCharacterEncoding("UTF-8");
		
							//결과확인
							System.out.println("[UserController]");
		
		//파라미터 값 추출
		String action = request.getParameter("action");
							//파라미터 값 확인
							System.out.println("파라미터 값: " + action);
		
		if("joinForm".equals(action)) {		//회원가입 폼
							
							//결과확인
							System.out.println("[UserController.joinForm]");
			
			//회원가입폼 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {	//회원가입
							
							//결과확인
							System.out.println("[UserController.join]");
							
			//파라미터 꺼내기
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
							//파라미터 값 확인
							System.out.println("파라미터 값: id=" + id + ", pw=" + pw + 
									", name=" + name + ", gender=" + gender);
			
			//vo만들기
			UserVo userVo = new UserVo(id, pw, name, gender);
			
							//userVo확인
							System.out.println(userVo);
							
			//dao.insert(vo) --> db저장
			UserDao userDao = new UserDao();
			int count = userDao.userInsert(userVo);
			
			//포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/joinOk.jsp");
			
		}else if("loginForm".equals(action)) {		//로그인 폼 
			
							//결과확인
							System.out.println("[loginForm]");
							
			//포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/loginForm.jsp");
			
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
