package com.yizhilu.os.cache.redis;

import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.yizhilu.os.core.util.PropertyUtil;

public class RedisUtil {

    private static JedisPool pool;
    // 分布式时用到
    private static ShardedJedisPool shardPool;

    static {
        PropertyUtil redisProperty = PropertyUtil.getInstance("redis");
        // 创建jedis池配置实例
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(Integer.valueOf(redisProperty.getProperty("redis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(redisProperty.getProperty("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.valueOf(redisProperty.getProperty("redis.pool.maxWait")));
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        pool = new JedisPool(config, redisProperty.getProperty("redis.ip"), Integer.valueOf(redisProperty.getProperty("redis.port")), 2000, redisProperty.getProperty("redis.password"));
        // 创建多个redis共享服务，分布式时
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(redisProperty.getProperty("redis1.ip"), Integer.valueOf(redisProperty.getProperty("redis1.port")));
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(redisProperty.getProperty("redis2.ip"), Integer.valueOf(redisProperty.getProperty("redis2.port")));

        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        list.add(jedisShardInfo2);

        // 根据配置文件,创建shared池实例
        shardPool = new ShardedJedisPool(config, list);

    }

    /**
     * 单个jedis*
     * 
     * @return
     */
    public static Jedis getJedis() {
        Jedis Jedis = getPool().getResource();
        return Jedis;
    }

    public static JedisPool getPool() {
        return pool;
    }

    public static void closeJedis(Jedis jedis) {
        getPool().returnResource(jedis);
    }
    
    public static void destroyPool(Jedis jedis) {
        getPool().destroy();
    }

    /**
     * ShardedJedis分布式时
     * 
     */
    public static ShardedJedis getShardJedis() {
        return getShardPool().getResource();
    }

    public static ShardedJedisPool getShardPool() {
        return shardPool;
    }

    public static void returnShardResource(ShardedJedis jedis) {
        getShardPool().returnResource(jedis);
    }

}
