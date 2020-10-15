package com.chen17.pingip.test;

import com.chen17.pingip.opt.ConcurrentPingIp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 18:39
 */

public class TestIP {
    /**
     * 测试方法，具体的不详细说了
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        ConcurrentPingIp concurrentPingIp = new ConcurrentPingIp();
        Map<Integer, Boolean> dealIp = concurrentPingIp.dealIp(idIpMap, 100);
        System.out.println(dealIp);
    }

    /**
     * 下面是生成测试数据的内容，具体IP表往static块内填写并放置
     */
    public static Map<Integer,String> idIpMap;

    static {
        idIpMap = new HashMap<>();

        for(int i = 0; i<=180;i++){
            idIpMap.put(i,"192.168.1."+i);
        }

    }
}
