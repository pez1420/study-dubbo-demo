package com.study.rule.cep;

/**
 * @author pez1420@gmail.com
 * @version $Id: StockTickerSamplePlugin.java v 0.1 2020/9/27 5:10 下午 pez1420 Exp $$
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.runtime.client.EPRuntimeProvider;
import com.espertech.esper.runtime.client.EPUndeployException;
import com.espertech.esper.runtime.client.plugin.PluginLoader;
import com.espertech.esper.runtime.client.plugin.PluginLoaderInitContext;

public class StockTickerSamplePlugin implements PluginLoader {
    private static final Logger log         = LoggerFactory
        .getLogger(StockTickerSamplePlugin.class);

    private static final String RUNTIME_URI = "runtimeURI";

    private String              runtimeURI;
    private StockTickerMain     main;
    private Thread              simulationThread;

    @Override
    public void init(PluginLoaderInitContext context) {
        if (context.getProperties().getProperty(RUNTIME_URI) != null) {
            runtimeURI = context.getProperties().getProperty(RUNTIME_URI);
        } else {
            runtimeURI = context.getRuntime().getURI();
        }
    }

    @Override
    public void postInitialize() {
        System.out.println("Starting StockTicker-example for runtime URI '" + runtimeURI + "'.");

        try {
            main = new StockTickerMain(runtimeURI, true);
            simulationThread = new Thread(main, this.getClass().getName() + "-simulator");
            simulationThread.setDaemon(true);
            simulationThread.start();
            main.run();
        } catch (Exception e) {
            log.error("Error starting StockTicker example: " + e.getMessage());
        }

        log.info("StockTicker-example started.");
    }

    @Override
    public void destroy() {
        if (main != null) {
            try {
                EPRuntimeProvider.getRuntime(runtimeURI).getDeploymentService().undeployAll();
            } catch (EPUndeployException e) {
                log.warn("Failed to undeploy: " + e.getMessage(), e);
            }
        }
        try {
            simulationThread.interrupt();
            simulationThread.join();
        } catch (InterruptedException e) {
            log.info("Interrupted", e);
        }
        main = null;
        log.info("StockTicker-example stopped.");
    }
}
