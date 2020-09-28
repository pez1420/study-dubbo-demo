package com.study.rule.groovy.ruleengine.action;

import com.study.rule.groovy.ruleengine.Unit;

/**
 * 动作接口
 * Created by LI_ZHEN on 2016/5/5.
 */
public interface Action extends Unit {

    /**
     * 注册单元
     * @param unit 单元
     * */
    void registerUnit(Unit unit);

}
