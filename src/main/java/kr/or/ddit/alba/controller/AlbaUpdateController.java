package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.validator.GeneralValidator;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class AlbaUpdateController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value = "/alba/albaUpdate.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String alba_id = req.getParameter("what");
		if(StringUtils.isBlank(alba_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		AlbaVO alba = service.readAlba(alba_id);
		req.setAttribute("alba", alba);
		
		return "albaForm";
	}
	
	@URIMapping(value = "/alba/albaUpdate.do", method = HttpMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AlbaVO alba = new AlbaVO(); // command object
		LicenseVO license = new LicenseVO(); 
		req.setAttribute("alba", alba);
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
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(alba, errors, UpdateHint.class);
		String goPage = null;
		String message = null;
		if (valid) {
//		2. 로직 선택 service.modifyalba(alba)
			ServiceResult result = service.modifyAlba(alba);
//		3. 로직의 실행 결과에 따른 분기 페이지 선택
			switch (result) {
			case OK:
				goPage = "redirect:/alba/albaView.do?what="+alba.getAl_id();
				break;
			default: // FAIL
				message = "서버 오류, 잠시뒤 다시 .";
				goPage = "alba/albaForm";
				break;
			}
		} else {
			goPage = "alba/albaForm";
		}
		req.setAttribute("message", message);
//		4. 모델 데이터 공유
		return goPage;
	}
}
