package com.study.rule.cep;

/**
 * @author pez1420@gmail.com
 * @version : StockTickerMain.java v 0.1 2020/9/27 5:10 下午 pez1420 Exp $$
 */

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;

public class StockTickerMain implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(StockTickerMain.class);

    private final String        runtimeURI;
    private final boolean       continuousSimulation;

    public StockTickerMain(String runtimeURI, boolean continuousSimulation) {
        this.runtimeURI = runtimeURI;
        this.continuousSimulation = continuousSimulation;
    }

    public static void main(String[] args) {
        new StockTickerMain("StockTicker", false).run();
    }

    @Override
    public void run() {
        Configuration configuration = StockTickerEPLUtil.getConfiguration();
        EPCompiled compiled = StockTickerEPLUtil.compileEPL(configuration);

        System.out.println("Setting up runtime");
        EPRuntime runtime = EPRuntimeProvider.getRuntime(runtimeURI, configuration);
        runtime.initialize();

        System.out.println("Deploying compiled EPL");
        StockTickerEPLUtil.deploy(runtime, compiled);

        System.out.println("Generating test events: 1 million ticks, ratio 2 hits, 100 stocks");
        StockTickerEventGenerator generator = new StockTickerEventGenerator();
        LinkedList stream = generator.makeEventStream(1000000, 500000, 100,
                25, 30, 48, 52, false);
        System.out.println("Generating " + stream.size() + " events");

        System.out.println("Sending " + stream.size() + " limit and tick events");
        for (Object theEvent : stream) {
            runtime.getEventService().sendEventBean(theEvent, theEvent.getClass().getSimpleName());

            if (continuousSimulation) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }

        System.out.println("Done.");
    }
}
