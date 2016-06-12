package com.study.safe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestUrlEncodeAndDecode {

	public static void encode() {
		  try {
			String	s = "123&456ÄãºÃ"; 
			s = "The string ¨¹@foo-bar";
			String	url	="Http://localhost:8080/forum.jsp?id=" 
					  	+ URLEncoder.encode(s, "UTF-8");
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		encode();
	}

}
