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
@EqualsAndHashCode(of = "gr_code")
@ToString
@NoArgsConstructor
@Alias("gradeVO")
public class GradeVO implements Serializable{
	
	String gr_code;
	String gr_name;
}
