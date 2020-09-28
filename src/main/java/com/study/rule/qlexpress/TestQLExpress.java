package com.study.rule.qlexpress;

import org.junit.Test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author pez1420@gmail.com
 * @version $Id: TestQLExpress.java v 0.1 2020/7/30 1:18 下午 pez1420 Exp $$
 */
public class TestQLExpress {

    @Test
    public void testQLExpress() throws Exception {
        ExpressRunner expressRunner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        Object execute = expressRunner.execute("a+b", context, null, true, false);
        System.out.println(execute);

    }
}
