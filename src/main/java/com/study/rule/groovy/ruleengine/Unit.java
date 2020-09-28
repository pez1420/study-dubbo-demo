package com.study.rule.groovy.ruleengine;

import com.study.rule.groovy.ruleengine.context.Context;
import com.study.rule.groovy.ruleengine.exception.UnitRunException;

/**
 * 单元接口
 */
public interface Unit {

    /**
     * 返回下一个单元
     *
     * @return 返回下一个单元，未执行或不存在返回 null
     * */
    Unit next();

    /**
     * 执行单元
     *
     * @param context 执行上下文
     * @throws UnitRunException 单元执行异常
     * */
    void run(Context context) throws UnitRunException;

}
