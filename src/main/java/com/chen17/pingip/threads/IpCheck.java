package com.chen17.pingip.threads;

import lombok.SneakyThrows;
import com.chen17.pingip.swvar.ExVars;
import com.chen17.pingip.utils.IPUtils;

import java.util.List;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 17:18
 */

public class IpCheck implements Runnable {

    /**
     * 初始化各个信息
     * evs：共享资源对象
     * index：指针
     * idList：evs中list的存放位置
     */
    private ExVars evs;
    private int index;

    /**
     * 带参构造
     * @param evs 入口中传入的资源对象，资源对象在传入前idIpMap、list已经赋值
     */
    public IpCheck(ExVars evs){
        //获得资源共享对象
        this.evs=evs;
        //获取临时生成的key组成的list对象
        List<Integer> idList = evs.getList();
        //获取到所有key的数量
        this.index = idList.size()-1;
    }

    @SneakyThrows
    @Override
    public void run() {
        //当集合里面key的数量>=0 也就是存在可操作的时候
        while (index >= 0) {
            //cId:每移除一次key的key代表的值
            Integer cId = evs.getList().remove(0);
            //根据cId利用Map的get（）方法获取Ip地址
            String ipAddress = evs.getIdIpMap().get(cId);
            //判断Ip地址是否通畅
            Boolean checked = IPUtils.ipPingAndFormat(ipAddress);
            //检查并将结果放到Map中
            evs.getIpChecked().put(cId,checked);
            /*测试查看里面文件用
            System.out.println(cId+"======"+ipAddress+"======="+checked);*/

            index--;
            //key的数量自减，再投入到循环中
        }
    }
}
