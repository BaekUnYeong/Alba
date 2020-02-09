package kr.or.ddit.alba.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AlbaVO;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value="/alba/albaInsert.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) {
		return "alba/albaForm";
	}
	
	@URIMapping(value="/alba/alvaInsert.do", method=HttpMethod.POST )
	public String insert(HttpServletRequest req, HttpServletResponse resp) {
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);
		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		if(req instanceof FileUploadRequestWrapper) {
			List<PartWrapper> bo_file =
					((FileUploadRequestWrapper) req).getPartWrappers("bo_file");
			alba.setAl_spec("al_spec");;
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(alba, errors, InsertHint.class);
		String viewName = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.createAlba(alba);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/alba/albaView.do?what="+alba.getAl_id();
			}else {
				message = "서버 오류 쫌따 다시.";
				viewName = "alba/albaForm";
			}
		}else {
			viewName = "alba/albaForm";
		}
		req.setAttribute("message", message);
		
		return viewName;		
	}
}
