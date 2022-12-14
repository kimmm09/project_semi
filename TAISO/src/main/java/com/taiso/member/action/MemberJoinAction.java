package com.taiso.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tasio.member.db.MemberDAO;
import com.tasio.member.db.MemberDTO;

public class MemberJoinAction implements Member {

	@Override
	public MemberForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberJoinAction_execute() 호출");
		
		// 전달된 회원가입 정보 저장
		MemberDTO mDTO = new MemberDTO();
		
		mDTO.setMem_id(request.getParameter("mem_id"));
		mDTO.setMem_pw(request.getParameter("mem_pw"));
		mDTO.setMem_name(request.getParameter("mem_name"));
		mDTO.setMem_nickName(request.getParameter("mem_nickName"));
		mDTO.setMem_phone(request.getParameter("mem_phone"));
		mDTO.setMem_email(request.getParameter("mem_email"));
		mDTO.setMem_accept_sns(Integer.parseInt(request.getParameter("mem_accept_sns")));
		
		System.out.println(" M : mDTO : " +mDTO);
		
		// DAO 객체 생성 - 회원가입 메서드 호출
		MemberDAO mDAO = new MemberDAO();
		mDAO.memberJoin(mDTO);
		System.out.println(" M : 회원가입 성공! ");
		
		// 페이지 이동(준비)
		MemberForward forward = new MemberForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		
		return forward;
	}

}
