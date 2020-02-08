package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHandlerInvoker {
	/**
	 * 핸들러 객체와 핸들러 메소드를 직접 사용(호출)
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 */
	public String invokeHandler(URIMappingInfo mappingInfo, 
				HttpServletRequest req, HttpServletResponse resp);
}
