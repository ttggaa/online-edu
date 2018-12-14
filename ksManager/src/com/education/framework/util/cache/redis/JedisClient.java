package com.education.framework.util.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.education.framework.util.SerializeUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Created by dean on 2017/6/13.
 */
@Component
public class JedisClient {

    private static Logger logger = LoggerFactory.getLogger(JedisClient.class);
    @Autowired
    private JedisPool jedisPool ;
    private String REDIS_PREFIX = "pxgl_";
//    @Autowired
//    private JedisCluster jedisCluster;

    /**
     * 加前缀
     * @param key
     * @return
     */
	public String addPrefix(String key) {
		return REDIS_PREFIX + key;
	}
	
    public long incr(String key) {
    	long value = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.incr(key);
            }
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }
    
    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */

    public  String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = !StringUtil.isBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                logger.debug("get {} = {}", key, value);
            }
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public  Object getObject(String key) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)));
                logger.debug(key, value);
            }
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug(key, value);
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时(单位 ： 秒)
     * @return
     */
    public  String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取List缓存
     * @param key 键
     * @return 值
     */
    public List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取List缓存
     * @param key 键
     * @return 值
     */
    public List<?> getObjectList(String key) {
        List<?> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
//                List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
//                value = Lists.newArrayList();
//                for (byte[] bs : list){
//                    value.add(toObject(bs));
//                }
                
                value = SerializeUtil.unserializeList(jedis.get(getBytesKey(key)));
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置List缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setObjectList(String key, List<?> value, int cacheSeconds) {
    	String result = "";
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
//            List<byte[]> list = Lists.newArrayList();
//            for (Object o : value){
//                list.add(toBytes(o));
//            }

//            Object[] tmpObject = value.toArray();
//            byte[][]  params = new byte[tmpObject.length][];
//            for (int i =0;i<tmpObject.length;i++){
//                params[i] = toBytes(tmpObject[i]);
//            }

            result = jedis.set(getBytesKey(key), SerializeUtil.serializeList(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    
    /**
     * 设置List缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.rpush(key, value.toArray(new String[0]));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.rpush(key, value);
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  long listObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
//            List<byte[]> list = Lists.newArrayList();
//            for (Object o : value){
//                list.add(toBytes(o));
//            }
            byte[][]  params = new byte[value.length][];
            for (int i =0;i<value.length;i++){
                params[i] = toBytes(value[i]);
            }
            result = jedis.rpush(getBytesKey(key), params);
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public  Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = Sets.newHashSet();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set){
                    value.add(toObject(bs));
                }
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Set缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, value.toArray(new String[0]));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug(key, value);
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Set缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
//            Set<byte[]> set = Sets.newHashSet();
//            for (Object o : value){
//                set.add(toBytes(o));
//            }

            byte[][]  params = new byte[value.size()][];
            int i = 0;
            for (Object o:value){
                params[i] = toBytes(o);
                i++;
            }

            result = jedis.sadd(getBytesKey(key), params);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  long setSetObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
//            Set<byte[]> set = Sets.newHashSet();
//            for (Object o : value){
//                set.add(toBytes(o));
//            }

            byte[][]  params = new byte[value.length][];
            for (int i =0;i<value.length;i++){
                params[i] = toBytes(value[i]);
            }

            result = jedis.rpush(getBytesKey(key), params);
            logger.debug(key, value);
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取Map缓存
     * @param key 键
     * @return 值
     */
    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存
     * @param key 键
     * @return 值
     */
    public  Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = Maps.newHashMap();
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()){
                    value.put(StringUtil.toString(e.getKey()), toObject(e.getValue()));
                }
                logger.debug( key, value);
            }
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Map缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug(key, value);
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Map缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public  String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Map<byte[], byte[]> map = Maps.newHashMap();
            for (Map.Entry<String, Object> e : value.entrySet()){
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>)map);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hmset(key, value);
            logger.debug("mapPut {} = {}", key, value);
        } catch (Exception e) {
            logger.error(key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public  String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<byte[], byte[]> map = Maps.newHashMap();
            for (Map.Entry<String, Object> e : value.entrySet()){
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>)map);
            logger.debug( key, value);
        } catch (Exception e) {
            logger.error( key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public  long mapRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(key, mapKey);
            logger.debug( key, mapKey);
        } catch (Exception e) {
            logger.error( key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public  long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
            logger.debug( key, mapKey);
        } catch (Exception e) {
            logger.error( key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public  boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(key, mapKey);
            logger.debug(key, mapKey);
        } catch (Exception e) {
            logger.error(key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public  boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
            logger.debug( key, mapKey);
        } catch (Exception e) {
            logger.error( key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    public  long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)){
                result = jedis.del(key);
                logger.debug("del {}", key);
            }else{
                logger.debug("del {} not exists", key);
            }
        } catch (Exception e) {
            logger.error("del {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    public  long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))){
                result = jedis.del(getBytesKey(key));
                logger.debug("delObject {}", key);
            }else{
                logger.debug("delObject {} not exists", key);
            }
        } catch (Exception e) {
            logger.error("delObject {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return
     */
    public  boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(key);
            logger.debug("exists {}", key);
        } catch (Exception e) {
            logger.error("exists {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return
     */
    public  boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
            logger.debug("existsObject {}", key);
        } catch (Exception e) {
            logger.error("existsObject {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取资源
     * @return
     * @throws JedisException
     */
    public  Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
//			logger.debug("getResource.", jedis);
        } catch (JedisException e) {
            logger.error("getResource.", e);
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * 归还资源
     * @param jedis
     */
    public  void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 释放资源
     * @param jedis
     */
    public  void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 获取byte[]类型Key
     * @param object
     * @return
     */
    public static byte[] getBytesKey(Object object){
        if(object instanceof String){
            return (String.valueOf(object)).getBytes();
        }else{
            return StringUtil.serialize(object);
        }
    }

    /**
     * Object转换byte[]类型
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object){
        return StringUtil.serialize(object);
    }

    /**
     * byte[]型转换Object
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes){
        return StringUtil.unserialize(bytes);
    }
	
	/////////
	/**
	 * 存储REDIS队列 顺序存储
	 * @param byte[] key reids键名
	 * @param byte[] value 键值
	 */
	public void lpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {

			jedis = getResource();
			jedis.lpush(key, value);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {
            returnResource(jedis);
        }
	}

	/**
	 * 存储REDIS队列 反向存储
	 * @param byte[] key reids键名
	 * @param byte[] value 键值
	 */
	public void rpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.rpush(key, value);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			returnResource(jedis);

		}
	}

	/**
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
	 * @param byte[] key reids键名
	 * @param byte[] value 键值
	 */
	public void rpoplpush(byte[] key, byte[] destination) {

		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			jedis.rpoplpush(key, destination);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			returnResource(jedis);

		}
	}

	/**
	 * 获取队列数据
	 * @param byte[] key 键名
	 * @return
	 */
	public List<byte[]> lpopList(byte[] key) {

		List<byte[]> list = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			list = jedis.lrange(key, 0, -1);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			returnResource(jedis);

		}
		return list;
	}

	/**
	 * 获取队列数据
	 * @param byte[] key 键名
	 * @return
	 */
	public byte[] rpop(byte[] key) {

		byte[] bytes = null;
		Jedis jedis = null;
		try {

			jedis = jedisPool.getResource();
			bytes = jedis.rpop(key);

		} catch (Exception e) {

			//释放redis对象
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();

		} finally {

			//返还到连接池
			returnResource(jedis);

		}
		return bytes;
	}
	
}
