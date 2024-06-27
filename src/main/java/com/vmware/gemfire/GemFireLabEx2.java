package com.vmware.gemfire;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class GemFireLabEx2 {
    public static void main(String[] args) {
        // Cache 생성
        Cache cache = new CacheFactory()
                .set("log-level", "WARN")
                .create();

        // Region 생성
        Region<String, String> region = cache.<String, String>createRegionFactory(RegionShortcut.REPLICATE)
                .create("presidents");

        // 데이터 삽입 시작 시간
        long startInsertTime = System.currentTimeMillis();

        // 10,000건의 임의 데이터 생성 및 삽입
        Random random = new Random();
        for (int i = 1; i <= 10000; i++) {
            String key = "key" + i;
            String value = "value" + random.nextInt(10000);
            region.put(key, value);
        }

        // 데이터 삽입 종료 시간
        long endInsertTime = System.currentTimeMillis();
        // 종료시간 - 시작시간
        System.out.println("Data insertion time: " + (endInsertTime - startInsertTime) + " ms");

        // 데이터 조회 시작 시간 기록
        long startQueryTime = System.currentTimeMillis();

        // 10,000건의 데이터 조회
        Map<String, String> results = new HashMap<>();
        for (int i = 1; i <= 10000; i++) {
            String key = "key" + i;
            String value = region.get(key);
            results.put(key, value);
        }

        // 데이터 조회 종료 시간 기록
        long endQueryTime = System.currentTimeMillis();
        // 종료시간 - 시작시간
        System.out.println("HashMap Data select query time: " + (endQueryTime - startQueryTime) + " ms");

        // 결과 출력 (생략 가능,hashMap)
        //for (Map.Entry<String, String> entry : results.entrySet()) {
        //    System.out.println(entry.getKey() + ": " + entry.getValue());
        //}
        String p_value;
        long startQueryTime2 = System.currentTimeMillis();
        for (int i = 1; i <= 10000; i++) {

            p_value = region.get(i);
            // 결과 출력 (생략 가능,one by one fetch)
            //System.out.println(p_value);
            //System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        long endQueryTime2 = System.currentTimeMillis();
        System.out.println("One by one fetch Data select query time: " + (endQueryTime2 - startQueryTime2) + " ms");

        // Cache 종료
        cache.close();
    }
}