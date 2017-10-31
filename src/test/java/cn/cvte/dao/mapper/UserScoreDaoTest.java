package cn.cvte.dao.mapper;

import cn.cvte.entity.UserScore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserScoreDaoTest {

    @Autowired
    private UserScoreDao userScoreDao;

    @Test
    public void insert() throws Exception {
        UserScore userScore = new UserScore("testUid", "testPhone", 0);
        userScoreDao.insert(userScore);
    }

    @Test
    public void getUserByUid() throws Exception {
        System.out.println(userScoreDao.getUserByUid("testUid").getScore());
    }

    @Test
    public void update() throws Exception {
        UserScore userScore = userScoreDao.getUserByUid("testUid");
        userScore.setUpdateTime(new Date());
        userScoreDao.updateByUid(userScore);
    }

}