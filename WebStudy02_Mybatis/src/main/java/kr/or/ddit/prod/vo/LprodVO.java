package kr.or.ddit.prod.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

//부모를 중심으로 보면 Has Many
// 자식   			Has A
@Data
public class LprodVO implements Serializable{
	private Long lprodId;
	private String lprodGu;
	private String lprodNm;
	
	private List<ProdVO> prodList; // Has Many
	private List<BuyerVO> buyerList; // Has Many
}
