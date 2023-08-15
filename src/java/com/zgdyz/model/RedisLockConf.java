package com.zgdyz.model;

/**
 * 版   权: zysc.com
 * 包   名: com.zgdyz.model
 * 描   述: TODO
 * 创建时间: 2023/8/11 11:52
 *
 * @author: lijun
 */
public class RedisLockConf {
    public static final String READ_LOCK_PREFIX = "read_lock_";
    public static final String WRITE_LOCK_PREFIX = "write_lock_";
    public static final String REENTRANT_WRITE_LOCK_PREFIX = "reentrant_write_lock_";
}
