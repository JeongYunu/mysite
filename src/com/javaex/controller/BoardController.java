package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.GuestBookDao;
import com.javaex.dao.UserDao;
import com.javaex.utill.WebUtill;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestBookVo;
import com.javaex.vo.UserVo;

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
							
			//da join 리스트
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getBoardList();

			//어트리뷰트
			request.setAttribute("boardList", boardList);
			
			//에러?
			System.out.println("여기서에러?");

			//포워드
			WebUtill.forword(request, response, "./WEB-INF/views/board/list.jsp");
			
		}else if("writeForm".equals(action)) {
			
							//결과확인
							System.out.println("[BoardController.writeForm]");
					
			//writeForm 포워드
			WebUtill.forword(request, response, "./WEB-INF/views/board/writeForm.jsp");
			
		}else if("write".equals(action)) {
			
							//결과확인
							System.out.println("[BoardController.write]");
							
			//세션 no소환
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
			
							//세션no
							System.out.println("session no=" + no);
			
			
			//파라미터 추출				
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			//board db insert
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setHit(0);
			boardVo.setNo(no);
			boardDao.boardInsert(boardVo);
			
			//리다이렉트
			WebUtill.redirect(request, response, "/mysite/board?action=list");
			
		}else if("read".equals(action)) {

			//파라미터
			int no = Integer.parseInt(request.getParameter("no"));
			
			//db users board join
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getRead(no);
			
			//어트리뷰트
			request.setAttribute("boardRead", boardVo);
			
			//포워드
			WebUtill.forword(request, response, "/WEB-INF/views/board/read.jsp");
			
		}else if("mform".equals(action)) {
			
			//파라미터 보드넘버
			int no = Integer.parseInt(request.getParameter("no"));
			
			//db join
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getRead(no);
			
			//어트리뷰트
			request.setAttribute("boardUpdate", boardVo);
			
			//포워드
			WebUtill.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
			
		}else if("modify".equals(action)) {
			
			//파라미터
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("boardNo"));
			
			//db update
			BoardDao boardDao = new BoardDao();
			boardDao.boardUpdate(title, content, no);
			
			//리다이렉트
			WebUtill.redirect(request, response, "/mysite/board?action=list");
			
		}else if("delete".equals(action)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao boardDao = new BoardDao();
			boardDao.listDelete(no);
			
			//리다이렉트
			WebUtill.redirect(request, response, "/mysite/board?action=list");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
