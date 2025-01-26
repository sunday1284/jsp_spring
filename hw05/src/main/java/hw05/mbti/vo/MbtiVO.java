package hw05.mbti.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "mtType")
@Data
public class MbtiVO implements Serializable {
	private int mtSort;
	private String mtType;
	private String mtTitle;
	private String mtContent;
}
