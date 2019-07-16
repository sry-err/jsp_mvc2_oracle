package model;

import java.util.Date;

public class QnaDTO {
	int qnum;
	String qwriter;
	String qtitle;
	String qcontent;
	int qreadcount;
	Date qdate;
	String filename;
	String thumbnail;
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getQwriter() {
		return qwriter;
	}
	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public int getQreadcount() {
		return qreadcount;
	}
	public void setQreadcount(int qreadcount) {
		this.qreadcount = qreadcount;
	}
	public Date getQdate() {
		return qdate;
	}
	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}
