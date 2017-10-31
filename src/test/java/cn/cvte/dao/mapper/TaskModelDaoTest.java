package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TaskModelDaoTest {
    @Autowired
    private TaskModelDao taskModelDao;

    @Test
    public void getAll() throws Exception {
        List<TaskModel> modelList = taskModelDao.getAll();
        System.out.println(modelList.size());

    }

}