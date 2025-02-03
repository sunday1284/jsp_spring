package hw05.mbti.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import hw05.mbti.db.CustomSqlSessionFactoryBuilder;
import hw05.mbti.vo.MbtiVO;

public class MbtiDAOImpl implements MbtiDAO {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public List<MbtiVO> MbtiList() {	
		try(
		   SqlSession session =	sqlSessionFactory.openSession();		
		){		
			List<MbtiVO> mblist = session.selectList("hw05.mbti.dao.MbtiDAO.MbtiList");

			return mblist;
		} 
		
	}

	@Override
	public MbtiVO getMbti(String MtType) {	
				
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			MbtiVO mvo = session.selectOne("hw05.mbti.dao.MbtiDAO.getMbti",MtType);		
					
			return mvo;
		} 
		
		
	}

	@Override
	public int insertMbti(MbtiVO Mbti) {	
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			int cnt = session.insert("hw05.mbti.dao.MbtiDAO.insertMbti", Mbti);
			if(cnt > 0)
				session.commit();
			return cnt;
		} 

	}

	@Override
	public int updateMbti(MbtiVO Mbti) { 
	
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			int cnt = session.update("hw05.mbti.dao.MbtiDAO.updateMbti", Mbti);
			
			if(cnt>0)
				session.commit();
			return cnt;
			
		}
		
	}

	@Override
	public int deleteMbti(String MtType) {
		try (
			SqlSession session = sqlSessionFactory.openSession();
		) {
			int cnt = session.delete("hw05.mbti.dao.MbtiDAO.deleteMbti", MtType);
			if(cnt>0)
				session.commit();
			return cnt;
		} 
		
		
		
	}

}
