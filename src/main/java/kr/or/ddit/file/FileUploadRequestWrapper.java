package kr.or.ddit.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, List<PartWrapper>> partWrapperMap;
	
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		parseRequest(request);		
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		Iterator<Part> it = parts.iterator();
		while (it.hasNext()) {
			Part part = (Part) it.next();
			String contentType = part.getContentType();
			if(contentType==null) continue;
			String partName = part.getName();
			PartWrapper wrapper = new PartWrapper(part);
			if(StringUtils.isBlank(wrapper.getFilename())) continue; 
			List<PartWrapper> already =  partWrapperMap.get(partName);
			if(already==null) {
				already = new ArrayList<PartWrapper>();
				partWrapperMap.put(partName, already);
			}
			already.add(wrapper);
		}
	}

	public Map<String, List<PartWrapper>> getPartWrapperMap() {
		return partWrapperMap;
	}
	
	public PartWrapper getPartWrapper(String partName){
		List<PartWrapper> wrappers = partWrapperMap.get(partName);
		if(wrappers!=null && wrappers.size()>0) {
			return wrappers.get(0);
		}else {
			return null;
		}
	}
	
	public List<PartWrapper> getPartWrappers(String partName){
		return partWrapperMap.get(partName);
	}
	
	public Enumeration<String> getPartNames(){
		Iterator<String> it = partWrapperMap.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
			}

			@Override
			public String nextElement() {
				return it.next();
			}
			
		};
	}
	
	public void deleteAll() throws IOException{
		for(Entry<String, List<PartWrapper>> entry : partWrapperMap.entrySet()) {
			List<PartWrapper> wrappers = entry.getValue();
			for(PartWrapper tmp : wrappers) {
				tmp.delete();
			}
		}
	}
		
}













