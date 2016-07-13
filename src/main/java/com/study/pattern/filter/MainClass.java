package com.study.pattern.filter;

public class MainClass {
    public static void main(String[] args) {
        // ��Ҫ�����˵����
        String msg = "����ҵ�ˣ�����������Ϣ��<script>";
 
        //����Request��Response����
        Request req = new Request();
        Response resp = new Response();
        //req.reqStr = msg;
        //resp.respStr = "response";
 
        //��һ��������������������������
        FilterChain chain = new FilterChain();
        chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
 
        //��ʼ����
        chain.doFilter(req, resp);
 
        //System.out.println(req.reqStr);
        //System.out.println(resp.respStr);
    }
}
