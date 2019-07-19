package controller;

import java.awt.Image;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import model.QnaDAO;
import model.QnaDTO;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDTO qdto = new QnaDTO();
		QnaDAO qdao = new QnaDAO();
		
		String realPath="";
		String savePath="/upload";
		String encType="utf-8";
		int sizeLimit=10*1024*1024; // 10MB
		
		realPath=request.getServletContext().getRealPath(savePath);
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realPath, sizeLimit, encType, 
										new DefaultFileRenamePolicy());
		
		qdto.setQwriter(multi.getParameter("i_writer"));
		qdto.setQtitle(multi.getParameter("i_title"));
		qdto.setQcontent(multi.getParameter("i_content"));
		
		Enumeration files = multi.getFileNames();
		
		if(files.hasMoreElements()) {
			String paramName = (String)files.nextElement();
			String fileName = multi.getFilesystemName(paramName);
			
			String fileExt=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			
			boolean isImage = (fileExt.equalsIgnoreCase("PNG") ||
							fileExt.equalsIgnoreCase("JPG") ||
							fileExt.equalsIgnoreCase("GIF") ||
							fileExt.equalsIgnoreCase("JPEG"));
			
			if(isImage) {
				String thumbnailPath = realPath + "\\s_"+fileName;
				int thumbWidth=96;
				int thumbHeight=96;
				Image thumbnailImage = JimiUtils.getThumbnail((realPath+"/"+fileName), thumbWidth, thumbHeight, Jimi.IN_MEMORY);
				Jimi.putImage(thumbnailImage, thumbnailPath);
				String thumbnailFileName=thumbnailPath.substring(realPath.length()+1);
				qdto.setThumbnail(thumbnailFileName);
			}
			qdto.setFilename(fileName);
		}
		
		int result = qdao.insert(qdto);
		
		System.out.println(result > 0 ? "데이터 입력 성공" : "데이터 입력 실패");
		
	}

}
