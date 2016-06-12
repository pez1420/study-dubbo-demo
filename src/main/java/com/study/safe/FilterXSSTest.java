package com.study.safe;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class FilterXSSTest {

	public static void main(String[] args) {
		String unsafe = "<table><tr><td>1</td></tr></table>" +		"<img src='' alt='' />" +		"<p><a href='http://example.com/' onclick='stealCookies()'>Link</a>" +		"<object></object>" +		"<script>alert(1);</script>" +		"</p>";
		System.out.println("unsafe:" + unsafe);
		
		
		String safe = Jsoup.clean(unsafe, Whitelist.relaxed());
		System.out.println("safe: " + safe);
	}

}
