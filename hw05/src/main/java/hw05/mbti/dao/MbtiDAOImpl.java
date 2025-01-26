package hw05.mbti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hw05.mbti.db.ConnectionPoolingFactory;
import hw05.mbti.vo.MbtiVO;

public class MbtiDAOImpl implements MbtiDAO {

	@Override
	public List<MbtiVO> MbtiList() {
		//1. 리스트 값 초기화
		List<MbtiVO> mblist = new ArrayList<>();
		//2. sql관련 작업 
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT      ");
		sql.append("	MT_SORT,    ");
		sql.append("	MT_TYPE,    ");
		sql.append("	MT_TITLE,   ");
		sql.append("	MT_CONTENT  ");
		sql.append(" FROM            ");
		sql.append("	 TB_MBTIDESC ");
		sql.append(" ORDER BY  		 ");
		sql.append("  		MT_SORT ");
		
		try(
			//3. try with resource 구문에서 관련 커넥션 객체 생성후 -> sql 셋팅
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			// 4. sql을 자바로 변환 시킨다.
			ResultSet rs = pstmt.executeQuery();
			
			MbtiVO mbti = null;
			//5. 관련 리스트를 보여주기 위한 rs.next() 커맨드로 보여줌 -> 값셋팅
			while(rs.next()) {
				mbti = new MbtiVO();
				mbti.setMtSort(rs.getInt("MT_SORT"));
				mbti.setMtType(rs.getString("MT_TYPE"));
				mbti.setMtTitle(rs.getString("MT_TITLE"));
				mbti.setMtContent(rs.getString("MT_CONTENT"));
				//6. 리스트에 셋팅한 값을 넣어줌
				mblist.add(mbti);
			}
			//7. 리턴값 넘김
			return mblist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public MbtiVO getMbti(String MtType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT          ");
		sql.append("	MT_SORT,        ");
		sql.append("	MT_TYPE,        ");
		sql.append("	MT_TITLE,       ");
		sql.append("	MT_CONTENT      ");
		sql.append(" FROM                ");
		sql.append("	TB_MBTIDESC     ");
		sql.append(" WHERE               ");
		sql.append("	MT_TYPE = ? ");
				
		try (
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			MbtiVO mvo = null;
			pstmt.setString(1, MtType);
			ResultSet rs = pstmt.executeQuery();
			//해당 mbti 타입의 정보만 보게 한다.
			if(rs.next()) {
				mvo = new MbtiVO();
				mvo.setMtSort(rs.getInt("MT_SORT"));
				mvo.setMtType(rs.getString("MT_TYPE"));
				mvo.setMtTitle(rs.getString("MT_TITLE"));
				mvo.setMtContent(rs.getString("MT_CONTENT"));
			}
			
			
			return mvo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public int insertMbti(MbtiVO Mbti) {
		//1. sql에 담을 StringBuffer 생성해서 sql문 담음
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO    ");
	    sql.append(" TB_MBTIDESC    ");
		sql.append("    ( MT_SORT, ");
		sql.append("    MT_TYPE,   ");
		sql.append("    MT_TITLE,  ");
		sql.append("    MT_CONTENT) ");
	    sql.append(" VALUES(?, ?, ?, ?) ");	
		
		try (
			//2. try with resource 구문에서 db연결 작업
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {
			// 1. 트랜잭션 시작
	        conn.setAutoCommit(false);
			//3. db에 해당 바인딩 함수에 값을 저장한다.
			int cnt = 0;
			pstmt.setInt(1, Mbti.getMtSort());
			pstmt.setString(2, Mbti.getMtType());
			pstmt.setString(3, Mbti.getMtTitle());
			pstmt.setString(4, Mbti.getMtContent());
			//4. insert 작업 수행
			cnt = pstmt.executeUpdate();
			//5. 작업 성공시 COMMIT 트랜잭션 처리
			if(cnt > 0)
				conn.commit();
			//6. 해당 반환값 반환
			return cnt;
		} catch (SQLException e) {
			//7. 예외처리로 자동으로 닫아줌 
			throw new RuntimeException(e);
		}
		
			
		
		
	}

	@Override
	public int updateMbti(MbtiVO Mbti) {
		//1. sql 문 작성
		StringBuffer sql = new StringBuffer();
	   	sql.append(" UPDATE TB_MBTIDESC   ");
		sql.append("    SET               ");
		sql.append("    MT_SORT = ?,     ");
		sql.append("    MT_TYPE = ?,     ");
		sql.append("    MT_TITLE = ?,    ");
		sql.append("    MT_CONTENT = ?   ");
		sql.append("    WHERE             ");
		sql.append("         MT_TYPE = ?");
		
		try (
			//2. db 연동 작업
			Connection conn = ConnectionPoolingFactory.getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());	
		) {
			conn.setAutoCommit(false);
			//3. 값 셋팅후 update 작업
			pstmt.setInt(1, Mbti.getMtSort());
			pstmt.setString(2, Mbti.getMtType());
			pstmt.setString(3, Mbti.getMtTitle());
			pstmt.setString(4, Mbti.getMtContent());
			pstmt.setString(5, Mbti.getMtType());
			int cnt = pstmt.executeUpdate();
			// 4. 성공시 커밋 
			if(cnt>0)
				conn.commit();
			//5. 리턴값 넘김 
			return cnt;
		}catch (SQLException e) {
			//6. 예외처리를 이용한 자동 연결 닫기
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int deleteMbti(String MtType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE               ");
		sql.append(" FROM                 ");
		sql.append("     TB_MBTIDESC      ");
		sql.append(" WHERE                ");
		sql.append("     MT_TYPE = ? 	  ");
		
		try (
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int cnt = 0;
			conn.setAutoCommit(false);
			pstmt.setString(1, MtType);
			cnt = pstmt.executeUpdate();
			
			if(cnt>0)
				conn.commit();
			
			return cnt;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

}
