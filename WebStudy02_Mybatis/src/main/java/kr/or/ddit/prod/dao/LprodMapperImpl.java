package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.vo.LprodVO;

public class LprodMapperImpl implements LprodMapper {
	private SqlSessionFactory sqlSessionFactory =
			 CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public LprodVO selectLprod(String lprodGu) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			LprodMapper proxy = sqlSession.getMapper(LprodMapper.class);
			return proxy.selectLprod(lprodGu);
		}
	}
	@Override
	public List<LprodVO> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			LprodMapper proxy = sqlSession.getMapper(LprodMapper.class);
			return proxy.selectLprodList();
		}
	}
	

}
