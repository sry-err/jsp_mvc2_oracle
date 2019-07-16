package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		String contextPath=request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		
		String path = uri.substring(contextPath.length());
		System.out.println("path : " + path);
		
		Action action = null;
		String destPage = null;
		if(path.equals("/qna/insertSave.do")) {
			action=new InsertAction();
			try {
				action.execute(request, response);
				destPage = "/qna/list.jsp";
			} catch (Exception e) {
				System.out.println(">>> insertAction/execute Error");
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
