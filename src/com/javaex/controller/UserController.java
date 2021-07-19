package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.utill.WebUtill;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 텍스트 인코딩
		request.setCharacterEncoding("UTF-8");
		
							// 결과확인
							System.out.println("[UserController]");
		
		// 파라미터
		String action = request.getParameter("action");
							// 파라미터 값
							System.out.println("파라미터 값: " + action);
		
		if("joinForm".equals(action)) {		//회원가입 폼
							
							// 결과확인
							System.out.println("[UserController.joinForm]");
			
			// 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/joinForm.jsp");
			
		}else if("join".equals(action)) {	// 회원가입
							
							// 결과확인
							System.out.println("[UserController.join]");
							
			// 파라미터
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
							// 파라미터 값
							System.out.println("파라미터 값: id=" + id + ", pw=" + pw + 
									", name=" + name + ", gender=" + gender);
			
			// vo
			UserVo userVo = new UserVo(id, pw, name, gender);
			
							// userVo
							System.out.println(userVo);
							
			// db저장
			UserDao userDao = new UserDao();
			int count = userDao.userInsert(userVo);
			
			//포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/joinOk.jsp");
			
		}else if("loginForm".equals(action)) {		// 로그인 폼 
			
							// 결과확인
							System.out.println("[UserController.loginForm]");
							
			// 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/loginForm.jsp");
			
		}else if("login".equals(action)) {		// 로그인
			
							// 결과확인
							System.out.println("[UserController.login]");
							
			// 파라미터 값		
			String id = request.getParameter("id");				
			String password = request.getParameter("pw");				
			
							// 파라미터 값
							System.out.println("파라미터 값: id=" + id + ", pw=" + password);
			
			// 로그인된 유저 id password 
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(id, password);
			
							// db id pass
							System.out.println("db 데이터" + userVo);
							
			if(userVo != null) {
				
				// 로그인데이터 == db데이터 --> 세션갱신(최소한의 데이터)
				HttpSession session = request.getSession();
				session.setAttribute("authUser", userVo);
				
				//리다이렉트
				WebUtill.redirect(request, response, "/mysite/main");
				
			}else {
				
								// 결과확인
								System.out.println("로그인 실패");
								
				//로그인 정보와 데이터 정보가 다를경우
				//출력만할거니까 리다이렉트					
				//리다이렉트
				WebUtill.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
			
			}
							
		}else if("logout".equals(action)) {		//로그아웃
			
							// 결과확인
							System.out.println("[UserController.logout]");
							
			//세션에 있는 "authUser"의 정보 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("authUser"); //지정된 이름에 해당하는 객체를 세션에서 제거한다
			session.invalidate(); //해당세션을 없애고 세션에 속해있는 값들을 없앤다
			
			//리다이렉트
			WebUtill.redirect(request, response, "/mysite/main");
			
		}else if("modifyForm".equals(action)) {
			
							//결과확인
							System.out.println("[UserController.modifyForm]");
			
			//세션 no소환
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
							
							//세션 no
							System.out.println("세션 no: " + no);
			//db조회
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUserInfo(no);
			
							//유저정보
							System.out.println("유저정보" + userVo);
							
			//어트리뷰트
			request.setAttribute("userInfo", userVo);
							
			//포워드
			WebUtill.forword(request, response, "./WEB-INF/views/user/modifyForm.jsp");
			
		}else if("modify".equals(action)) { //수정 db업데이트
			
							//결과확인
							System.out.println("[UserController.modify]");
			
			//세션 no소환
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
							
							//세션 no
							System.out.println("세션 no: " + no);
		
			//파라미터
			String pass = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");				
			
			//db update
			UserDao userDao = new UserDao();
			userDao.userUpdate(no, pass, name, gender);
			
			//세션수정
			((UserVo)session.getAttribute("authUser")).setName(name);
			
			//리다이렉트
			WebUtill.redirect(request, response, "/mysite/main");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
