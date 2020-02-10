<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<style type="text/css">
	#rnum { 
		width : 100px;
		text-align: center; 
	}
	#name { 
		width : 250px;
		text-align: center; 
	}
	#age { 
		width : 150px;
		text-align: center; 
	}
	#gen { 
		width : 150px;
		text-align: center; 
	}
	#hp { 
		width : 300px;
		text-align: center; 
	}
	#spec { 
		width : 400px;
		text-align: center; 
	}
	#career { 
		width : 200px;
		text-align: center; 
	}
	#mail { 
		width : 350px;
		text-align: center; 
	}
	
	
	
</style>
</head>
<body>
	<table class="table table-bordered">
	<thead>
		<tr>
			<th id = "rnum">행번호</th>
			<th id = "name">이름</th>
			<th id = "age">나이</th>
			<th id = "gen">성별</th>
			<th id = "hp">휴대폰</th>
			<th id = "spec">특기사항</th>
			<th id = "career">직업</th>
			<th id = "mail">이메일</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
		<tr>
			<td colspan = "8">
				<div class="form-inline mb-3 justify-content-center">
					<select id="searchType" class="form-control mr-2">
						<option value="">전체</option>
						<option value="name">이름</option>
						<option value="age">나이</option>
						<option value="gen">성별</option>
						<option value="career">직업</option>
						<option value="spec">특기사항</option>
						<option value="mail">이메일</option>
					</select>
				<input type="text" id="searchWord"  class="form-control mr-2"
					value="${param.searchWord }"
				/>
				<input type="button" value="검색" id="searchBtn" class="btn btn-info"/>
				<input type="button" value="새글쓰기" onclick="location.href='<c:url value='/alba/albaInsert.do'/>'" 
										class="btn btn-info"/>
				</div>
				<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot> 
</table>

<form id="searchForm"> 
	<input type="text" name="page" />
	<input type="text" name="searchType" value="${param.searchType }"/>
	<input type="text" name="searchWord" value="${param.searchWord }"/>
</form>

<script type="text/javascript">
	var searchTypeTag = $("#searchType");
	searchTypeTag.val("${param.searchType }");
	var searchWordTag = $("#searchWord");
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let queryString = $(this).serialize();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		if(!action) action = "<c:url value='/alba/albaList.do' />";
		if(!method) method = "get";
		$.ajax({
			url:action,
			method:method,
			data : queryString,
			dataType : "json", //Accept,  Content-Type
			success : function(resp) {
				let dataList = resp.dataList;
				let trTags = [];
				if(dataList.length>0){
					$(dataList).each(function(index, alba){
						let eachTr = $("<tr>");
						eachTr.append(
							$("<td>").text(alba.rnum),	
							$("<td>").html(
								$("<a>").attr({
											"href":"<c:url value='/alba/albaView.do'/>?what="+alba.al_id
										})
										.text(alba.al_name)
							),	
							$("<td>").text(alba.al.age),	
							$("<td>").text(alba.al_gen),
							$("<td>").text(alba.al_hp),	
							$("<td>").text(alba.al_career),
							$("<td>").text(alba.al_spec),	
							$("<td>").text(alba.al_mail)
						);
						trTags.push(eachTr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr({
								colspan:"8"
							}).text("게시글 목록이 없음.")		
						)
					);
				}
				listBody.empty();
				listBody.append(trTags);
				let pagingHTML = resp.pagingHTML;
				pagingArea.empty(); 
				pagingArea.html(pagingHTML);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
		return false;
	});
	var searchBtn = $("#searchBtn").on("click", function(){
		let searchType = searchTypeTag.val();
		let searchWord = searchWordTag.val();
		searchForm.find("[name='searchType']").val(searchType); 
		searchForm.find("[name='searchWord']").val(searchWord); 
		searchForm.find("[name='page']").val(1);
		searchForm.submit();
	});
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	searchForm.submit();

</script>
</body>
</html>