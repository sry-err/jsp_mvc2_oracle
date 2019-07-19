package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QnaDAO;
import model.QnaDTO;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDAO qdao = new QnaDAO();
		ArrayList<QnaDTO> list = qdao.getList();
		
		if(list==null) {
			System.out.println("리스트 출력 실패");
		}
		
		request.setAttribute("qlist", list);
	}

}
