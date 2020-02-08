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
				<td>${alba.alba_id}</td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<td>${alba.alba_name }</td>
			</tr>
			<tr>
				<th scope="col">나이</th>
				<td>${alba.alba_age }</td>
			</tr>
			<tr>
				<th scope="col">주소</th>
				<td>${alba.alba_address}</td>
			</tr>
			<tr>
				<th scope="col">핸드폰번호</th>
				<td>${alba.alba_hp}</td>
			</tr>
			<tr>
				<th scope="col">특이사항</th>
				<td>${alba.alba_spec}</td>
			</tr>
			<tr>
				<th scope="col">비고</th>
				<td>${alba.alba_desc}</td>
			</tr>
			<tr>
				<th scope="col">경력사항</th>
				<td>${alba.alba_career}</td>
			</tr>
			<tr>
				<th scope="col">성별</th>
				<td>${alba.alba_gen}</td>
			</tr>
			<tr>
				<th scope="col">혈액형</th>
				<td>${alba.alba_btype}</td>
			</tr>
			<tr>
				<th scope="col">이메일</th>
				<td>${alba.alba_mail}</td>
			</tr>
			</thead>
			
			<tbody>
				<tr>
					<td colspan="2">
						<c:url value="/alba/albaView.do" var="albaUpdateURL">
							<c:param name="what" value="${alba.alba_id }"/>
						</c:url>
						<input type="button" value="수정" class="btn btn-success"
							onclick="location.href='${albaUpdateURL }';"
						/>
						<input type="button" value="목록으로" class="btn btn-info" 
							onclick="location.href='<c:url value="/alba/albaList.do "/>';"
						/>
						<input type="button" value="뒤로가기" class="btn btn-info" 
							onclick="history.back();"
						/>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>