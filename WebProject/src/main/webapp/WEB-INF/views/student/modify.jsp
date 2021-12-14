<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-12-14
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../import/importList.jsp" %>

<html>
<head>
	<title>학생 수정</title>
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
<form method='post' action="/student/modify-exec">
	<table>
		<tbody>
		<input type='hidden' name="studentCode" value="${studentVo.studentCode}" maxlength='32' required/>
		<tr>
			<th>면접그룹</th>
			<td>
				<select name="interviewGroupCode" required>
					<option value="">선택해주세요.</option>
					<c:forEach var="interviewGroup" items="${interviewGroupList}">
						<c:choose>
							<c:when test="${interviewGroup.interviewGroupCode == studentVo.interviewGroupCode}">
								<option value="${interviewGroup.interviewGroupCode}" selected>${interviewGroup.interviewGroupName}</option>
							</c:when>
							<c:otherwise>
								<option value="${interviewGroup.interviewGroupCode}">${interviewGroup.interviewGroupName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type='text' name="name" value="${studentVo.name}" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<select name="gender">
					<c:choose>
						<c:when test="${studentVo.gender == 'F'}">
							<option value="F" selected>여자</option>
							<option value="M">남자</option>
						</c:when>
						<c:otherwise>
							<option value="F">여자</option>
							<option value="M"selected>남자</option>
						</c:otherwise>
					</c:choose>
				</select>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type='date' name="birth" value="${studentVo.birth}" maxlength='32' required/>
			</td>
		</tr>
		<tr>
			<th>합격여부</th>
			<td>
				<select name="passYn">
					<c:choose>
						<c:when test="${studentVo.passYn == 'Y'}">
							<option value="">미정</option>
							<option value="Y" selected>Yes</option>
							<option value="N">No</option>
						</c:when>
						<c:when test="${studentVo.passYn == 'N'}">
							<option value="">미정</option>
							<option value="Y">Yes</option>
							<option value="N" selected>No</option>
						</c:when>
						<c:otherwise>
							<option value="" selected>미정</option>
							<option value="Y">Yes</option>
							<option value="N">No</option>
						</c:otherwise>
					</c:choose>
				</select>
			</td>
		</tr>
		<tr>
			<th>입학여부</th>
			<td>
				<select name="entranceYn">
					<c:choose>
						<c:when test="${studentVo.entranceYn == 'Y'}">
							<option value="">미정</option>
							<option value="Y" selected>Yes</option>
							<option value="N">No</option>
						</c:when>
						<c:when test="${studentVo.entranceYn == 'N'}">
							<option value="">미정</option>
							<option value="Y">Yes</option>
							<option value="N" selected>No</option>
						</c:when>
						<c:otherwise>
							<option value="" selected>미정</option>
							<option value="Y">Yes</option>
							<option value="N">No</option>
						</c:otherwise>
					</c:choose>
				</select>
			</td>
		</tr>
		<tr>
			<th>교수 친분 여부</th>
			<td>
				<select name="friendshipYn">
					<c:choose>
						<c:when test="${studentVo.friendshipYn == 'Y'}">
							<option value="Y" selected>Yes</option>
							<option value="N">No</option>
						</c:when>
						<c:when test="${studentVo.friendshipYn == 'N'}">
							<option value="Y">Yes</option>
							<option value="N" selected>No</option>
						</c:when>
					</c:choose>
				</select>
			</td>
		</tr>
		<tr>
			<th>교수 친분 설명</th>
			<td>
				<input type='text' name="friendshipDescription" value="${studentVo.friendshipDescription}" maxlength='255'/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit">수정</button>
			</td>
		</tr>
	</table>
</form>
<form method='post' action="/student/remove-exec">
	<input type='hidden' name="studentCode" value="${studentVo.studentCode}" maxlength='32' required/>
	<button type="submit">삭제</button>
</form>
<script>

  // 수정 및 삭제 여부 알림
  let status = "${status}"
  let message = "${message}"

  if (status == "error" || status == "success") {
    alert(message);
    window.location.href = '/student/list';
  }
</script>
</body>
</html>
