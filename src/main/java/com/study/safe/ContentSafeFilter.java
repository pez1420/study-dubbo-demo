package com.study.safe;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.safety.Whitelist;

public class ContentSafeFilter {
	private final static Whitelist user_content_filter = Whitelist.relaxed();

	static {
		// ���ӿ��ű�ǩ��������
		user_content_filter.addTags("embed", "object", "param", "span", "div"); // ���ӿ�������
		user_content_filter.addAttributes(":all", "style", "class", "id", "name");
		user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
		user_content_filter.addAttributes("param", "name", "value");
		user_content_filter.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen",
				"allowScriptAccess", "flashvars", "name", "type", "pluginspage");
	}

	/** * ���û��������ݽ��й��� 
	 * 
	 * @param html
	 * @return 
	 */
	public static String filter(String html) {
		if (StringUtil.isBlank(html))
			return "";
		return Jsoup.clean(html, user_content_filter);
		// return filterScriptAndStyle(html);
	}

	/**
	 * * �ȽϿ��ɵĹ��ˣ����ǻ���˵�object��script�� span,div�ȱ�ǩ�������ڸ��ı��༭�����ݻ�����html���� 
	 *  @param html 
	 *  
	 *  @return
	 */
	public static String relaxed(String html) {
		return Jsoup.clean(html, Whitelist.relaxed());
	}

	/** 
	 * ȥ�����б�ǩ�����ش�����.������textarea��input 
	 * @param html 
	 * @return 
	 */
	public static String pureText(String html) {
		return Jsoup.clean(html, Whitelist.none());
	}

	/** * @param args */
	public static void main(String[] args) {
		String unsafe = "<table><tr><td>1</td></tr></table>" + "<img src='' alt='' />"
				+ "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a>" + "<object></object>"
				+ "<script>alert(1);</script>" + "</p>";
		String safe = ContentSafeFilter.filter(unsafe);
		System.out.println("safe: " + safe);
	}
}