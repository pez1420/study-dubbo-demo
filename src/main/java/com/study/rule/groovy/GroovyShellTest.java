package com.study.rule.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author pez1420@gmail.com
 * @version $Id: GroovyShellTest.java v 0.1 2020/7/8 9:53 上午 pez1420 Exp $$
 */
public class GroovyShellTest {
    public static void main(String[] args) {
        Binding binding = new Binding();

        // 绑定变量到 groovy 执行环境
        binding.setVariable("foo", new Integer(2));

        // 构建 groovy shell
        GroovyShell shell = new GroovyShell(binding);

        // 执行脚本
        Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");

        // 执行结果
        System.out.printf("value:%s, type:%s\n", value, value.getClass().getCanonicalName());

        // 获取执行过程中的变量
        System.out.printf("x:%s, type:%s\n", binding.getVariable("x"),
            binding.getVariable("x").getClass().getCanonicalName());
    }

}
