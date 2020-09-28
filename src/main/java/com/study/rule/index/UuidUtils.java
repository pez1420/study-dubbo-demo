package com.study.rule.index;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author pez1420@gmail.com
 * @version $Id: UuidUtils.java v 0.1 2020/8/14 10:45 上午 pez1420 Exp $$
 */
public class UuidUtils {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());

        System.out.println(System.currentTimeMillis());

        System.out.println(DateUtils.addDays(new Date(), 1));
    }
}
