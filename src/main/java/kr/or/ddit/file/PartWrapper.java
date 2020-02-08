package kr.or.ddit.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "part")
public class PartWrapper {
	public PartWrapper(Part part){
		this.part = part;
		filename = getOriginalName(part);
		savename = UUID.randomUUID().toString();
		filesize = part.getSize();
		mime = part.getContentType();
		fancySize = FileUtils.byteCountToDisplaySize(filesize);
	}
	private Part part;
	private String filename;
	private String savename;
	private long filesize;
	private String mime;
	private String fancySize;
	
	public byte[] getBytes() throws IOException {
		try(
			InputStream inputStream = part.getInputStream();
		){		
			return IOUtils.toByteArray(inputStream);
		}
	}
	
	public void saveFile(File saveFolder) throws IOException {
		File saveFile = new File(saveFolder, savename);
		try(
			InputStream input = part.getInputStream();
			FileOutputStream output = new FileOutputStream(saveFile);	
		){
			IOUtils.copy(input, output);
		}
	}
	
	public void delete() throws IOException {
		part.delete();
	}
	
	private String getOriginalName(Part part) {
		String disposition = part.getHeader("Content-Disposition");
		int fromIndex = disposition.indexOf("filename=");
		int qtFirst = disposition.indexOf('"', fromIndex);
		int qtLast = disposition.indexOf('"', qtFirst+1);
		return disposition.substring(qtFirst+1, qtLast);
	}
}
