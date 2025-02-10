package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdMapperImpl implements ProdMapper {
	//팩토리 생성
	private SqlSessionFactory sqlSessionFactory
		= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectTotalRecord(PaginationInfo<ProdVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.selectTotalRecord(paging);
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PaginationInfo<ProdVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.selectProdList(paging);
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

	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.insertProd(prod);
		}
			
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			ProdMapper proxy = sqlSession.getMapper(ProdMapper.class);
			return proxy.updateProd(prod);
		}
	}

}
