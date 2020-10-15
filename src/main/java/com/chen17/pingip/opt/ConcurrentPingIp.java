package com.chen17.pingip.opt;

import com.chen17.pingip.swvar.ExVars;
import com.chen17.pingip.threads.IpCheck;
import com.chen17.pingip.utils.CreateThreadPool;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 17:12
 */

public class ConcurrentPingIp {

    /**
     *
     * @param idIpMap id-ip Map<Integer,String> map集合
     * @param threadNum 线程数，最大100，多了报错，根据IP数量来变
     * @return Map<Integer, Boolean> 返回id-T/F ,通或者是断
     * @throws InterruptedException 中断异常
     *
     */
    public Map<Integer, Boolean> dealIp(Map<Integer,String> idIpMap,Integer threadNum) throws InterruptedException {

        //创建地址池工具对象
        CreateThreadPool ct = new CreateThreadPool();
        //调用方法创建默认地址池（也可以选择自定义）
        ThreadPoolExecutor cp = ct.defaultThreadPoolExecutor();
        //创建资源共享对象
        ExVars evs = new ExVars();
        //资源共享对象中设置传进来的Map（ID,IP地址）
        evs.setIdIpMap(idIpMap);
        //资源共享对象中传入map中IP的集合
        evs.setList(new ArrayList<>(idIpMap.keySet()));
        //创建Runnable的实现类对象，并传入共享资源对象
        IpCheck ipCheck = new IpCheck(evs);


        //Runnable对象必须依赖一个Thread类才能真正意义上说是另外开辟了一个线程，不然是默认在主线程中的，
        for (int i = 0; i < threadNum; i++) {
            Thread.sleep(10);
            cp.submit(ipCheck);
        }

        cp.shutdown();

        //阻塞循环监听所有线程是否完成，若完成则跳出循环
        while (true) {
            if (cp.isTerminated()) {
                break;
            }
        }

        /*System.out.println(evs.getIpChecked().size());*/

        //将所得结果封装打包
        return evs.getIpChecked();
    }
}
