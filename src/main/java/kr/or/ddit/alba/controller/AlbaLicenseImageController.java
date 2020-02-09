package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.file.FileUploadRequestWrapper;
import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
/**
 * req.setAttribute("licenseVO", licenseVO);
 * req.getRequestDispatcher(req.getContentType()+"/alba/albaLicense.do")
 * 		.include(req, resp);
 * req.getAttribute("licenseVO");
 * 위와 같은 순서로 진행할 것.
 * licenseVO를 setAttribute하여 넣어준다. 혹은 넣지 않을 경우 자동 생성됨.
 * 이 컨트롤러의 Dispatcher를 호출하여 include한다.
 * req에 파일이 들어있다면 (FileUploadRequestWrapper를 instanceof로 만족할 때)
 * 이 컨트롤러가 licenseVO에 setter를 호출하여 넣어준다.
 * 그 후 req.setAttribute("licenseVO", license);를 통해 Attribute한다.
 * 사용자는 req.getAttribute("licenseVO");를 호출하면 VO를 반환받을 수 있다.
 * 
 * 이 컨트롤러를 사용하는 곳은(Insert, Update) Dao에서 Alba뿐만이 아닌
 * license와 lic_alba 테이블을 고려해야 한다. 테이블 이름 체크할것. TODO
 */
public class AlbaLicenseImageController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value = "/alba/albaLicense.do", method = HttpMethod.POST)
	public String imageUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		LicenseVO license = (LicenseVO) req.getAttribute("licenseVO");
		if(license == null) {
			license = new LicenseVO();
			//service 호출 TODO lic_code를 set해야 한다.
		}
		req.setAttribute("licenseVO", license);
		
		if(req instanceof FileUploadRequestWrapper) {
			PartWrapper imageFile = 
					((FileUploadRequestWrapper) req).
					getPartWrapper("??");//chrome F12 활용할것. TODO
			byte[] lic_img = imageFile.getBytes();
			license.setLic_img(lic_img);
		}
		
		return null;
	}
}

/*
	service에 lic_code를 생성하는 코드를 만들것.
	
	반환값이 String인가? 체크할것. TODO
	public String getNew_Lic_code();
	public String getNew_Lic_code(){
		return dao.getNew_Lic_code();
	}
	
	dao에 lic_code를 생성하는 코드를 만들것.
	
	public String getNew_Lic_code();
	public String getNew_Lic_code(){
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			return mapper.getNew_Lic_code();
		}
	}
	
	xml에 max(lic_code)+1을 반환하는 코드 생성
	
	<select id="getNew_Lic_code" resultType="String">
		SELECT MAX(lic_code)+1
		FROM license;	//TODO ?? sql 확인해볼것.
	</select>
*/