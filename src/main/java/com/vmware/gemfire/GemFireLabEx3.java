package com.vmware.gemfire;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;

public class GemFireLabEx3 {

    final Region<Integer, President> region;

    public GemFireLabEx3(Region<Integer, President> region) {
        this.region = region;
    }

    public static void main(String[] args) {
        // Cache 생성
        ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                .set("log-level", "WARN")
                .setPdxSerializer(new ReflectionBasedAutoSerializer("com.vmware.gemfire.President"))
                .create();

        // Region 생성
        Region<Integer, President> region = cache.<Integer, President>createClientRegionFactory(ClientRegionShortcut.PROXY)
                .create("presidents");

        GemFireLabEx3 example = new GemFireLabEx3(region);

        //OK #1 with static President create
        //region.put(1, create("President 1", "1", "April 28, 1758"));

        //OK #2 protected President(String strName, String strBirthLocation, String strBirthDay)
        //President president = new President("President 2", "4", "March 16, 1751");
        //region.put(2, president);

        region.put(4, create ("James Madison", "I don't know", "March 16, 1751"));
        region.put(5, create ("James Monroe", "I don't know", "March 4, 1871"));
        region.put(6, create ("John Quincy Adams", "I don't know", "July 11, 1767"));
        region.put(7, create ("Andew Jackson", "I don't know", "March 15, 1767"));
        region.put(8, create ("Martin Van Buren", "I don't know", "December 5, 1782"));
        region.put(9, create ("William Henry Harrison", "I don't know", "February 9, 1773"));
        region.put(10, create ("John Tyler", "I don't know", "March 29, 1790"));

        System.out.println("Data Inserted to Gemfire Successfully\n");

        System.out.println("President List\n");

        for (int i = 4; i <= 10; i++) {
            President president = region.get(i);
            //president.printPresident();
            System.out.println("President #" + i + ":,\t Name: " +  president.getName()
                    + ",\tBirth Location: " + president.getBirthLocation()
                    + ",\tBirthday: " + president.getBirthDay()  );
        }
        cache.close();
    }

    static President  create(String strName, String strBirthLocation, String strBirthDay) {
        return new President(strName, strBirthLocation, strBirthDay);
    }
}