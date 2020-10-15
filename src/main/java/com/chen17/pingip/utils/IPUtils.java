package com.chen17.pingip.utils;

import java.net.InetAddress;
import java.util.regex.Pattern;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 15:00
 */

public class IPUtils {

    /**
     * 判断IP格式是否正确
     * @param iPAddress ip地址
     * @return 格式正确则返回True
     */
    public static Boolean checkIPFormat(String iPAddress){

        //匹配判断是否符合IP标准
        Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]" +
                "\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.(" +
                "(?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|" +
                "1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        //匹配则返回True
        return pattern.matcher(iPAddress).matches();
    }

    /**
     * 判断IP是否能ping通
     * @param ipAddress ip地址
     * @return 可PING通则返回True
     * @throws Exception
     */
    public static boolean ping(String ipAddress) throws Exception {

        if ((ipAddress == null)&&ipAddress.equals("")) {
            return false;
        }

        if (!checkIPFormat(ipAddress)) {
            return false;
        }

        int timeOut = 2000;
        //超时应该在3钞以上
        // 当返回值是true时，说明host是可用的，false则不可。
        return InetAddress.getByName(ipAddress).isReachable(timeOut);
    }

    /**
     * 判断IP格式和通断
     * @param ipAddress
     * @return
     * @throws Exception
     */
    public static boolean ipPingAndFormat(String ipAddress) throws Exception {

        return ping(ipAddress);

    }
}
