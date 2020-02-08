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
	private String al_name;
	private int al_age;
	private String al_address;
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String al_career;
	private String al_gen;
	private String al_btype;
	private String al_mail;
	
	private String gr_code;
}
