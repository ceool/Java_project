<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-11-30
  Time: 오후 6:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="import/importList.jsp" %>

<html>
<head>
	<title>청탁학생 여부</title>
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

<h3>총 입학 학생 : ${fn:length(studentList)}명</h3>
<br>

<div><a href="/student/list"><button>전체 학생 리스트 보기</button></a> : 등록 및 수정, 삭제 가능</div>
<div><a href="/professor/list"><button>교수 리스트 보기</button></a></div>
<div><a href="/interview/list"><button>인터뷰 그룹 리스트 보기</button></a></div>
<br>
<strong>청탁 학생 리스트</strong><br>
 - 입학 전에, 교수님실에 방문한 학생입니다.
<table>
	<thead>
	<th>학생코드</th>
	<th>이름</th>
	<th>면접그룹이름</th>
	<th>성별</th>
	<th>교수 친분 여부</th>
	<th>교수 친분 설명</th>
	<th>청탁학생 여부</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty request3List}">
			<c:forEach var="list" items="${request3List}">
				<tr>
						<%--				<td>${list.interviewGroupCode}</td>--%>
					<td>${list.studentCode}</td>
					<td>${list.name}</td>
					<td>${list.interviewGroupName}</td>
					<td>${list.gender}</td>
					<td>${list.friendshipYn}</td>
					<td>${list.friendshipDescription}</td>
					<td>청탁학생</td>
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

<br>
<br>

<strong>청탁 의심 리스트</strong><br>
- 방문기록에 학생 코드는 없지만, 입학 전에 교수님실에 방문한 외부인 중, 이름과 생일이 동일한 학생입니다.
<table>
	<thead>
	<th>학생코드</th>
	<th>이름</th>
	<th>면접그룹이름</th>
	<th>성별</th>
	<th>교수 친분 여부</th>
	<th>교수 친분 설명</th>
	<th>청탁학생 여부</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty request2List}">
			<c:forEach var="list" items="${request2List}">
				<tr>
						<%--				<td>${list.interviewGroupCode}</td>--%>
					<td>${list.studentCode}</td>
					<td>${list.name}</td>
					<td>${list.interviewGroupName}</td>
					<td>${list.gender}</td>
					<td>${list.friendshipYn}</td>
					<td>${list.friendshipDescription}</td>
					<td>청탁의심</td>
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

<br>
<br>

<strong>청탁 주의 리스트</strong><br>
- 교수와 친분이 있는 학생으로 주의해야하는 학생입니다. <br>
- 방문기록에 학생 코드는 없고 교수님실에 방문하지 않았지만, 입학전에 방문한 외부인 중 이름과 생년월일 동일한 학생입니다.

<table>
	<thead>
	<th>학생코드</th>
	<th>이름</th>
	<th>면접그룹이름</th>
	<th>성별</th>
	<th>교수 친분 여부</th>
	<th>교수 친분 설명</th>
	<th>청탁학생 여부</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty request1List}">
			<c:forEach var="list" items="${request1List}">
				<tr>
						<%--				<td>${list.interviewGroupCode}</td>--%>
					<td>${list.studentCode}</td>
					<td>${list.name}</td>
					<td>${list.interviewGroupName}</td>
					<td>${list.gender}</td>
					<td>${list.friendshipYn}</td>
					<td>${list.friendshipDescription}</td>
					<td>청탁주의</td>
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

<br>
<br>

<strong>정상 입학 학생 리스트</strong><br>
- 의심되는 것이 없는 정상 입학 학생입니다.
<table>
	<thead>
	<th>학생코드</th>
	<th>이름</th>
	<th>면접그룹이름</th>
	<th>성별</th>
	<th>교수 친분 여부</th>
	<th>교수 친분 설명</th>
	<th>청탁학생 여부</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty request0List}">
			<c:forEach var="list" items="${request0List}">
				<tr>
						<%--				<td>${list.interviewGroupCode}</td>--%>
					<td>${list.studentCode}</td>
					<td>${list.name}</td>
					<td>${list.interviewGroupName}</td>
					<td>${list.gender}</td>
					<td>${list.friendshipYn}</td>
					<td>${list.friendshipDescription}</td>
					<td>정상</td>
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

<br>
<br>
<br>
<strong>전체 입학 학생 리스트</strong>
<table>
	<thead>
	<th>학생코드</th>
	<th>이름</th>
	<th>면접그룹이름</th>
	<th>성별</th>
	<th>생년월일</th>
	<th>합격여부</th>
	<th>입학여부</th>
	<th>교수 친분 여부</th>
	<th>교수 친분 설명</th>
	<th>생성일</th>
	<th>수정일</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty studentList}">
			<c:forEach var="list" items="${studentList}">
				<tr>
						<%--				<td>${list.interviewGroupCode}</td>--%>
					<td>${list.studentCode}</td>
					<td>${list.name}</td>
					<td>${list.interviewGroupName}</td>
					<td>${list.gender}</td>
					<td>${list.birth}</td>
					<td>${list.passYn}</td>
					<td>${list.entranceYn}</td>
					<td>${list.friendshipYn}</td>
					<td>${list.friendshipDescription}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${list.createDate}" /></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${list.updateDate}" /></td>
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
