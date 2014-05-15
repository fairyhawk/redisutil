package com.yizhilu.os.service.redis;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @ClassName com.supergenius.sns.service.common.MemCache
 * @description memcache操作类
 * @author : qinggang.liu bis@foxmail.com
 * @Create Date : 2013-12-25 上午11:55:52
 */
public class RedisCache {

    private static RedisService redisService = null;
    private static RedisCache redisCache = new RedisCache();

    public static RedisCache getInstance() {
        return redisCache;
    }

    private RedisCache() {
        redisService = RedisServiceImpl.getInstance();
    }

    /**
     * 获取
     * 
     * @param key
     * @return Object
     */
    public Object get(String key) {
        try {
            if (redisService != null) {
                return redisService.get(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };

    /**
     * 设置。默认时间为1天
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean append(String key, Object value) {
        try {
            if (redisService != null) {
                return redisService.append(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量取
     * 
     * @param keys
     * @return
     */
    public Map<String, Object> getBulk(Set<String> keys) {
        try {
            if (redisService != null) {
                return redisService.getBulk(keys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据key删除
     * 
     * @param key
     * @return
     */
    public boolean remove(String key) {
        try {
            if (redisService != null) {
                return redisService.remove(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 存,设置超时时间
     * 
     * @param key
     *            键
     * @param value值
     * @param exp
     *            时间（秒） 60*60为一小时
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            if (redisService != null) {
                return redisService.set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
