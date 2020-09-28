package com.study.rule.cep.cep5;

import java.util.Random;

import com.espertech.esper.client.*;

/**
 * @author pez1420@gmail.com
 * @version $Id: Cep5Test.java v 0.1 2020/9/27 8:51 下午 pez1420 Exp $$
 */
public class Cep5Test {
    private static Random generator = new Random();

    public static void GenerateRandomTick(EPRuntime cepRT) {//事件模拟
        double price = (double) generator.nextInt(10);
        long timeStamp = System.currentTimeMillis();
        String symbol = "APPL";
        Tick tick = new Tick(symbol, price, timeStamp);
        System.out.println("Sending tick:" + tick);
        cepRT.sendEvent(tick);
    }

    public static void main(String[] args) {
        Configuration cepConfig = new Configuration();
        //事件配置
        cepConfig.addEventType("StockTick", Tick.class);
        EPServiceProvider cep = EPServiceProviderManager.getDefaultProvider(cepConfig);
        //获取Esper运行时
        EPRuntime cepRT = cep.getEPRuntime();

        //注册引擎
        EPAdministrator cepAdm = cep.getEPAdministrator();
        EPStatement cepStatement = cepAdm.createEPL("select symbol,price from StockTick");
        //添加监听器
        cepStatement.addListener(new CEPListener());
        for (int i = 0; i < 10; i++) {
            GenerateRandomTick(cepRT);
        }
    }

    public static class CEPListener implements UpdateListener {//监听器实现
        @Override
        public void update(EventBean[] newData, EventBean[] oldData) {
            for (EventBean eb : newData) {
                System.out.println("Event received:" + eb.get("price"));
            }
        }
    }
}
