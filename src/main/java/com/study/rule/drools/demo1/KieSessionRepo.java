package com.study.rule.drools.demo1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author pez1420@gmail.com
 * @version $Id: KieSessionRepo.java v 0.1 2020/7/17 1:51 下午 pez1420 Exp $$
 */
public class KieSessionRepo {

    private static Map<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();

    private static Map<String, KieSession>   kieSessionMap   = new ConcurrentHashMap<>();

    public static void setKieContainer(String key, KieContainer kieContainer) {
        KieSession newKieSession = kieContainer.newKieSession();
        kieContainerMap.put(key, kieContainer);
        kieSessionMap.put(key, newKieSession);
    }

    public KieSession getKieSession(String key) {
        return kieSessionMap.get(key);
    }
}
