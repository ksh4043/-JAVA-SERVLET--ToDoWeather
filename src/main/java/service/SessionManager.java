package service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 세션 관리 로직
public class SessionManager {
	// 세션 관리에 필요한 필드 데이터
	private static final String SESSION_COOKIE_NAME = "TDS-SID";	// 웹 고유 쿠키 이름
	private final Map<String, SessionData> sessionStore = new ConcurrentHashMap<>();	// 세션 저장 객체
	
	// 세션 생성
	public String createSession(Map<String, Object> data, HttpServletResponse response) {
		/*
		 * 랜덤 UUID로 세션 아이디 생성
		 * 세션 집합에 저장
		 * 서버에서 세션을 구별하기 위한 쿠키를 생성 >> 응답 헤더에 추가해서 클라리언트 브라우저에 저장
		 */
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId, new SessionData(data, System.currentTimeMillis()));
		
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return sessionId;
	}
	
	// 세션 가져오기
	public Map<String, Object> getSession(HttpServletRequest request) {
		String sessionId = extractSessionId(request);
		if (sessionId == null) {
			return null;
		}
		
		SessionData sessionData = sessionStore.get(sessionId);
		if (sessionData == null || sessionData.isExpired()) {
			sessionStore.remove(sessionId);
			return null;
		}
		
		return sessionData.getData();
	}
	
	// 세션 아이디 유효 검사
	public void invalidate(HttpServletRequest request) {
		String sessionId = extractSessionId(request);
		
		if (sessionId != null) {
			sessionStore.remove(sessionId);
		}
	}
	
	// 세션 아이디 존재 여부 체크
	private String extractSessionId(HttpServletRequest request) {
		if (request.getCookies() == null) {
			return null;
		}
		
		for (Cookie cookie : request.getCookies()) {
			if (SESSION_COOKIE_NAME.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		
		return null;
	}

}
