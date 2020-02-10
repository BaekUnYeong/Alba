package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaDeleteController {
	
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping("/alba/albaDelete.do")
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String viewPage = null;
		
		String al_id = req.getParameter("al_id");

		if(StringUtils.isBlank(al_id)) {
			resp.sendError(400, "없는 알바번호입니다.");
			return null;
		}
		
		AlbaVO delAlba = new AlbaVO();
		delAlba.setAl_id(al_id);
		
		ServiceResult result = service.deleteAlba(delAlba);
		String message = null;
		switch (result) {
		case OK:
			viewPage = "redirect:/alba/albaList.do";
			message = "알바정보삭제 성공!!";
			break;

		default: //FAIl
			viewPage = "redirect:/alba/albaView.do?al_id="+al_id;
			message = "잠시뒤 다시 시도해 주세요";
			break;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		
		return viewPage;
	}
}
