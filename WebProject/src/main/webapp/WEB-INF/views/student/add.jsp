<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-12-12
  Time: 오후 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../import/importList.jsp" %>

<html>
<head>
	<title>학생 등록</title>
	<style>
      table {
        border: 1px solid #444444;
      }
      th{
        text-align: left;
      }
	</style>
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
			<th>면접그룹</th>
			<td>
				<select name="interviewGroupCode" required>
					<option value="">선택해주세요.</option>
					<c:forEach var="interviewGroup" items="${interviewGroupList}">
						<option value="${interviewGroup.interviewGroupCode}">${interviewGroup.interviewGroupName}</option>
					</c:forEach>
				</select>
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

  // 등록 및 실패 여부 알림
  let status = "${status}"
  let message = "${message}"

  if (status == "error" || status == "success") {
    alert(message);
    window.location.href = '/student/list';
  }
</script>
</body>
</html>
