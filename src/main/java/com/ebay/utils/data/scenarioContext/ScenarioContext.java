package com.ebay.utils.data.scenarioContext;

import com.ebay.enums.ScenarioContextKey;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static Map<ScenarioContextKey, Object> scenarioContext = new HashMap<>();

    public static Map<ScenarioContextKey, Object> getContext() {
        return scenarioContext;
    }

    public static void setValue(ScenarioContextKey key, Object value) {
        scenarioContext.put(key, value);
    }

    public static void setInitialTestValue(ScenarioContextKey key, Map<String, Map<String, Double>> value) {
        Map<String, Map<String, Double>> haveMap = (Map<String, Map<String, Double>>) scenarioContext.get(key);
        if (haveMap == null){
            scenarioContext.put(key, value);
        } else {
            for (String keyNext : value.keySet()){
                haveMap.put(keyNext, value.get(keyNext));
            }
            scenarioContext.put(key, haveMap);
        }
    }

    public static Object getValueByKey(ScenarioContextKey key) {
        return scenarioContext.get(key);
    }

    public static void clear() {
        scenarioContext = new HashMap<>();
    }
}
