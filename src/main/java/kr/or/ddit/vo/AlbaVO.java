package kr.or.ddit.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import kr.or.ddit.validator.rules.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "al_id")
@ToString
@NoArgsConstructor
@Alias("albaVO")
public class AlbaVO implements Serializable{
	
	public AlbaVO(String al_id) {
		super();
		this.al_id = al_id;
	}
	
	private String al_id;
	@NotBlank(message = "이름 필수")
	private String al_name;
	@NotNull(message = "나이 필수")
	private Integer al_age;
	@NotBlank(message = "주소 필수")
	private String al_address;
	@NotBlank(message = "핸드폰번호 필수")
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String al_career;
	@NotBlank(message = "성별 필수")
	private String al_gen;
	@NotBlank(message = "혈액형 필수")
	private String al_btype;
	@NotBlank(message = "이메일 필수")
	private String al_mail;
	
	private String gr_code;
	
	private LicenseVO license;
}
