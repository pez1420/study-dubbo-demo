package com.study.rule.groovy.ruleengine.action;

import com.study.rule.groovy.ruleengine.Unit;

/**
 * 抽象动作类
 * Created by LI_ZHEN on 2016/5/5.
 */
public abstract class AbstractAction implements Action {

    protected Unit _unit;

    @Override
    public final void registerUnit(Unit unit) {
        this._unit = unit;
    }

    @Override
    public final Unit next() {
        return _unit;
    }
}
