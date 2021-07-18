package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.utill.WebUtill;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//텍스트 인코딩
		request.setCharacterEncoding("UTF-8");
		
							//결과확인
							System.out.println("[BoardController]");
		
		//파라미터 값 추출
		String action = request.getParameter("action");
							//파라미터 값 확인
							System.out.println("파라미터 값: " + action);
							
		if("list".equals(action)) {
			
							//결과확인
							System.out.println("[BoardController.list]");
							
			//게시판 리스트 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/board/list.jsp");
			
		}else if("writeForm".equals(action)) {
			
							//결과확인
							System.out.println("[BoardController.writeForm]");
					
			//writeForm 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/board/writeForm.jsp");
			
		}else if("write".equals(action)) {
			
							//결과확인
							System.out.println("[BoardController.write]");
							
			//파라미터 추출				
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
							//파라미터 값 확인
							System.out.println("파라미터 값: title=" + title + ", content=" + content);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
