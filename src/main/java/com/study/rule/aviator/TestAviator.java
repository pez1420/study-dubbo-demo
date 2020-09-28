package com.study.rule.aviator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;

/**
 * @author pez1420@gmail.com
 * @version $Id: TestAviator.java v 0.1 2020/7/29 8:28 下午 pez1420 Exp $$
 */
public class TestAviator {

    /**
     * Aviator只支持四种数字类型（2.3.0之后的版本）：Long、Double、big int、decimal
     *
     */
    @Test
    public void testAdd() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

    /**
     *
     */
    @Test
    public void testName() {
        String name = "唐简";
        Map<String, Object> env = new HashMap<>();
        env.put("name", name);
        String result = (String) AviatorEvaluator.execute(" 'Hello ' + name ", env);
        System.out.println(result);
    }

    @Test
    public void testCompile() {
        String expression = "a-(b-c)>100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }

    @Test
    public void testCustomFunction() {
        AviatorEvaluator.addFunction(new MinFunction());
        String expression = "min(a,b)";
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        Double result = (Double) compiledExp.execute(env);
        System.out.println(result);
    }

    static class MinFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorBigInt(Math.min(left.doubleValue(), right.doubleValue()));
        }

        @Override
        public String getName() {
            return "min";
        }
    }
}
