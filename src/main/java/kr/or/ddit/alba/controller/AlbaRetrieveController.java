package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.utils.CookieUtils.TextType;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@CommandHandler
public class AlbaRetrieveController {
	
IAlbaService service = new AlbaServiceImpl();
	
//	paging _ search (전체, 작성자, 제목, 내용)
	@URIMapping("/alba/albaList.do")
	public String list(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String accept = req.getHeader("Accept");
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<AlbaVO> pagingVO = new PagingVO<AlbaVO>();
		pagingVO.setSearchVO(searchVO);
		
		pagingVO.setTotalRecord(service.readAlbaCount(pagingVO));
		
		pagingVO.setCurrentPage(currentPage);
		List<AlbaVO> boardList = service.readAlbaList(pagingVO);
		pagingVO.setDataList(boardList);
		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getWriter(), pagingVO);
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			viewName = "alba/albaList";
		}
		return viewName;
	}
	
	@URIMapping("/alba/albaView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String al_id = req.getParameter("who");
		if(StringUtils.isBlank(al_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		AlbaVO alba = service.readAlba(al_id);
		req.setAttribute("alba", alba);
		String view = "alba/albaView";
		return view;
	}
	

	
}

