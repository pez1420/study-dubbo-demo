package com.study.rule.cep.cep5.temprature;

import java.util.Date;

/**
 * @author pez1420@gmail.com
 * @version $Id: TemperatureEvent.java v 0.1 2020/9/28 2:33 下午 pez1420 Exp $$
 */
public class TemperatureEvent {

    /** Temperature in Celcius. */
    private int  temperature;

    /** Time temerature reading was taken. */
    private Date timeOfReading;

    /**
     * Single value constructor.
     * @param value Temperature in Celsius.
     */
    /**
     * Temeratur constructor.
     * @param temperature Temperature in Celsius
     * @param timeOfReading Time of Reading
     */
    public TemperatureEvent(int temperature, Date timeOfReading) {
        this.temperature = temperature;
        this.timeOfReading = timeOfReading;
    }

    /**
     * Get the Temperature.
     * @return Temperature in Celsius
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Get time Temperature reading was taken.
     * @return Time of Reading
     */
    public Date getTimeOfReading() {
        return timeOfReading;
    }

    @Override
    public String toString() {
        return "TemperatureEvent [" + temperature + "C]";
    }
}
