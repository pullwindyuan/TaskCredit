package cn.cvte.dao;

import cn.cvte.dto.Task;
import cn.cvte.entity.TaskModel;
import cn.cvte.util.SerializationUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisDao {
    private JedisPool jedisPool;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RedisDao(JedisPoolConfig jedisPoolConfig, String ip, int port, String password){

        jedisPool = new JedisPool(jedisPoolConfig, ip, port, 10000);

    }

    public RedisDao(JedisPoolConfig jedisPoolConfig, String ip, int port){

        jedisPool = new JedisPool(jedisPoolConfig, ip, port);

    }

    private RuntimeSchema<TaskModel> schema = RuntimeSchema.createFrom(TaskModel.class);

    /*public Map<Integer, TaskModel> getModelMap(){
        //redis操作逻辑
        Jedis jedis = null;
        Map<Integer, TaskModel> result = new HashMap<Integer, TaskModel>();
        try {
            jedis = jedisPool.getResource();
            String key = "task";
            //并没有实习内部序列化操作
            //get->byte[] ->反序列化 -> Object(Seckill)
            //采用自定义序列化
            byte[] bytes = jedis.get(key.getBytes());
            //缓存获取到
            if(bytes != null){
                //空对象，用来保存序列化后的对象
                HashMap<Integer, TaskModel> seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                result = seckill;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
            return result;
        }
    }

    public String put(Map<Integer, TaskModel> modelMap){
        //set Object(Seckill) ->序列化-> byte[]
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = "task";
            byte[] bytes = ProtostuffIOUtil.toByteArray((HashMap) modelMap, schema,
                    //缓存器
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeout = 60 * 60;//单位s
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
        }
        return null;
    }

    public void put(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(SerializationUtil.serialize(key), SerializationUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public Object get(String key) {
        Jedis jedis = null;
        Object result = null;
        try {
            jedis = jedisPool.getResource();
            result =  SerializationUtil.deserialize(jedis.get(SerializationUtil.serialize(key)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
            return result;
        }
    }
*/
    public void putAll(String key, List<TaskModel> taskModels) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            int timeout = 60 * 60;//单位s
            for (TaskModel taskModel : taskModels) {
                byte[] bytes = ProtostuffIOUtil.toByteArray(taskModel, schema,
                        //缓存器
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                jedis.lpush(key.getBytes(), bytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public List<TaskModel> getAll(String key) {
        Jedis jedis = null;
        List<TaskModel> result = new ArrayList<TaskModel>();
        try {
            jedis = jedisPool.getResource();
            //并没有实习内部序列化操作
            //get->byte[] ->反序列化 -> Object(Seckill)
            //采用自定义序列化
            //byte[] bytes = jedis.blpop(key.getBytes());
            long size = jedis.llen(key.getBytes());
            for (byte[] bytes : jedis.lrange(key.getBytes(), 0, size)) {
                //缓存获取到
                if(bytes != null){
                    //空对象，用来保存序列化后的对象
                    TaskModel seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    result.add(seckill);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
            return result;
        }
    }

    public void put(String key, TaskModel taskModel){
        //set Object(Seckill) ->序列化-> byte[]
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = ProtostuffIOUtil.toByteArray(taskModel, schema,
                    //缓存器
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeout = 60 * 60;//单位s
            String result = jedis.setex(key.getBytes(), timeout, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public TaskModel get(String key){
        Jedis jedis = null;
        TaskModel result = null;
        try {
            jedis = jedisPool.getResource();
            //并没有实习内部序列化操作
            //get->byte[] ->反序列化 -> Object(Seckill)
            //采用自定义序列化
            byte[] bytes = jedis.get(key.getBytes());

                if(bytes != null){
                    //空对象，用来保存序列化后的对象
                    TaskModel seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    result = seckill;
                }
                return result;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(jedis != null){
                jedis.close();
                jedis = null;
            }
            return result;
        }
    }

}
