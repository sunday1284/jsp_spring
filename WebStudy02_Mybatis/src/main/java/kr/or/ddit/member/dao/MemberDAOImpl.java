package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.member.vo.MemberVO;


/**
 * DATA MAPPING 시 주의사항 
 * DATE 나 TIMESTAMP 에 해당하는 포로퍼티 타입을 String으로 설정한 경우.
 * select 에서는 fomatting 이 필요 : TO_CHAR의 컬럼 알리아스가 필요함.
 * insert/update 에서는 parsing 이 필요 : TO_DATE, TO_TIMESTAMP
 */
public class MemberDAOImpl implements MemberDAO {
	//mybatis하고 의존관계 형성 -> 공장 팩토리에 연결
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MEMBER (      ");
		sql.append("	      MEM_ID          ");
		sql.append("	    , MEM_PASS        ");
		sql.append("	    , MEM_NAME        ");
		sql.append("	    , MEM_REGNO1      ");
		sql.append("	    , MEM_REGNO2      ");
		sql.append("	    , MEM_BIR         ");
		sql.append("	    , MEM_ZIP         ");
		sql.append("	    , MEM_ADD1        ");
		sql.append("	    , MEM_ADD2        ");
		sql.append("	    , MEM_HOMETEL     ");
		sql.append("	    , MEM_COMTEL      ");
		sql.append("	    , MEM_HP          ");
		sql.append("	    , MEM_MAIL        ");
		sql.append("	    , MEM_JOB         ");
		sql.append("	    , MEM_LIKE        ");
		sql.append("	    , MEM_MEMORIAL    ");
		sql.append("	    , MEM_MEMORIALDAY ");
		sql.append("	    , MEM_MILEAGE     ");
		sql.append("	) VALUES (            ");
		sql.append("	      ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , TO_DATE(?, 'YYYY-MM-DD')               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , ?               ");
		sql.append("	    , TO_DATE(?, 'YYYY-MM-DD')               ");
		sql.append("	    , 3000            ");
		sql.append("	)                     ");
		try(
			Connection conn = null;
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			int idx = 1;
			pstmt.setString(idx++, member.getMemId());
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemRegno1());
			pstmt.setString(idx++, member.getMemRegno2());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			
			
			return pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			//업데이트에도 추가가 가능함
//			sqlSession.update(null)
			List<MemberVO> memList = sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
				
			return memList;
		}
			
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                ");
		sql.append("     MEM_ID           ");
		sql.append("    , MEM_PASS        ");
		sql.append("    , MEM_NAME        ");
		sql.append("    , MEM_REGNO1      ");
		sql.append("    , MEM_REGNO2      ");
		sql.append("    , TO_CHAR(MEM_BIR, 'YYYY-MM-DD') AS MEM_BIR        ");
		sql.append("    , MEM_ZIP         ");
		sql.append("    , MEM_ADD1        ");
		sql.append("    , MEM_ADD2        ");
		sql.append("    , MEM_HOMETEL     ");
		sql.append("    , MEM_COMTEL      ");
		sql.append("    , MEM_HP          ");
		sql.append("    , MEM_MAIL        ");
		sql.append("    , MEM_JOB         ");
		sql.append("    , MEM_LIKE        ");
		sql.append("    , MEM_MEMORIAL    ");
		sql.append("    , TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') AS MEM_MEMORIALDAY ");
		sql.append("    , MEM_MILEAGE     ");
		sql.append("    , MEM_DELETE        ");
		sql.append(" FROM  MEMBER          ");
		sql.append(" WHERE MEM_ID = ?  ");
		
		try(
			Connection conn = null;
//			Statement stmt = conn.createStatement();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		)
		{
			pstmt.setString(1, memId);
			
			ResultSet rs = pstmt.executeQuery(); 	
			
			MemberVO member = null;
			
			if(rs.next()) {
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemRegno1(rs.getString("MEM_REGNO1"));
				member.setMemRegno2(rs.getString("MEM_REGNO2"));
				member.setMemBir(rs.getString("MEM_BIR"));
				member.setMemZip(rs.getString("MEM_ZIP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD2"));
				member.setMemHometel(rs.getString("MEM_HOMETEL"));
				member.setMemComtel(rs.getString("MEM_COMTEL"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemJob(rs.getString("MEM_JOB"));
				member.setMemLike(rs.getString("MEM_LIKE"));
				member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				member.setMemMileage(rs.getLong("MEM_MILEAGE"));
				member.setMemDelete(rs.getBoolean("MEM_DELETE"));		
			}		
			
			return member;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
			
		}	
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MEMBER              ");
		sql.append(" SET                        ");
		sql.append("     MEM_BIR = TO_DATE(?, 'YYYY-MM-DD')          ");
		sql.append("     , MEM_ZIP = ?         ");
		sql.append("     , MEM_ADD1 = ?        ");
		sql.append("     , MEM_ADD2 = ?        ");
		sql.append("     , MEM_HOMETEL = ?     ");
		sql.append("     , MEM_COMTEL = ?     ");
		sql.append("     , MEM_HP = ?         ");
		sql.append("     , MEM_MAIL = ?      ");
		sql.append("     , MEM_JOB = ?        ");
		sql.append("     , MEM_LIKE = ?       ");
		sql.append("     , MEM_MEMORIAL = ?   ");
		sql.append("     , MEM_MEMORIALDAY = TO_DATE(?, 'YYYY-MM-DD') ");
		sql.append(" WHERE                      ");
		sql.append("     MEM_ID = ?           ");
		
		try(
			Connection conn = null;
			PreparedStatement pstmt = conn.prepareStatement(sql.toString())	
		){
			int idx = 1;
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			pstmt.setString(idx++, member.getMemId());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
