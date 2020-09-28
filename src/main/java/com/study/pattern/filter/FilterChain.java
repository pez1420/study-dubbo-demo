package com.study.pattern.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    //�������ϵĹ�����ʱ����¼��������λ����
    private int index = 0;
 
    public FilterChain addFilter(Filter f){
        filters.add(f);
        return this;
    }
 
    public void doFilter(Request req, Response resp) {
        if(index == filters.size()) 
        	return;
        
        //�õ���ǰ������
        Filter f = filters.get(index);
        index++;
        
        f.doFilter(req, resp, this);
    }
}