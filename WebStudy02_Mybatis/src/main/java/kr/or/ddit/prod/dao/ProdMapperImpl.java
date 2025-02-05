package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdMapperImpl implements ProdMapper {
	//팩토리 생성
	private SqlSessionFactory sqlSessionFactory
		= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public List<ProdVO> selectProdList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.selectProdList();
		}
		
	}


	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.selectProd(prodId);
		}
		
	}

}
