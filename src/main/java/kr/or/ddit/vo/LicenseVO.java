package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import kr.or.ddit.file.PartWrapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "lic_code")
@ToString
@NoArgsConstructor
@Alias("licenseVO")
public class LicenseVO implements Serializable{
	
	private String al_id;
	private String lic_code;
	
	private byte[] lic_img;
	private PartWrapper lic_image;
	
	public void setLic_image(PartWrapper lic_image) throws IOException{
		this.lic_image = lic_image;
		if(lic_image!=null) {
			lic_img = lic_image.getBytes();
		}
	}

	public String getImgBase64() {
		if(lic_img==null) return null;
		return Base64.encodeBase64String(lic_img);
	}
}
