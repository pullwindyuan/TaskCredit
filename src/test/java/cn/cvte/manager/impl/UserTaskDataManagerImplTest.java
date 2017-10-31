package cn.cvte.manager.impl;

import cn.cvte.entity.UserScore;
import cn.cvte.manager.UserTaskDataManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class UserTaskDataManagerImplTest {

    @Autowired
    private UserTaskDataManager userTaskDataManager;

    @Test
    public void getTaskRecord() throws Exception {
    }

    @Test
    public void getUserScore() throws Exception {
        UserScore userScore = userTaskDataManager.getUserScore("testUid2");
        System.out.println(userScore.getScore());
    }

    @Test
    public void getTaskHistory() throws Exception {
    }

}