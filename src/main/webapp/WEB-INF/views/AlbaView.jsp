<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table class="table">
			<thead class="thead-dark">
				<tr>
				<th scope="col">알바아이디</th>
				<td>${buyer.buyer_id}</td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<td>${buyer.buyer_name }</td>
			</tr>
			<tr>
				<th scope="col">나이</th>
				<td>${buyer.buyer_lgu }</td>
			</tr>
			<tr>
				<th scope="col">주소</th>
				<td>${buyer.buyer_bank}</td>
			</tr>
			<tr>
				<th scope="col">핸드폰번호</th>
				<td>${buyer.buyer_bankno}</td>
			</tr>
			<tr>
				<th scope="col">특이사항</th>
				<td>${buyer.buyer_bankname}</td>
			</tr>
			<tr>
				<th scope="col">비고</th>
				<td>${buyer.buyer_zip}</td>
			</tr>
			<tr>
				<th scope="col">학력</th>
				<td>${buyer.buyer_add1}</td>
			</tr>
			<tr>
				<th scope="col">경력사항</th>
				<td>${buyer.buyer_add2}</td>
			</tr>
			<tr>
				<th scope="col">성별</th>
				<td>${buyer.buyer_comtel}</td>
			</tr>
			<tr>
				<th scope="col">혈액형</th>
				<td>${buyer.buyer_fax}</td>
			</tr>
			<tr>
				<th scope="col">이메일</th>
				<td>${buyer.buyer_mail}</td>
			</tr>
			
			</thead>
		</table>
	</body>
</html>