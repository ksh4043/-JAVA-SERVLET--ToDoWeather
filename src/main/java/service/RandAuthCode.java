package service;

public class RandAuthCode {
	public static String authRandCode() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 6; i++) {
			if(Math.random() < 0.5) {
				sb.append((char)((int)(Math.random() * 10) + '0'));
			} else {
				sb.append((char)((int)(Math.random() * 26) + 'A'));
			}
		}
		
		return sb.toString();
	}

}
