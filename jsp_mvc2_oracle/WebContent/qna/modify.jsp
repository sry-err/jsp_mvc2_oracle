<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.QnaDTO" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
	int qnum = qdto.getQnum();
	String thumbnail = qdto.getThumbnail();
	String qtitle = qdto.getQtitle();
	String qwriter = qdto.getQwriter();
	int qreadcount = qdto.getQreadcount();
	Date qdate = qdto.getQdate();
	String qcontent = qdto.getQcontent();
	String filename = qdto.getFilename();
%>
<body>
<form action="./modifySave.do?cnum=<%=qnum %>" method="post">
	<table border="1">
		<tr>
			<th colspan="2">QnA Detail</th>
		</tr>
		<tr>
			<td>번호</td>
			<td><%=qnum %></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" value="<%=qwriter %>" name="i_writer"></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><%=qreadcount %></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><%=qdate %></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" value="<%=qtitle %>" name="i_title"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="25" rows="10" name="i_content"><%=qcontent %></textarea></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><%=filename %></td>
		</tr>
		<tr>
			<td>썸네일</td>
			<td><%=thumbnail %></td>
		</tr>	
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="수정하기제출">
			<input type="reset" value="초기화하기">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<a href="./list.do">Go List</a>&nbsp;
			</td>
		</tr>
	</table>
	</form>
</body>
</html>