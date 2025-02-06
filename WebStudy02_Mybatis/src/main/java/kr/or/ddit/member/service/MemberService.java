package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.auth.exception.AuthenticateException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.paging.PaginationInfo;

/**
 * 회원 관리용(CRUD) Business Logic Layer
 */
public interface MemberService {
	
	/**
	 * 신규 회원 등록
	 * @param member
	 * @return 
	 */
	public boolean createMember(MemberVO member);
	
	/**
	 * 전체 회원 목록 조회(추후 페이징과 검색 적용 예정)
	 * @param paging TODO
	 * @return
	 */
	public List<MemberVO> readMemberList(PaginationInfo<MemberVO> paging);
	
	
	/**
	 * 회원 정보 상세 조회
	 * @param memId
	 * @return 
	 * @throws UserNotFoundException 조회 대상이 없을 때 발생함.
	 */
	public MemberVO readMember(String memId) throws UserNotFoundException;
	
	/**
	 * 회원 정보 수정 
	 * 인증 성공 여부 판단 후, 수정 성공/실패 결정.
	 * @param member
	 * @return
	 * @throws AuthenticateException
	 */
	public boolean modifyMember(MemberVO member) throws AuthenticateException;
	
	/**
	 * 회원 탈퇴 
	 * 인증 성공 여부 판단 후, 탈퇴 성공/실패 결정.
	 * @param inputData (아이디, 비밀번호)
	 * @return
	 * @throws AuthenticateException
	 */
	public boolean removeMember(MemberVO inputData) throws AuthenticateException;
}
