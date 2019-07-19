package model;

import java.util.ArrayList;

public interface DAO {
	public int insert(QnaDTO qdto) throws Exception;
	public ArrayList<QnaDTO> getList() throws Exception;
	public QnaDTO getDetail(int qnum) throws Exception;
	/*public boolean modify(QnaDTO qdto, int qnum) throws Exception;*/
	public int modify(QnaDTO qdto) throws Exception;
	public int remove(int qnum) throws Exception;
	public String getFileName(int qnum) throws Exception;
	public void upReadCount(int qnum) throws Exception;
}
