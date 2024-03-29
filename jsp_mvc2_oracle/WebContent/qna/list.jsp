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
<c:set var="list" value="${qlist }"/>
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
	<c:forEach var="dto" items="${list }">
	<tr>
			<td>${dto.qnum }</td>
			<td>
			<c:choose>
				<c:when test="${dto.filename != null && dto.filename != '' }">
					<c:choose>
						<c:when test="${dto.thumbnail != null && dto.thumbnail != '' }">
							<img src="../upload/${dto.thumbnail}" width="96" >
						</c:when>
						<c:otherwise>
							<img src="../upload/file_96.png" width="96">
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<img src="../upload/noentry_96.png" width="96">
				</c:otherwise>
			</c:choose>
			</td>
			<td><a href="./detail.do?cnum=${dto.qnum }">${dto.qtitle }</a></td>
			<td>${dto.qwriter }</td>
			<td>${dto.qreadcount }</td>
			<td>${dto.qdate}</td>
		</tr>
	</c:forEach>
<%-- 	<%
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
	%> --%>
		
<%-- 	<%
		}
	%> --%>
</table>
</body>
</html>