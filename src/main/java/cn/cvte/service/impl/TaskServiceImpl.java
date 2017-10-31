package cn.cvte.service.impl;

import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.dao.mapper.TaskRecordDao;
import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.Task;
import cn.cvte.entity.TaskRecord;
import cn.cvte.entity.UserScore;
import cn.cvte.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskModelDao taskModelDao;

    @Autowired
    private UserScoreDao userScoreDao;

    @Autowired
    private TaskRecordDao taskRecordDao;

    public boolean receiveTask(String uid, int tid) throws Exception {
        TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        throw new Exception("test");
//        if (1==1) {
//            //taskRecordDao.updateRecord();
//            return true;
//        } else {
//            return false;
//        }

    }

    public boolean doTask(String uid, int tid) {
        return false;
    }

    public List<Task> getTaskList(String uid) {
        return null;
    }

    public Task getTaskDetail(String uid, int tid) {
        return null;
    }
}
