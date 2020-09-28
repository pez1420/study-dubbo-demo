package com.study.fastjson;

/**
 * @author pez1420@gmail.com
 * @version $Id: A.java v 0.1 2020/6/1 10:02 上午 pez1420 Exp $$
 */
public abstract class A {
    private String paramA;

    /**
     * Getter for property 'paramA'.
     *
     * @return paramA Value for property 'paramA'.
     */
    public String getParamA() {
        return paramA;
    }

    /**
     * Setter for property 'paramA'.
     *
     * @param paramA Value to set for property 'paramA'.
     */
    public void setParamA(String paramA) {
        this.paramA = paramA;
    }

    @Override
    public String toString() {
        return "A{" + "paramA='" + paramA + '\'' + '}';
    }
}
