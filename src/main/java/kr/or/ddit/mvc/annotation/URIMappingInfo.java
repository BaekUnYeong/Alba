package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 어떤 요청과 그 요청을 처리할 수 있는 핸들러(핸들러 객체, 핸들러 메소드)에 대한 정보 캡슐화
 *
 */
public class URIMappingInfo {
	private URIMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	public URIMappingInfo(URIMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	public URIMappingCondition getMappingCondition() {
		return mappingCondition;
	}
	public Object getCommandHandler() {
		return commandHandler;
	}
	public Method getHandlerMethod() {
		return handlerMethod;
	}
	
	@Override
	public String toString() {
		return String.format("%s.%s", commandHandler.getClass().getName(), handlerMethod.getName());
	}
}











