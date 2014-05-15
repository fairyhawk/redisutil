package com.yizhilu.os.cache.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * @ClassName com.yizhilu.os.cache.redis.RedisTest
 * @description
 * @author : qinggang.liu 305050016@qq.com
 * @Create Date : 2014-5-14 下午5:04:04
 */
public class RedisTest {
    public static void main(String[] args) {
        RedisTest redisTest = new  RedisTest();
        
        // 根据redis主机和端口号实例化Jedis对象
        Jedis jedis = RedisUtil.getJedis();
        redisTest.testList();
     
       
    }
    public  void testList(){
     // 根据redis主机和端口号实例化Jedis对象
        Jedis jedis = RedisUtil.getJedis();
        
       /* // 开始前，先移除所有的内容  ,lpush在之前的数组的前面添加，rpush在后面添加
        jedis.del("messages");  
        jedis.rpush("messages", "nihao");  
        jedis.rpush("messages", "2.9");  
        jedis.rpush("messages", "HEllO");

        // 再取出所有数据jedis.lrange是按范围取出，  
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
        List<String> values = jedis.lrange("messages", 0, -1);  
        System.out.println(values); 
        // 数组长度  
        System.out.println(jedis.llen("messages"));
        */
        
        
        
        // list数组测试举例2 清空数据  
       /* System.out.println(jedis.flushDB());  
        // 添加数据  
        jedis.lpush("lists", "vector");  
        jedis.lpush("lists", "ArrayList");  
        jedis.lpush("lists", "LinkedList");  
        // 数组长度  
        System.out.println(jedis.llen("lists"));  
        // 排序  
       // System.out.println(jedis.sort("lists"));  
        // 字串  
        System.out.println("0-3:"+jedis.lrange("lists", 0, 3));  
        // 修改列表中单个值  
        jedis.lset("lists", 0, "hello list!");  
        // 获取列表指定下标的值  
        System.out.println("lists.1="+jedis.lindex("lists", 1));  
        // 删除列表指定下标的值  
        System.out.println(jedis.lrem("lists", 1, "vector"));  
        // 删除区间以外的数据  
        System.out.println("lists.1="+jedis.ltrim("lists", 0, 1));  
        // 列表出栈  
        System.out.println(jedis.lpop("lists"));  
        // 整个列表值  
*/        System.out.println(jedis.lrange("lists", 0, -1));  
        
        
    }
    
    
    public  void testValue(){
         Jedis jedis = RedisUtil.getJedis();
         String testkey = "name";

         // 添加 key-value对象，如果key对象存在就覆盖该对象
         jedis.set(testkey, "xmong");

         // 查询 key的value值，如果key不存在返回null
         String value = jedis.get(testkey);
         System.out.println("get name:" + value);

         // 删除 key-value对象，如果key不存在则忽略此操作
         System.out.println("del name:" + jedis.del(testkey));

         // 在后面追加内容
         jedis.append(testkey, "增加下内容");
         System.out.println("get name:" + jedis.get(testkey));
        
         //一次存放多个值 mset相当于 jedis.set("name1","value1"); jedis.set("name2","value2"); 
         jedis.mset("name1", "value1999", "name2", "value2000"); 
         System.out.println("get name1:" + jedis.get("name1"));
         System.out.println("get name2:" + jedis.get("name2"));
         
         
         // 判断key是否存在，不存在返回false存在返回true
         System.out.println("testkey is exists:" + jedis.exists(testkey));
         
         //// 清空数据  
         //System.out.println("flushDB:"+jedis.flushDB());  
         System.out.println("get name1:" + jedis.echo("name1"));
         System.out.println("get name2:" + jedis.echo("name2"));
         

         //可以不存在存储，存在则不存储
         jedis.set("foo", "foo  new111");  
         System.out.println("get foo:" + jedis.get("foo"));
         
         
         // 设置key的有效期，并存储数据  ,时间为秒
         jedis.setex("foo2", 10 ,"foo2"); 
         System.out.println("get foo2:" + jedis.get("foo2"));
         
         
        // 获取并更改数据  ,返回之前的值。更新为最新的值 
        jedis.set("foo3", "foo update");  
        System.out.println("update foo3:"+jedis.getSet("foo3", "foo modify"));
         
        // 截取value的值  
        jedis.set("getrange", "abcdef"); 
        System.out.println(jedis.getrange("getrange", 1, 3));  
        
        //批量删除key ,返回删除成功的个数
        System.out.println(jedis.del(new String[] { "foo", "foo1", "foo2" }));
        
     }
}
