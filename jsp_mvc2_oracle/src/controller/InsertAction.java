package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QnaDAO;
import model.QnaDTO;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDTO qdto = new QnaDTO();
		QnaDAO qdao = new QnaDAO();
		
		qdto.setQwriter(request.getParameter("i_writer"));
		qdto.setQtitle(request.getParameter("i_title"));
		qdto.setQcontent(request.getParameter("i_content"));
		
		int result = qdao.insert(qdto);
		
		System.out.println(result > 0 ? "데이터 입력 성공" : "데이터 입력 실패");
		
	}

}
