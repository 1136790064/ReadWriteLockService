package com.zgdyz.model;

import java.util.UUID;

/**
 * 版   权: zysc.com
 * 包   名: com.zgdyz.model
 * 描   述: TODO
 * 创建时间: 2023/8/11 11:52
 *
 * @author: lijun
 */
public class RedisReadWriteLock {
    //读锁
    private static volatile RedisReadLock redisReadLock;
    //写锁
    private static volatile RedisWriteLock redisWriteLock;

    //双重检查锁实现单例
    public static RedisReadLock readLock(){
        if(redisReadLock == null){
            synchronized (RedisReadLock.class){
                if (redisReadLock == null){
                    redisReadLock = new RedisReadLock();
                }
            }
        }
        return redisReadLock;
    }

    public static RedisWriteLock writeLock(){
        if(redisWriteLock == null){
            synchronized (RedisWriteLock.class){
                if (redisWriteLock == null){
                    redisWriteLock = new RedisWriteLock();
                }
            }
        }
        return redisWriteLock;
    }

    /** 
     * 构建读锁的key 
     * @param  name 读锁的后缀
     * @return 读锁的全称
     */
    public static String getReadLockKey(String name){
        return RedisLockConf.READ_LOCK_PREFIX + name;
    }

    public static String getWriteLockKey(String name){
        return RedisLockConf.WRITE_LOCK_PREFIX + name;
    }

    public static String getReentrantWriteLockKey(String name){
        return RedisLockConf.REENTRANT_WRITE_LOCK_PREFIX + name;
    }

    //由连接池id+获取锁的线程id，来区分分布式中的不同线程
    public static String getThreadUid(){
        return UUID.randomUUID() + ":" + Thread.currentThread().getId();
    }
}



