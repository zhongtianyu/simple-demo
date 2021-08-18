package com.cmsz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/10/13 9:47
 */
public class threadPool {

    public static void main(String[] args) {
        int tablePoolSize = 1;
        ExecutorService threadPool = new ThreadPoolExecutor(
                tablePoolSize,
                tablePoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(20));
    }
}
