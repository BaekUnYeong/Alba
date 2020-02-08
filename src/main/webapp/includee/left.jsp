<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li><a href="#" data-command="STANDARD">StandardJSP</a></li>
	<li><a href="#" data-command="GUGUDAN">구구단</a></li>
	<li><a href="#" data-command="IMAGESTREAMING">이미지스트리밍</a></li>
	<li><a href="#" data-command="VIDEOSTREAMING">동영상스트리밍</a></li>
	<li><a href="#" data-command="CALENDAR">달력</a></li>
	<li><a href="#" data-command="SESSIONTIMER">세션타이머</a></li>
</ul>
<form>
	<input type="hidden" name="command" />
</form>
<script type="text/javascript">
	var commandTag = $("[name='command']");
	$("#left a").on("click", function(){
		let command = $(this).data("command");
		commandTag.val(command);
		commandTag.closest("form").submit();
	});
</script>











