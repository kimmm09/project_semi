package com.taiso.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tasio.member.db.MemberDAO;

public class MemberLoginAction implements Member {

	@Override
	public MemberForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberLoginAction_execute() 호출 ");
		
		// 전달정보 저장(mem_id, mem_pw)
		String mem_id = request.getParameter("mem_id");
		String mem_pw = request.getParameter("mem_pw");
		
		// DAO 객체 생성 - 로그인 여부 체크메서드
		MemberDAO mDAO = new MemberDAO();
		int result = mDAO.memberLogin(mem_id, mem_pw);
		
		// 체크 결과에 따른 페이지 이동(JS)	
		if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("	alert('비밀번호가 맞지 않습니다.'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;			
		}
		
		if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("	alert('등록된 회원이 아닙니다.'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;			
		}
		// result == 1
		// 로그인 성공 -> 아이디 세션영역에 저장
		HttpSession session = request.getSession();
		session.setAttribute("mem_id", mem_id);
		
		// 나중에 메인페이지로 주소 옮겨야함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		MemberForward forward = new MemberForward();
		forward.setPath("./MemberUpdate.me");
		forward.setRedirect(true);
		
		return forward;
	}

}

