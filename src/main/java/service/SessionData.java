package service;

import java.util.Map;
// 세션 데이터 클래스
public class SessionData {
	private static final long EXPIRATION = 30 * 60 * 1000L; // 30분
	private final Map<String, Object> sessionData;
	private final long createdTime;

	public SessionData(Map<String, Object> sessionData, long createdTime) {
		this.sessionData = sessionData;
		this.createdTime = createdTime;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() - createdTime > EXPIRATION;
	}

	public Map<String, Object> getData() {
		return sessionData;
	}

}
