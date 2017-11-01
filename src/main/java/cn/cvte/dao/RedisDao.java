package cn.cvte.dao;

import cn.cvte.entity.TaskModel;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
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

    private RuntimeSchema<HashMap> schema = RuntimeSchema.createFrom(HashMap.class);

    public Map<Integer, TaskModel> getSeckill(){
        //redis操作逻辑
        Jedis jedis = null;
        Map<Integer, TaskModel> result = null;
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
                Map<Integer, TaskModel> seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, (HashMap) seckill, schema);
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

    public String putSeckill(Map<Integer, TaskModel> seckill){
        //set Object(Seckill) ->序列化-> byte[]
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = "task";
            byte[] bytes = ProtostuffIOUtil.toByteArray((HashMap) seckill, schema,
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
}
