package com.study.pattern.filter;

public class HtmlFilter implements Filter {

	@Override
	public void doFilter(Request req, Response rsp, FilterChain chain) {
		chain.doFilter(req, rsp);
	}

}
