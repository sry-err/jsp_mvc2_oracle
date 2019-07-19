<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.QnaDTO" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	ArrayList<QnaDTO> list = (ArrayList<QnaDTO>)request.getAttribute("qlist");
%>
<body>
<table border="1">
	<tr>
		<th colspan="4">Qna List</th>
		<th colspan="2"><a href="insert.jsp">글쓰기</a></th>
	</tr>
	<tr>
		<th>글번호</th>
		<th>썸네일</th>
		<th>글제목</th>
		<th>글쓴이</th>
		<th>조회수</th>
		<th>작성일</th>
	</tr>
	<%
		QnaDTO qdto = new QnaDTO();
		int qnum, qreadcount;
		String qwriter, qtitle, thumbnail;
		Date qdate;
		
		for(int i=0; i < list.size(); i++){
			qdto = list.get(i);
			qnum = qdto.getQnum();
			//thumbnail = qdto.getThumbnail();
			qtitle = qdto.getQtitle();
			qwriter = qdto.getQwriter();
			qreadcount = qdto.getQreadcount();
			qdate = qdto.getQdate();
	%>
		<tr>
			<td><%=qnum %></td>
			<td>썸네일 부분</td>
			<td><a href="./detail.do?cnum=<%=qnum %>"><%=qtitle %></a></td>
			<td><%=qwriter %></td>
			<td><%=qreadcount %></td>
			<td><%=qdate %></td>
		</tr>
	<%
		}
	%>
</table>
</body>
</html>