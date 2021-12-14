<%--
  Created by IntelliJ IDEA.
  User: scott
  Date: 2021-12-14
  Time: 오후 6:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../import/importList.jsp" %>

<html>
<head>
	<title>교수 리스트</title>
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

<div>총 게시글 개수 : ${pagination.listCnt}</div><br>
<table>
	<thead>
	<th>교수 코드</th>
	<th>이름</th>
	<th>교수실</th>
	<th>성별</th>
	<th>생일</th>
	<th>생성일</th>
	<th>수정일</th>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty professorList}">
			<c:forEach var="list" items="${professorList}">
				<tr>
					<td>${list.professorCode}</td>
					<td>${list.name}</td>
					<td>${list.professorRoomName}</td>
					<td>${list.gender}</td>
					<td>${list.birth}</td>
<%--					<td>${list.roomCode}</td>--%>
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
<div>
	<c:if test="${pagination.curRange ne 1 }">
		<a href="?curPage=1">[처음]</a>
	</c:if>
	<c:if test="${pagination.curPage ne 1}">
		<a href="?curPage=${pagination.prevPage}">[이전]</a>
	</c:if>
	<c:forEach var="pageNum" begin="${pagination.startPage}" end="${pagination.endPage}">
		<c:choose>
			<c:when test="${pageNum eq  pagination.curPage}">
				<span style="font-weight: bold;"><a href="?curPage=${pageNum}">${pageNum}</a></span>
			</c:when>
			<c:otherwise>
				<a href="?curPage=${pageNum}">${pageNum}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
		<a href="?curPage=${pagination.nextPage}">[다음]</a>
	</c:if>
	<c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
		<a href="?curPage=${pagination.endPage}">[끝]</a>
	</c:if>
</div>

<div>현재 페이지 : ${pagination.curPage}/${pagination.pageCnt}</div>
</body>
</html>
