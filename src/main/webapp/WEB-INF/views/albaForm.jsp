<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알바생 등록</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<c:if test="${not empty message }">
	<div class='error'>${message }</div>
</c:if>
<form method="post" enctype="multipart/form-data" >
	<table class="table table-bordered">
			<tr>
				<th>알바생코드</th>
				<td colspan="2"><input class="form-control" type="text" name="al_id" required readonly
					value="${alba.al_id}" /><span class="error">${errors.al_id}</span></td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="2"><input class="form-control" type="text" name="al_name"
					value="${alba.al_name}" /><span class="error">${errors.al_name}</span></td>
			</tr>
			<tr>
				<th>나이</th>
				<td colspan="2"><input class="form-control" type="number" name="al_age"
					value="${alba.al_age}" /><span class="error">${errors.al_age}</span></td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="2"><input class="form-control" type="text" name="al_address"
					value="${alba.al_address}" /><span class="error">${errors.al_address}</span></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td colspan="2"><input class="form-control" type="text" name="al_hp"
					value="${alba.al_hp}" /><span class="error">${errors.al_hp}</span></td>
			</tr>
			<tr>
				<th>특기사항</th>
				<td>
					<div id="add_license">
						<select name="al_spec">
							<option value="">특기사항</option>
							<c:forEach items="${license}" var="licenseMap">
								<option value="${licenseMap['lic_code'] }">${licenseMap["lic_name"] }</option>
							</c:forEach>
						</select>
					    <input type="file" >
					</div>
					<div class="lic_list">
					</div>
					<span class="error">${errors.al_spec}</span>
					<div align="right">
						<input type="button" id="addBtn" class="btn btn-light" value="특기사항추가"/>
					</div>
				</td>
			</tr>
			<tr>
			
			</tr>
			<tr>
				<th>비고</th>
				<td colspan="2"><input class="form-control" type="text" name="al_desc"
					value="${alba.al_desc}" /><span class="error">${errors.al_desc}</span></td>
			</tr>
			<tr>
				<th>학력</th>
				<td colspan="2">
					<select class="custom-select" name="gr_code">
						<option value="">학력</option>
						<c:forEach items="${grade }" var="gradeMap">
							<option value="${gradeMap['gr_code'] }">${gradeMap["gr_name"] }</option>
						</c:forEach>
					</select>
					<span class="error">${errors.gr_code}</span>
				</td>
			</tr>
			<tr>
				<th>경력사항</th>
				<td colspan="2"><input class="form-control" type="text" name="al_career"
					value="${alba.al_career}" /><span class="error">${errors.al_career}</span></td>
			</tr>
			<tr>
				<th>성별</th>
				<td colspan="2">
					<div class="custom-control custom-radio custom-control-inline">
						<input type="radio" name="al_gen" value="F" class="custom-control-input" />
						<label class="custom-control-label" > 여성 </label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
						<input type="radio" name="al_gen" value="M" class="custom-control-input" />
						<label class="custom-control-label" > 남성 </label>
					</div>
					
					<span class="error">${errors.al_gen}</span>
				</td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td colspan="2">
					<div class="custom-control custom-radio custom-control-inline">
						<input class="custom-control-input" type="radio" name="al_btype" value="A"/>
						<label class="custom-control-label" > A </label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
						<input class="custom-control-input" type="radio" name="al_btype" value="A"/>
						<label class="custom-control-label" > B </label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
						<input class="custom-control-input" type="radio" name="al_btype" value="A"/>
						<label class="custom-control-label" > O </label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
						<input class="custom-control-input" type="radio" name="al_btype" value="A"/>
						<label class="custom-control-label" > AB </label>
					</div>
						
					<span class="error">${errors.al_btype}</span>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td colspan="2"><input class="form-control" type="text" name="al_mail"
					value="${alba.al_mail}" /><span class="error">${errors.al_mail}</span></td>
			</tr>
			<tr>
				<td colspan="3">
					<input class="btn btn-success" type="submit" value="저장" />
					<input class="btn btn-warning" type="reset" value="취소" />
				</td>
			</tr>
		</table>
</form>
<script type="text/javascript">
	$("#addBtn").on("click", function(){
		$("#add_license").clone().appendTo(".lic_list")
	});

</script>
</body>
</html>