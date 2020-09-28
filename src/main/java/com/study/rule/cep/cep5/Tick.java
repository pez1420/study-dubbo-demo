package com.study.rule.cep.cep5;

/**
 * @author pez1420@gmail.com
 * @version : Tick.java v 0.1 2020/9/28 1:21 下午 pez1420 Exp $$
 */
public class Tick {
    private String symbol;

    private double price;

    private long   timeStamp;

    public Tick(String symbol, double price, long timeStamp) {
        this.symbol = symbol;
        this.price = price;
        this.timeStamp = timeStamp;
    }

    /**
     * Getter for property 'symbol'.
     *
     * @return symbol Value for property 'symbol'.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Setter for property 'symbol'.
     *
     * @param symbol Value to set for property 'symbol'.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter for property 'price'.
     *
     * @return price Value for property 'price'.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for property 'price'.
     *
     * @param price Value to set for property 'price'.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for property 'timeStamp'.
     *
     * @return timeStamp Value for property 'timeStamp'.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Setter for property 'timeStamp'.
     *
     * @param timeStamp Value to set for property 'timeStamp'.
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
