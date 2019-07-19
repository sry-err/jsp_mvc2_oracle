<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.QnaDTO" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- <%
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
	int qnum = qdto.getQnum();
	String thumbnail = qdto.getThumbnail();
	String qtitle = qdto.getQtitle();
	String qwriter = qdto.getQwriter();
	int qreadcount = qdto.getQreadcount();
	Date qdate = qdto.getQdate();
	String qcontent = qdto.getQcontent();
	String filename = qdto.getFilename();
%> --%>
<body>
<c:set var="dto" value="${qdto }"/>
<form name="iform" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th colspan="2">QnA Detail</th>
		</tr>
		<tr>
			<td>번호</td>
			<td>${dto.qnum }</td>
		</tr>
		<tr>
			<td>작성자</td><td><input type="text" value="${dto.qwriter }" name="i_writer" readonly="readonly" id="writer"></td>
		</tr>
		<tr>
			<td>조회수</td><td>${dto.qreadcount }</td>
		</tr>
		<tr>
			<td>작성일</td><td>${dto.qdate }</td>
		</tr>
		<tr>
			<td>제목</td><td><input type="text" value="${dto.qtitle }" name="i_title" readonly="readonly" id="title"></td>
		</tr>
		<tr>
			<td>내용</td><td><textarea cols="25" rows="10" name="i_content" readonly="readonly" id="content">${dto.qcontent }</textarea></td>
		</tr>
		<tr>
			<td>파일</td><td><input type="text" value="${dto.filename }" name="old_file" readonly></td>
		</tr>
		<tr  id="fileInput">
		</tr>
		<tr>
			<td>이미지</td><td><img src="../upload/${dto.filename }"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<a href="./list.do">Go List</a>&nbsp;
			<a href="#" id="goEdit">Go Edit</a>&nbsp;
			<a href="./remove.do?cnum=${dto.qnum }">Remove</a>&nbsp;
			</td>
		</tr>
	</table>
</form>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
  <script>
  	$('#goEdit').on("click", function(e){
  		e.preventDefault();
  		$('#writer').removeAttr("readonly");
  		$('#title').removeAttr("readonly");
  		$('#content').removeAttr("readonly");
  		$(this).remove();
  		$('table tr:last-child td a:first-child').append("\&nbsp\;\&nbsp\;\&nbsp\;<a href='#' id='ModSubmit'>Submit</a>");
  		$('#fileInput').append("<td>새파일</td><td><input type='file' name='i_file'></td>");
  	});
  	
  	$(document).on("click","#ModSubmit", function(e){
  		e.preventDefault();
  		var qnum='${qdto.qnum}';
  		$('form').attr("method", "post");
  		$('form').attr("action", "./modifySave.do?cnum="+qnum);
  		iform.submit();
  	});
  </script>
<%--   <c:remove var="${dto }"/> --%>
</body>
</html>