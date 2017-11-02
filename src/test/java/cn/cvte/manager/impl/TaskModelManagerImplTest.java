package cn.cvte.manager.impl;

import cn.cvte.manager.TaskModelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-web.xml"})
public class TaskModelManagerImplTest {
    @Autowired
    private TaskModelManager taskModelManager;
    @Test
    public void getMap() throws Exception {
        taskModelManager.getAll();
        taskModelManager.getAll();
    }

}