package com.study.performance.removeall;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;

/**
 * @author pez1420@gmail.com
 * @version $Id: TestRemoveAll.java v 0.1 2019/9/16 6:48 PM pez1420 Exp $$
 */
public class TestRemoveAll {

    public static void main(String[] args) {

        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(1);
        A.add(2);
        A.add(3);

        ArrayList<Integer> B = new ArrayList<>();

        B.add(1);
        B.add(1);

        A.removeAll(B);

        System.out.println(ArrayUtils.toString(A));
    }
}
