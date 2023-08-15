package com.zgdyz.service;

import com.zgdyz.model.RedisReadWriteLock;

import java.util.concurrent.TimeUnit;


/**
 * 版   权: zysc.com
 * 包   名: com.zgdyz.service
 * 描   述: TODO
 * 创建时间: 2023/8/11 11:27
 *
 * @author: lijun
 */
public class test {
    public static void main(String[] args) {
        final int[] num = {0};
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                RedisReadWriteLock.writeLock().tryLock("ccc", 30, 300, TimeUnit.SECONDS);
                num[0]++;
                System.out.println("【写】：" + num[0]);
                RedisReadWriteLock.writeLock().unlock("ccc");
            });
            thread.start();
        }
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                RedisReadWriteLock.readLock().tryLock("ccc", 30, 300, TimeUnit.SECONDS);

                System.out.println("读：" + num[0]);

                RedisReadWriteLock.readLock().unlock("ccc");
            });
            thread.start();
            if (i % 3 == 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
