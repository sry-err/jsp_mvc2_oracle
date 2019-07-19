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

public class ModifySaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaDTO qdto = new QnaDTO();
		QnaDAO qdao = new QnaDAO();

		String realPath = "";
		String savePath = "/upload";
		String encType = "utf-8";
		int sizeLimit = 10 * 1024 * 1024; // 10Mbps

		realPath = request.getServletContext().getRealPath(savePath);

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realPath, sizeLimit, encType, new DefaultFileRenamePolicy());
		int qnum = Integer.parseInt(multi.getParameter("cnum"));
		qdto.setQnum(qnum);
		qdto.setQwriter(multi.getParameter("i_writer"));
		qdto.setQtitle(multi.getParameter("i_title"));
		qdto.setQcontent(multi.getParameter("i_content"));
		String old_file_name = multi.getParameter("old_file");

		String old_fileExt = old_file_name.substring(old_file_name.lastIndexOf(".") + 1, old_file_name.length());
		boolean isImage_old = (old_fileExt.equalsIgnoreCase("PNG") || old_fileExt.equalsIgnoreCase("JPG")
				|| old_fileExt.equalsIgnoreCase("GIF") || old_fileExt.equalsIgnoreCase("JPEG"));

		String old_thumbnailName = "";
		if (isImage_old) {
			old_thumbnailName = "s_" + old_file_name;
		}

		Enumeration files = multi.getFileNames();

		String fileName = "";
		if (files.hasMoreElements()) {
			System.out.println(">>> check 1");
			String paramName = (String) files.nextElement();
			System.out.println(">>> paramName : " + paramName);
			fileName = multi.getFilesystemName(paramName);
			System.out.println(">>> check 2");
			System.out.println(">>> fileName : " + fileName);
			
			if (fileName == null) {
				System.out.println(">>> check 3");
				qdto.setFilename(old_file_name);
				qdto.setThumbnail(old_thumbnailName);
			} else {
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

				boolean isImage = (fileExt.equalsIgnoreCase("PNG") || fileExt.equalsIgnoreCase("JPG")
						|| fileExt.equalsIgnoreCase("GIF") || fileExt.equalsIgnoreCase("JPEG"));
				if (isImage) {
					String thumbnailPath = realPath + "\\s_" + fileName;
					int thumbWidth = 96;
					int thumbHeight = 96;
					Image thumbnailImage = JimiUtils.getThumbnail((realPath + "/" + fileName), thumbWidth, thumbHeight,
							Jimi.IN_MEMORY);
					Jimi.putImage(thumbnailImage, thumbnailPath);
					String thumbnailFileName = thumbnailPath.substring(realPath.length() + 1);
					qdto.setThumbnail(thumbnailFileName);
				}
				qdto.setFilename(fileName);
			}

		}

		int result = qdao.modify(qdto);
		if (result == 0) {
			System.out.println("데이터 수정 실패");
		}

		request.setAttribute("cnum", qnum);
	}

}
