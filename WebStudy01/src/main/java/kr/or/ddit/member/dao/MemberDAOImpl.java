package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.or.ddit.db.ConnectionPoolingFactory;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {


	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                ");
		sql.append("     MEM_ID           ");
		sql.append("    , MEM_PASS        ");
		sql.append("    , MEM_NAME        ");
		sql.append("    , MEM_REGNO1      ");
		sql.append("    , MEM_REGNO2      ");
		sql.append("    , MEM_BIR         ");
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
		sql.append("    , MEM_MEMORIALDAY ");
		sql.append("    , MEM_MILEAGE     ");
		sql.append("    , MEM_DELETE        ");
		sql.append(" FROM  MEMBER          ");
		
		try(
			Connection conn = ConnectionPoolingFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			List<MemberVO> memList = new ArrayList<>();
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				String memPass = rs.getString("MEM_PASS");
				String memName = rs.getString("MEM_NAME");
				String memRegno1 = rs.getString("MEM_REGNO1");
				String memRegno2 = rs.getString("MEM_REGNO2");
				String memBir = rs.getString("MEM_BIR");
				String memZip = rs.getString("MEM_ZIP");
				String memAdd1 = rs.getString("MEM_ADD1");
				String memAdd2 =  rs.getString("MEM_ADD2");
				String memHotel =  rs.getString("MEM_HOMETEL");
				String memComtel = rs.getString("MEM_COMTEL");
				String memHp = rs.getString("MEM_HP");
				String memMail =  rs.getString("MEM_MAIL");
				String memJob = rs.getString("MEM_JOB");
				String memLike = rs.getString("MEM_LIKE");
				String memMemorial = rs.getString("MEM_MEMORIAL");
				String memMemorialday =  rs.getString("MEM_MEMORIALDAY");
				Long memMileage = rs.getLong("MEM_MILEAGE");
				String memDelete =  rs.getString("MEM_DELETE");
				MemberVO memvo = new MemberVO(memId, memPass, memName, 
						memRegno1, memRegno2, memBir, memZip, memAdd1, 
						memAdd2, memHotel, memComtel, memHp, memMail, 
						memJob, memLike, memMemorial, memMemorialday, 
						memMileage, memDelete);
				memList.add(memvo);	
			}
			
			return memList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
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
		sql.append("    , MEM_BIR         ");
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
		sql.append("    , MEM_MEMORIALDAY ");
		sql.append("    , MEM_MILEAGE     ");
		sql.append("    , MEM_DELETE        ");
		sql.append(" FROM  MEMBER          ");
		sql.append(" WHERE MEM_ID = ?  ");
		
		try(
			Connection conn = ConnectionPoolingFactory.getConnection();
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
				member.setMemDelete(rs.getString("MEM_DELETE"));		
			}		
			
			return member;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
			
		}	
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
