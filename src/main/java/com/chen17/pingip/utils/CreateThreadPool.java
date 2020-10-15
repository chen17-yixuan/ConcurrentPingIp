package com.chen17.pingip.utils;

import lombok.Data;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yd
 * @version 1.0
 * @date 2020-10-14 14:22
 */
@Data
public class CreateThreadPool {

    /**
     * 使用无参方法获取线程池，默认的
     * @return 默认线程池
     */
    public ThreadPoolExecutor defaultThreadPoolExecutor() {
        //创建地址池
        return new ThreadPoolExecutor(100,
                100, 30, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    private final AtomicInteger mThreadNum = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {

                        return new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
                    }
                });
    }

    /**
     * 自定义获取线程池，参数通ThreadPoolExecutor
     * @return 自定义地址池 放里面先不用，后续维护
     */
    public ThreadPoolExecutor diyThreadPoolExecutor(Integer corePoolSize, Integer maximumPoolSize, Integer keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        //创建地址池
        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, timeUnit,
                workQueue,threadFactory);
    }

    public CreateThreadPool(){

    }


}
