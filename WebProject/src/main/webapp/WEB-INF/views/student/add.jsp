<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-12-12
  Time: 오후 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>학생 등록</title>
</head>
<body>
<form method='post' action="/student/add-exec">
	<table>
		<tbody>
		<tr>
			<th>학생코드</th>
			<td>
				<input type='text' name="studentCode" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>면접그룹코드</th>
			<td>
				<input type='text' name="interviewGroupCode" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type='text' name="name" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<select name="gender">
					<option value="F">여자</option>
					<option value="M" selected>남자</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type='date' name="birth" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>합격여부</th>
			<td>
				<select name="passYn">
					<option value="" selected>미정</option>
					<option value="Y">Yes</option>
					<option value="N">No</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>입학여부</th>
			<td>
				<select name="entranceYn">
					<option value="" selected>미정</option>
					<option value="Y">Yes</option>
					<option value="N">No</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>교수 친분 여부</th>
			<td>
				<select name="friendshipYn">
					<option value="Y">Yes</option>
					<option value="N" selected>No</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>교수 친분 설명</th>
			<td>
				<input type='text' name="friendshipDescription" maxlength='255'/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit">등록</button>
			</td>
		</tr>
	</table>
</form>
<script>

  // 등록에 성공하면 '/student/list'로 이동, 실패시 에러 메시지 표시
  let status = "${status}"
  let message = "${message}"

  if (status == "error") {
    alert(message);
  }
</script>
</body>
</html>
