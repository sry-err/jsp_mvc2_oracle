<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="./insertSave.do" name="">
		<table border="1">
			<tr>
				<td colspan="2">QnA Board Insert</td>
			</tr>
			<tr>
				<td>작성자 :</td>
				<td><input type="text" name="i_writer" placeholder="이름을 입력하세요"></td>
			</tr>
			<tr>
				<td>글 제목 :</td>
				<td><input type="text" name="i_title" placeholder="제목을 입력하세요"></td>
			</tr>
			<tr>
				<td>글 내용 :</td>
				<td><textarea rows="10" cols="25" name="i_content"></textarea></td>
			</tr>
			<tr>
				<td>파일 :</td>
				<td><input type="file" name="i_file"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="제출하기"></td>
			</tr>
		</table>
	</form>

</body>
</html>