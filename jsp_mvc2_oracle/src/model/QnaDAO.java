package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				+ "values(seq_qna_qnum.nextval, ?, ?, ?, 0, sysdate, null, null)";
		try {
			cn = ds.getConnection();
			pst = cn.prepareStatement(sql);
			pst.setString(1, qdto.getQwriter());
			pst.setString(2, qdto.getQtitle());
			pst.setString(3, qdto.getQcontent());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(">>> DAO/insert Fail");
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
