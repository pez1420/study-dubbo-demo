package com.study.pattern.filter;

public interface Filter {

	public void doFilter(Request req, Response rsp, FilterChain chain);
}
