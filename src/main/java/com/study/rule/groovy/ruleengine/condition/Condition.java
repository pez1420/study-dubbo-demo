package com.study.rule.groovy.ruleengine.condition;

import com.study.rule.groovy.ruleengine.Unit;

/**
 * 条件单元接口
 * Created by LI_ZHEN on 2016/5/5.
 */
public interface Condition extends Unit {

    /**
     * 获取条件执行结果
     * @return 真或假
     * */
    boolean getResult();

    /**
     * 注册条件为真时执行的单元
     * @param unit 单元
     */
    void registerTrueUnit(Unit unit);

    /**
     * 注册条件为假时执行的单元
     * @param unit 单元
     */
    void registerFalseUnit(Unit unit);

}
