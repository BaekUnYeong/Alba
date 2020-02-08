package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

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
	
	private String lic_code;
	private String lic_name;
	
}
