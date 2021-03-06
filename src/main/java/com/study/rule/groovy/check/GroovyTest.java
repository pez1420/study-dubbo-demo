package com.study.rule.groovy.check;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.ErrorCollector;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.junit.Test;
import org.kohsuke.groovy.sandbox.GroovyInterceptor;
import org.kohsuke.groovy.sandbox.SandboxTransformer;

import groovy.lang.GroovyShell;

/**
 * @author pez1420@gmail.com
 * @version $Id: GroovyTest.java v 0.1 2020/7/24 10:02 上午 pez1420 Exp $$
 */
public class GroovyTest {

    class NoSystemExitSandbox extends GroovyInterceptor {
        @Override
        public Object onStaticCall(GroovyInterceptor.Invoker invoker, Class receiver, String method,
                                   Object... args) throws Throwable {
            if (receiver == System.class && method == "exit") {
                throw new SecurityException("No call on System.exit() please");
            }
            return super.onStaticCall(invoker, receiver, method, args);
        }
    }

    class NoRunTimeSandbox extends GroovyInterceptor {
        @Override
        public Object onStaticCall(GroovyInterceptor.Invoker invoker, Class receiver, String method,
                                   Object... args) throws Throwable {
            if (receiver == Runtime.class) {
                throw new SecurityException("No call on RunTime please");
            }
            return super.onStaticCall(invoker, receiver, method, args);
        }
    }

    /**
     * 语法校验
     */
    @Test
    public void grammarCheck() {
        try {
            String expression = "if(a==1) return 1;";
            new GroovyShell().parse(expression);
        } catch (MultipleCompilationErrorsException cfe) {
            ErrorCollector errorCollector = cfe.getErrorCollector();
            System.out.println("Errors: " + errorCollector.getErrorCount());
        }
    }

    /**
     * 沙盒运行
     */
    @Test
    public void sandboxRun() {
        final GroovyShell sh = new GroovyShell(
            new CompilerConfiguration().addCompilationCustomizers(new SandboxTransformer()));
        new NoSystemExitSandbox().register();
        new NoRunTimeSandbox().register();
        sh.evaluate("System.exit(0)");
    }

    @Test
    public void formatScript() {

    }
}
