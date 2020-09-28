package com.study.rule.cep.cep5.temprature;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.study.rule.cep.cep5.temprature.subscriber.CriticalEventSubscriber;
import com.study.rule.cep.cep5.temprature.subscriber.MonitorEventSubscriber;
import com.study.rule.cep.cep5.temprature.subscriber.StatementSubscriber;
import com.study.rule.cep.cep5.temprature.subscriber.WarningEventSubscriber;

/**
 * @author pez1420@gmail.com
 * @version : TemperatureEventHandler.java v 0.1 2020/9/28 2:38 下午 pez1420 Exp $$
 */
public class TemperatureEventHandler {

    public static void main(String[] args) {
        StatementSubscriber criticalEventSubscriber = new CriticalEventSubscriber();
        StatementSubscriber warningEventSubscriber = new WarningEventSubscriber();
        StatementSubscriber monitorEventSubscriber = new MonitorEventSubscriber();

        System.out.println("Initializing Servcie ..");
        Configuration config = new Configuration();
        config.addEventTypeAutoName("com.study.rule.cep.cep5.temprature");

        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
        EPStatement criticalEventStatement = epService.getEPAdministrator()
            .createEPL(criticalEventSubscriber.getStatement());
        criticalEventStatement.setSubscriber(criticalEventSubscriber);

        EPStatement warningEventStatement = epService.getEPAdministrator()
            .createEPL(warningEventSubscriber.getStatement());
        warningEventStatement.setSubscriber(warningEventSubscriber);
        EPStatement monitorEventStatement = epService.getEPAdministrator()
            .createEPL(monitorEventSubscriber.getStatement());
        monitorEventStatement.setSubscriber(monitorEventSubscriber);

        ExecutorService xrayExecutor = Executors.newSingleThreadExecutor();

        xrayExecutor.submit(() -> {
            System.out.println(getStartingMessage());
            int count = 0;
            while (count < 100) {
                TemperatureEvent ve = new TemperatureEvent(new Random().nextInt(500), new Date());
                epService.getEPRuntime().sendEvent(ve);
                count++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interrupted" + e.getMessage());
                }
            }

        });

    }

    private static String getStartingMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n************************************************************");
        sb.append("\n* STARTING - ");
        sb.append("\n* PLEASE WAIT - TEMPERATURES ARE RANDOM SO MAY TAKE");
        sb.append("\n* A WHILE TO SEE WARNING AND CRITICAL EVENTS!");
        sb.append("\n************************************************************\n");
        return sb.toString();
    }
}
