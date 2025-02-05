package kr.or.ddit.prod.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BuyerVO implements Serializable{
	private String buyerId;
	private String buyerName;
	private String lprodGu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	//제조사와의 거래 품목[들!] 
	private List<ProdVO> prodList;
	// 제조사의 분류 정보 
	private LprodVO lprod; // Has A 
}
