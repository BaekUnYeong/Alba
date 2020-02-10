package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

/**
 * 현재 어플리케이션의 유일한 서블릿.
 * 모든 요청은 FrontControllerServlet 을 통해 접수.
 *
 */
public class FrontControllerServlet extends HttpServlet{
	private static Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);
	
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String[] basePackages = null;
		String pkgs = config.getInitParameter("basePackages");
		if(pkgs!=null) {
			basePackages = pkgs.split("\\s+");
		}
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		
		handlerMapper = new HandlerMapper(basePackages);
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		if(prefix!=null)
			viewProcessor.setPrefix(prefix);
		if(suffix!=null)
			viewProcessor.setSuffix(suffix);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
		if(mappingInfo==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String viewName = handlerInvoker.invokeHandler(mappingInfo, req, resp);
		if(viewName==null) {
			if(!resp.isCommitted()) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return;
		}
		viewProcessor.viewProcess(viewName, req, resp);
	}
}














