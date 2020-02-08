package kr.or.ddit.mvc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) {
		Object handler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		try {
			String viewName = (String) handlerMethod.invoke(handler, req, resp);
			return viewName;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
