package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TaskHistoryDaoTest {

    @Autowired
    private TaskHistoryDao taskHistoryDao;

    @Test
    public void getHistoryByUid() throws Exception {
        TaskHistory history = taskHistoryDao.getHistoryByUid("testUid").get(0);
        System.out.println(history.getDesc());
        System.out.println(taskHistoryDao.getHistoryByUid("testUid").size());
    }

    @Test
    public void insert() throws Exception {
        TaskHistory history = new TaskHistory("testUid", 1, 0, 0, "领取任务");
        int result = taskHistoryDao.insert(history);
    }

}