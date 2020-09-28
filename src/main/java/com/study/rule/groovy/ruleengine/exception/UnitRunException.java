package com.study.rule.groovy.ruleengine.exception;

/**
 * Created by LI_ZHEN on 2016/5/5.
 */
public class UnitRunException extends Exception {

    public UnitRunException(String message) {
        super(message);
    }

    public UnitRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnitRunException(Throwable cause) {
        super(cause);
    }
}
