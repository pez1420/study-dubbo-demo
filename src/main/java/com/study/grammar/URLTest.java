package com.study.grammar;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL测试
 *
 * @author luyb@servyou.com.cn
 * @version 2016-08-26 13:28
 */
public class URLTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String name=java.net.URLEncoder.encode("测试", "UTF-8");
        System.out.println(name);
        name=java.net.URLEncoder.encode(name,"UTF-8");
        System.out.println(name);
        name=java.net.URLDecoder.decode(name, "UTF-8");
        System.out.println(name);
        System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));
    }
}
