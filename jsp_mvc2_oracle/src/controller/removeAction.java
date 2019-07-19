package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QnaDAO;

public class removeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qnum = Integer.parseInt(request.getParameter("cnum"));

		QnaDAO qdao = new QnaDAO();
		
		String fileName=qdao.getFileName(qnum);
		int result = qdao.remove(qnum);
		
		if(fileName!=null) {
			String realFilePath=request.getServletContext().getRealPath("/upload/")+fileName;
			
			File realFile = new File(realFilePath);
			
			if(realFile.exists()) {
				realFile.delete();
			}
		}
		
		System.out.println(result > 0 ? "데이터 삭제 성공" : "데이터 삭제 실패");

	}

}
