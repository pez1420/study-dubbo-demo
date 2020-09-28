package com.study.rule.drools.demo1;

import java.io.Serializable;

/**
 * @author pez1420@gmail.com
 * @version $Id: IrssetDroolsVo.java v 0.1 2020/7/17 1:49 下午 pez1420 Exp $$
 */
public class IrssetDroolsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer           surpDayCnt       = null;

    private boolean           mBlack           = false;

    private String            msg;

    public IrssetDroolsVo() {

    }

    public boolean ismBlack() {
        return mBlack;
    }

    /**
     * Setter for property 'mBlack'.
     *
     * @param mBlack Value to set for property 'mBlack'.
     */
    public void setmBlack(boolean mBlack) {
        this.mBlack = mBlack;
    }

    /**
     * Getter for property 'surpDayCnt'.
     *
     * @return surpDayCnt Value for property 'surpDayCnt'.
     */
    public Integer getSurpDayCnt() {
        return surpDayCnt;
    }

    /**
     * Setter for property 'surpDayCnt'.
     *
     * @param surpDayCnt Value to set for property 'surpDayCnt'.
     */
    public void setSurpDayCnt(Integer surpDayCnt) {
        this.surpDayCnt = surpDayCnt;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return msg Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter for property 'msg'.
     *
     * @param msg Value to set for property 'msg'.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}