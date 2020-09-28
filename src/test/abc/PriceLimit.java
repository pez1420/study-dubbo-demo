package com.study.rule.cep;

/**
 * @author pez1420@gmail.com
 * @version : PriceLimit.java v 0.1 2020/9/27 5:04 下午 pez1420 Exp $$
 */
public class PriceLimit {
    String userId;
    String symbol;
    double limitPct;

    public PriceLimit(String userId, String symbol, double limitPct) {
        this.userId = userId;
        this.symbol = symbol;
        this.limitPct = limitPct;
    }

    public String getUserId() {
        return userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getLimitPct() {
        return limitPct;
    }

    @Override
    public String toString() {
        return "userId=" + userId + "  symbol=" + symbol + "  limitPct=" + limitPct;
    }
}
