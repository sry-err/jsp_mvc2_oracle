package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QnaDAO implements DAO {

	DataSource ds;
	Connection cn;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;

	public QnaDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			System.out.println(">>> DBCP Fail");
			e.printStackTrace();
		}
	}

	@Override
	public int insert(QnaDTO qdto) {
		String sql = "insert into tbl_qna" + "(qnum, qwriter, qtitle, qcontent, qreadcount, qdate, filename, thumbnail)"
				+ "values(seq_qna_qnum.nextval, ?, ?, ?, 0, sysdate, ?, ?)";
		try {
			cn = ds.getConnection();
			pst = cn.prepareStatement(sql);
			pst.setString(1, qdto.getQwriter());
			pst.setString(2, qdto.getQtitle());
			pst.setString(3, qdto.getQcontent());
			pst.setString(4, qdto.getFilename());
			pst.setString(5, qdto.getThumbnail());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(">>> DAO/insert Fail");
			e.printStackTrace();
		} finally {
			if (pst != null) {	try {	pst.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {	try {	cn.close();	} catch (SQLException e) {	e.printStackTrace();}}
		}
		return 0;
	}
	@Override
	public ArrayList<QnaDTO> getList(){
		ArrayList<QnaDTO> list = new ArrayList<>();
		String sql = "select * from tbl_qna order by qnum desc";
		
		try {
			cn = ds.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				QnaDTO qdto = new QnaDTO();
				qdto.setQnum(rs.getInt("qnum"));
				qdto.setQwriter(rs.getString("qwriter"));
				qdto.setQtitle(rs.getString("qtitle"));
				qdto.setQcontent(rs.getString("qcontent"));
				qdto.setQreadcount(rs.getInt("qreadcount"));
				qdto.setQdate(rs.getDate("qdate"));
				qdto.setFilename(rs.getString("filename"));
				qdto.setThumbnail(rs.getString("thumbnail"));
				list.add(qdto);
				
			}
			return list;
		} catch (SQLException e) {
			System.out.println(">>> DAO/getList Fail");
			e.printStackTrace();
		}finally {
			if (st != null) {try {	st.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (rs != null) {try {	rs.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {try {  cn.close();} catch (SQLException e) {	e.printStackTrace();}}
		}
		return null;
	}

	@Override
	public QnaDTO getDetail(int qnum) {
		
		QnaDTO qdto = new QnaDTO();
		String sql = "select * from tbl_qna where qnum="+qnum;
		
		try {
			cn = ds.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				qdto.setQnum(rs.getInt("qnum"));
				qdto.setQwriter(rs.getString("qwriter"));
				qdto.setQtitle(rs.getString("qtitle"));
				qdto.setQcontent(rs.getString("qcontent"));
				qdto.setQreadcount(rs.getInt("qreadcount"));
				qdto.setQdate(rs.getDate("qdate"));
				qdto.setFilename(rs.getString("filename"));
				qdto.setThumbnail(rs.getString("thumbnail"));				
			}
			return qdto;
		} catch (SQLException e) {
			System.out.println(">>> DAO/getDetail Fail");
			e.printStackTrace();
		}finally {
			if (st != null) {try {	st.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (rs != null) {try {	rs.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {try {  cn.close();} catch (SQLException e) {	e.printStackTrace();}}
		}
		return null;
	}
	
	@Override
	public int modify(QnaDTO qdto){
		String sql = "update tbl_qna set qwriter=?, qtitle=?, qcontent=?, filename=? where qnum=?";
		
		try {
			cn = ds.getConnection();
			pst = cn.prepareStatement(sql);
			pst.setString(1, qdto.getQwriter());
			pst.setString(2, qdto.getQtitle());
			pst.setString(3, qdto.getQcontent());
			pst.setString(4, qdto.getFilename());
			pst.setInt(5, qdto.getQnum());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(">>> DAO/modify Fail");
			e.printStackTrace();
		} finally {
			if (pst != null) {	try {	pst.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {	try {	cn.close();	} catch (SQLException e) {	e.printStackTrace();}}
		}		
		return 0;
	}
	
	/*@Override
	public boolean modify(QnaDTO qdto, int qnum){
		String sql = "update tbl_qna "
				+ "set qwriter = ?, qtitle = ?, qcontent = ?"
				+ "where qnum = "+qnum;
		try {
			cn = ds.getConnection();
			pst = cn.prepareStatement(sql);
			pst.setString(1, qdto.getQwriter());
			pst.setString(2, qdto.getQtitle());
			pst.setString(3, qdto.getQcontent());
			int num = pst.executeUpdate();
			if(num==1)
				return true;
		} catch (SQLException e) {
			System.out.println(">>> DAO/modify Fail");
			e.printStackTrace();
		} finally {
			if (pst != null) {	try {	pst.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {	try {	cn.close();	} catch (SQLException e) {	e.printStackTrace();}}
		}
		return false;
	}*/

	@Override
	public int remove(int qnum){
		String sql = "delete from tbl_qna where qnum="+qnum;
		
		try {
			cn = ds.getConnection();
			st = cn.createStatement();
			return st.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(">>> DAO/remove Fail");
			e.printStackTrace();
		}finally {
			if (st != null) {	try {	st.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {	try {	cn.close();	} catch (SQLException e) {	e.printStackTrace();}}
		}
		
		return 0;
	}

	@Override
	public String getFileName(int qnum){
		String fileName="";
		String sql = "select filename from tbl_qna where qnum="+qnum;
		try {
			cn = ds.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				fileName=rs.getString("filename");				
			}
			return fileName;
		} catch (SQLException e) {
			System.out.println(">>> DAO/getDetail Fail");
			e.printStackTrace();
		}finally {
			if (st != null) {try {	st.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (rs != null) {try {	rs.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {try {  cn.close();} catch (SQLException e) {	e.printStackTrace();}}
		}
		
		return null;
	}

	@Override
	public void upReadCount(int qnum) {
		String sql="update tbl_qna set qreadcount=qreadcount+1 where qnum="+qnum;
		
		try {
			cn = ds.getConnection();
			st = cn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(">>> DAO/upReadCount Fail");
			e.printStackTrace();
		}finally {
			if (st != null) {	try {	st.close();} catch (SQLException e) {	e.printStackTrace();}}
			if (cn != null) {	try {	cn.close();	} catch (SQLException e) {	e.printStackTrace();}}
		}
		
	}
	
	
}
