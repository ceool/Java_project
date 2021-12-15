<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-12-15
  Time: 오전 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../import/importList.jsp" %>

<html>
<head>
	<title>인터뷰 상세 리스트</title>
	<style>
      table {
        border: 1px solid #444444;
      }
      th, td, tr {
        text-align: center;
        border: 1px solid #444444;
      }
	</style>
</head>
<body>

<table>
	<thead>
	<th>인터뷰 그룹 코드</th>
	<th>인터뷰 그룹 이름</th>
	<th>교수 성명</th>
	<th>교수 성별</th>
	<th>교수실</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty interviewDetailList}">
			<c:forEach var="list" items="${interviewDetailList}">
				<tr>
					<td rowspan="${fn:length(list.professorList)+1}">${list.interviewGroupCode}</td>
					<td rowspan="${fn:length(list.professorList)+1}">${list.interviewGroupName}</td>
					<c:forEach var="childlList" items="${list.professorList}">
						<tr>
							<td>${childlList.name}</td>
							<td>${childlList.gender}</td>
							<td>${childlList.professorRoomName}</td>
						</tr>
					</c:forEach>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td>데이터가 존재하지 않습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
</table>
</body>
</html>
