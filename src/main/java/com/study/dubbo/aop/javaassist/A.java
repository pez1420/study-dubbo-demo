package com.study.dubbo.aop.javaassist;

import java.util.Arrays;

public class A
{
    private int[] a = new int[]{23, 35, 46, 12, 99, 48, 74};
 
    public void foo(int n)
    { //ʾ���ԵĴ����ʾҵ���߼�
        for (int i = 0; i < n; i++)
        {
            Arrays.sort(a);
        }
        System.out.println("class A.foo()");
    }
}