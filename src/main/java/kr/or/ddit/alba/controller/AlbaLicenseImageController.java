package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class AlbaLicenseImageController {
	@URIMapping(value = "/alba/albaLicense.do")
	public String imageUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		LicenseVO license = new LicenseVO();
		//setAttribute할것. 참조형
		
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = 
					((FileUploadRequestWrapper) req).
					getPartWrapper("??");//chrome F12 활용할것.
			byte[] lic_img = imageFile.getBytes();
			license.setLic_img(lic_img);
		}
		
		return null;
	}
}

/*
@URIMapping(value="/member/memberInsert.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "member/memberForm";
		return goPage;
	}

	@URIMapping(value="/member/memberInsert.do", method = HttpMethod.POST)
	public String insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청 분석 (파라미터 검증 포함)
		MemberVO member = new MemberVO(); // command object
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = ((FileUploadRequestWrapper) req).getPartWrapper("mem_image");
			byte[] mem_img = imageFile.getBytes();
			member.setMem_img(mem_img);
		}
		
		Map<String, List<CharSequence>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		GeneralValidator validator = new GeneralValidator();
		boolean valid = validator.validate(member, errors, InsertHint.class);
		String goPage = null;
		String message = null;
		if (valid) {
//		2. 로직 선택 service.createMember(member)
			ServiceResult result = service.createMember(member);
//		3. 로직의 실행 결과에 따른 분기 페이지 선택
			switch (result) {
			case OK:
				goPage = "redirect:/";
				break;
			case PKDUPLICATED:
				message = "아이디 중복";
				goPage = "member/memberForm";
				break;

			default: // FAIL
				message = "서버 오류, 잠시뒤 다시 .";
				goPage = "member/memberForm";
				break;
			}
		} else {
			goPage = "member/memberForm";
		}
		req.setAttribute("message", message);
//		4. 모델 데이터 공유
//		5. 해당 뷰로 이동
		return goPage;
	}
*/