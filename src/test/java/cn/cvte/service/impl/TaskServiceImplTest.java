package cn.cvte.service.impl;

import cn.cvte.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void receiveTask() throws Exception {
        taskService.receiveTask("testUid", 2);
    }

    @Test
    public void doTask() throws Exception {
    }

    @Test
    public void getTaskList() throws Exception {
    }

    @Test
    public void getTaskDetail() throws Exception {
    }

}