package com.study.rule.cep;

/**
 * @author pez1420@gmail.com
 * @version : StockTick.java v 0.1 2020/9/27 5:04 下午 pez1420 Exp $$
 */
public class StockTick {

    private String symbol;
    private double price;

    public StockTick(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "symbol=" + symbol + "  price=" + price;
    }
}
