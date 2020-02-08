package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

/**
 * 1. 특정 패키지 내의 모든 핸들러에 대한 정보를 수집하고 Map으로 관리.
 * 		parseBasepackage
 * 2. handlerMap을 바탕으로 특정 요청을 처리할 수 있는 핸들러 검색.
 * 		findCommandHandler
 *
 */
public class HandlerMapper implements IHandlerMapper {
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class);
	
	private Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	public HandlerMapper(String...basePackages){
		handlerMap = new LinkedHashMap<>();
		if(basePackages==null || basePackages.length==0) return;
		parseBasePackage(basePackages);
	}

	private void parseBasePackage(String...basePackages) {
		Map<Class<?>, Annotation> resultMap =
				ReflectionUtils.getClassesWithAnnotationAtBasePackages(CommandHandler.class, basePackages);
		for(Entry<Class<?>, Annotation> entry : resultMap.entrySet()) {
			Class<?> handlerType = entry.getKey();
			Map<Method, Annotation> methodMap = ReflectionUtils.getMethodsWithAnnotationAtClass(handlerType, URIMapping.class, 
					String.class, HttpServletRequest.class, HttpServletResponse.class);
			if(methodMap.size()==0) continue;
			Object commandHandler;
			try {
				commandHandler = handlerType.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			for(Entry<Method, Annotation> mtdEntry : methodMap.entrySet()) {
				Method handlerMethod = mtdEntry.getKey();
				URIMapping mtdAnnotation = (URIMapping) mtdEntry.getValue();
				String uri = mtdAnnotation.value();
				HttpMethod httpMethod = mtdAnnotation.method();
				URIMappingCondition mappingCondition = new URIMappingCondition(uri, httpMethod);
				URIMappingInfo mappingInfo = new URIMappingInfo(mappingCondition, commandHandler, handlerMethod);
				handlerMap.put(mappingCondition, mappingInfo);
				logger.info("{} : {}", mappingInfo, mappingCondition);
			}
		}// outer for end
	}

	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = getRealativeContextPath(req);
		HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase());
		URIMappingCondition key = new URIMappingCondition(uri, method);
		return handlerMap.get(key);
	}

	private String getRealativeContextPath(HttpServletRequest req) {
		int cPLength = req.getContextPath().length();
		String uri = req.getRequestURI().split(";")[0];
		uri = uri.substring(cPLength);
		return uri;
	}
}









