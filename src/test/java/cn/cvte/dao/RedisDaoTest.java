package cn.cvte.dao;

import cn.cvte.dto.Task;
import cn.cvte.entity.TaskModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-web.xml"})
public class RedisDaoTest {

    @Autowired
    private RedisDao redisDao;
    @Test
    public void test() {
//        redisDao.put(new HashMap<Integer, TaskModel>());
//        Map<Integer, TaskModel> taskModelMap = redisDao.getModelMap();
//        System.out.println(taskModelMap.size());
    }

}