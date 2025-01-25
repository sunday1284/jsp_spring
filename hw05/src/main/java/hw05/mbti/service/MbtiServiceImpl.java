package hw05.mbti.service;

import java.util.List;

import hw05.mbti.dao.MbtiDAO;
import hw05.mbti.dao.MbtiDAOImpl;
import hw05.mbti.vo.MbtiVO;

public class MbtiServiceImpl implements MbtiService{

	private MbtiDAO dao = new MbtiDAOImpl();
	
	@Override
	public List<MbtiVO> MbtiList() {
		return dao.MbtiList();
	}

	@Override
	public MbtiVO getMbti(String MtType) {
		return dao.getMbti(MtType);
	}

	@Override
	public int insertMbti(MbtiVO Mbti) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMbti(MbtiVO Mbti) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMbti(String MtType) {
		// TODO Auto-generated method stub
		return 0;
	}

}
