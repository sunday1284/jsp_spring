package hw05.mbti.service;

import java.util.List;

import hw05.mbti.vo.MbtiVO;

public interface MbtiService {
	/**
	 * Mbti 리스트 전체를 보여줌
	 * @return 
	 */
	public List<MbtiVO> MbtiList();
	
	/**
	 * Mbti 해당 타입의 결과를 보여줌
	 * @param MtType
	 * @return
	 */	
	public MbtiVO getMbti(String MtType);
	
	
	
	/**
	 * 신규 Mbti 유형 추가
	 * @param MtVO
	 * @return 성공시 1: 실패 0
	 */
	public int insertMbti(MbtiVO Mbti);
	
	
	/**
	 * Mbti 수정 
	 * @param Mbti
	 * @return 성공시 1 실패 0
	 */
	public int updateMbti(MbtiVO Mbti);
	
	
	/**
	 * 해당 Mbti 유형 삭제
	 * @param MtType
	 * @return 성공시 1 실패 0
	 */
	public int deleteMbti(String MtType);
}
