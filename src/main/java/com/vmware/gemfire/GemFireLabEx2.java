package com.vmware.gemfire;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.distributed.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GemFireLabEx2 {
    public static void main(String[] args) {
        String p_value;
        ClientCache cache;
        ClientCacheFactory factory;
        Region<Integer, String> region;

        factory = new ClientCacheFactory();

        // Specify Locator Host and Port that is running
        // change logging to be less verbose then default INFO
        factory.addPoolLocator("127.0.0.1", 10334);
        factory.set(ConfigurationProperties.LOG_LEVEL, "warn");
        cache = factory.create();

        // configure and create local proxy Region named example
        region = cache.<Integer, String>createClientRegionFactory( ClientRegionShortcut.PROXY).create("presidents");

        System.out.println("Cache with Local Proxy Region for 'presidents' created successfully");

        // create data
        region.put(4, "James Madison");
        region.put(5, "James Monroe");
        region.put(6, "John Quincy Adams");
        region.put(7, "Andew Jackson");
        region.put(8, "Martin Van Buren");
        region.put(9, "William Henry Harrison");
        region.put(10, "John Tyler");

        System.out.println("Data Inserted to Gemfire Successfully");

        //Hash Map
        System.out.println("HashMap");
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 4; i <= 10; i++) {

            p_value = region.get(i);
            hashMap.put(i, p_value);
        }
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println("HashMap: President #" + entry.getKey() + ": " + entry.getValue());
        }

        //ConcurrentHashMap
        System.out.println("ConcurrentHashMap");
        ConcurrentHashMap<Integer, String> conMap = new ConcurrentHashMap<>();
        for (int i = 4; i <= 10; i++) {

            p_value = region.get(i);
            conMap.put(i, p_value);
        }
        for (ConcurrentHashMap.Entry<Integer, String> entry : conMap.entrySet()) {
            System.out.println("ConcurrentHashMap: President #" + entry.getKey() + ": " + entry.getValue());
        }
        cache.close();
    }
}