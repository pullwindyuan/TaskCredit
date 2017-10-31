package cn.cvte.dao.mapper;

import cn.cvte.entity.TaskRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TaskRecordDaoTest {

    @Autowired
    private TaskRecordDao taskRecordDao;

    @Test
    public void insert() throws Exception {
        TaskRecord record = new TaskRecord("testUid", 1, 1, 0, 0, -1, new Date(), new Date());
        taskRecordDao.insert(record);
    }

    @Test
    public void getRecordByUid() throws Exception {
        System.out.println(new Date());
        List<TaskRecord> taskRecordList = taskRecordDao.getRecordByUid("testUid");
        System.out.println(taskRecordList.size());
    }
    @Test
    public void getRecordByUidAndTid() throws Exception {
        TaskRecord record = taskRecordDao.getRecordByUid("testUid").get(0);
        System.out.println(taskRecordDao.getRecordByUidAndTid(record.getUid(), record.getTid()));
    }
    @Test
    public void updateRecord() throws Exception {
        TaskRecord record = taskRecordDao.getRecordByUid("testUid").get(0);
        record.setUpdateTime(new Date());
        taskRecordDao.updateRecord(record);
    }

}