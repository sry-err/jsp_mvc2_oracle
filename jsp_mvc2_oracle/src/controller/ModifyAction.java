package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QnaDAO;
import model.QnaDTO;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qnum = Integer.parseInt(request.getParameter("cnum"));
		QnaDAO qdao = new QnaDAO();
		QnaDTO qdto = qdao.getDetail(qnum);
		
		if(qdto == null) {
			System.out.println("데이터(DTO) 출력 실패");
		}
		
		request.setAttribute("qdto", qdto);
	}

}
