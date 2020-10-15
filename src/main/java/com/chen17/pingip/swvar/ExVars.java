package com.chen17.pingip.swvar;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 19:49
 */
@Data
public class ExVars {

    /**
     * idIpMap(传进来的id，id对应的ip地址)
     */
    private Map<Integer,String> idIpMap ;
    /**
     * ipChecked存放测试通断后的结果
     * （ID，是否通断）
     */
    private Map<Integer,Boolean> ipChecked = new HashMap<>();

    /**
     * 一个单独的集合，用于存放idIpMap中的key，在线程中当个指针删减用
     */
    List<Integer> list ;

}
