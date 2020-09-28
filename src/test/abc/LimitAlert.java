package com.study.rule.cep;

/**
 * @author pez1420@gmail.com
 * @version $Id: LimitAlert.java v 0.1 2020/9/27 5:04 下午 pez1420 Exp $$
 */
public class LimitAlert {

    private final StockTick  tick;
    private final PriceLimit priceLimit;
    private final double     reference;
    private final double     lower;
    private final double     upper;

    public LimitAlert(StockTick tick, PriceLimit priceLimit, double reference, double lower,
                      double upper) {
        this.tick = tick;
        this.priceLimit = priceLimit;
        this.reference = reference;
        this.lower = lower;
        this.upper = upper;
    }

    public StockTick getTick() {
        return tick;
    }

    public PriceLimit getPriceLimit() {
        return priceLimit;
    }

    public double getReference() {
        return reference;
    }

    public double getLower() {
        return lower;
    }

    public double getUpper() {
        return upper;
    }
}
