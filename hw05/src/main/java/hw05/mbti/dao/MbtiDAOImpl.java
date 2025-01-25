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
				mbti.setMtSort(rs.getString("MT_SORT"));
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
			pstmt.setString(1, MtType);
			ResultSet rs = pstmt.executeQuery();
			
			MbtiVO mvo = null;
			//해당 mbti 타입의 정보만 보게 한다.
			if(rs.next()) {
				mvo = new MbtiVO();
				mvo.setMtSort(rs.getString("MT_SORT"));
				mvo.setMtTitle(rs.getString("MT_TYPE"));
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
