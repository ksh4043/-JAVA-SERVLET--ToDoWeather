package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailProperties {
	public EmailProperties() {
		
	}
	
	protected Properties readProperties(String domain) {
		System.out.println("readProperties domain : " + domain);
		InputStream inputStream = null;
		if (domain.equals("google.com")) {
			inputStream = getClass().getClassLoader().getResourceAsStream("GOOGLE.properties");
		}else if (domain.equals("naver.com")) {
			inputStream = getClass().getClassLoader().getResourceAsStream("NAVER.properties");
		}else {
			System.out.println("Domain is Invalid!");
		}
		
		Properties prop = new Properties();
        
        try {
        	if (inputStream != null) {
        		prop.load(inputStream);
        		return prop;
        	}else {
        		throw new FileNotFoundException("Can not find Properties file");
        	}
        }catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
	}

}