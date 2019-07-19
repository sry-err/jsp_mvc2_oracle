package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QnaDAO;
import model.QnaDTO;

public class DetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer qnum = (Integer)request.getAttribute("cnum");
		QnaDAO qdao = new QnaDAO();
		if(qnum==null) {
			qnum = Integer.parseInt(request.getParameter("cnum"));
			qdao.upReadCount(qnum);
		}
		
		QnaDTO qdto = qdao.getDetail(qnum);
		
		if(qdto == null) {
			System.out.println("데이터(DTO) 출력 실패");
		}
		
		request.setAttribute("qdto", qdto);
		
	}

}
