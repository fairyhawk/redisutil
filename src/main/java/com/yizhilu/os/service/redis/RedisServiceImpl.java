package com.yizhilu.os.service.redis;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.yizhilu.os.core.util.PropertiesReader;

/**
 * 
 * @ClassName MemCacheServiceImpl
 * @package com.supergenius.sns.common.cache
 * @description
 * @author liuqinggang
 * @Create Date: 2013-5-25 下午5:37:39
 * 
 */
public class RedisServiceImpl implements RedisService {
    /**
     * 一些常量
     */
    public static final String CACHE_PROP_FILE = "redis";

    public static final String ENCODING = "UTF-8";

    public static String isUse = PropertiesReader.getValue(CACHE_PROP_FILE, "isUse");

    // 返回的实例
    private static ConcurrentHashMap<String, RedisService> flyweights = new ConcurrentHashMap<String, RedisService>();


    /**
     * 获取SessionService实例
     * 
     * @return 一个实例
     * @throws IOException
     */
    public static RedisService getInstance(String prop_file) {
        if (!flyweights.containsKey(prop_file)) {
            synchronized (prop_file) {
                flyweights.put(prop_file, new RedisServiceImpl(prop_file));
            }
        }
        return flyweights.get(prop_file);
    }

    public RedisServiceImpl() {
        String prop_file = CACHE_PROP_FILE;
        if (!flyweights.containsKey(prop_file)) {
            synchronized (prop_file) {
                flyweights.put(prop_file, new RedisServiceImpl(prop_file));
            }
        }
    }

    public static RedisService getInstance() {
        if ("1".equalsIgnoreCase(isUse)) {
            return getInstance(CACHE_PROP_FILE);
        } else {
            return null;
        }
    }

    /**
     * 私有构造方法,初始化memcached
     * 
     * @throws IOException
     * 
     */
    private RedisServiceImpl(String prop_file) {

    }

    /**
     * 获取一个对象(含重试机制)
     * 
     * @param key
     * @return piggie 2009-10-16 version 2.2.1
     */
    @Override
    public Object get(String key) {
        Object result = null;
        return result;
    }

    /**
     * 获取一批对象
     */
    @Override
    public Map<String, Object> getBulk(Set<String> keys) {

        return null;
    }

    /**
     * 存入一个对象(含重试机制)
     * 
     * @param key
     * @param value
     * @return piggie 2009-10-16 version 2.2.1
     */
    @Override
    public boolean set(String key, Object value) {
        return true;
    }

    /**
     * <p>
     * 移除一个对象
     * </p>
     * 
     * @see
     * @param key
     * @param value
     * @return
     * @author futuremining
     * @date 2009-1-12
     * @version 1.0.0
     */
    @Override
    public boolean remove(String key) {
        return true;
    }

    @Override
    public boolean append(String key, Object value) {
        return true;
    }
}