package kr.or.ddit.alba.controller;

import java.io.IOException;
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
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value="/alba/albaInsert.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) {
		return "albaForm";
	}
	
	@URIMapping(value="/alba/albaInsert.do", method=HttpMethod.POST )
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AlbaVO alba = new AlbaVO();
		LicenseVO license = new LicenseVO(); 
		GradeVO grade = new GradeVO();
		
		req.setAttribute("alba", alba);
		req.setAttribute("grade", grade);
		
		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		String al_id = req.getParameter("al_id");
		String lic_code = req.getParameter("lic_code");
		license.setAl_id(al_id);
		license.setLic_code(lic_code);

		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = 
					((FileUploadRequestWrapper) req).
					getPartWrapper("upload");//chrome F12 활용할것. TODO
			byte[] lic_img = imageFile.getBytes();
			license.setLic_img(lic_img);
		}
		
		
		alba.setLicense(license);
		alba.setAl_spec("al_spec");	//?
		
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
				message = "서버 오류, 다시 시도하세요";
				viewName = "albaForm";
			}
		}else {
			viewName = "albaForm";
		}
		req.setAttribute("message", message);
		
		return viewName;		
	}
}
