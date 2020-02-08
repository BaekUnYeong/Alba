package kr.or.ddit.file;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 파일 업로드 여부를 확인(Content-Type)하고,
 * 업로드 되는 요청이라면, request -> wrapper 로 변경.
 *
 */
public class FileUploadCheckFilter implements Filter{
	private static Logger logger = 
			LoggerFactory.getLogger(FileUploadCheckFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{} 필터 생성", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String bodyMime = req.getContentType();
		boolean isUploaded = bodyMime!=null && bodyMime.startsWith("multipart/");
		HttpServletRequest passReq = req;
		if(isUploaded) {
			passReq = new FileUploadRequestWrapper(req);
		}
		chain.doFilter(passReq, response);
	}

	@Override
	public void destroy() {
		logger.info("{} 필터 소멸", getClass().getSimpleName());
	}

}









