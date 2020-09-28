package com.study.rule.cep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import com.espertech.esper.runtime.client.UpdateListener;

/**
 * @author pez1420@gmail.com
 * @version $Id: StockTickerAlertListener.java v 0.1 2020/9/27 5:07 下午 pez1420 Exp $$
 */
public class StockTickerAlertListener implements UpdateListener {
    private static final Logger log         = LoggerFactory
        .getLogger(StockTickerAlertListener.class);

    private List<Object>        matchEvents = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement statement,
                       EPRuntime runtime) {
        for (int i = 0; i < newEvents.length; i++) {
            EventBean event = newEvents[i];

            StockTick tick = (StockTick) event.get("tick");
            PriceLimit priceLimit = (PriceLimit) event.get("priceLimit");
            double lowerLimit = (double) event.get("observed.lowerLimit");
            double upperLimit = (double) event.get("observed.upperLimit");
            double reference = (double) event.get("observed.reference");

            System.out.println(".update Alert for stock=" + tick.getSymbol() + "  price="
                               + tick.getPrice() + "  reference=" + reference + "  lower="
                               + lowerLimit + "  upper=" + upperLimit);

            LimitAlert alert = new LimitAlert(tick, priceLimit, reference, lowerLimit, upperLimit);
            matchEvents.add(alert);
        }
    }

    public int getSize() {
        return matchEvents.size();
    }

    public List getMatchEvents() {
        return matchEvents;
    }

    public void clearMatched() {
        matchEvents.clear();
    }

}
