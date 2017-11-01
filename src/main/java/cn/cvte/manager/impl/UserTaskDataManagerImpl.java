package cn.cvte.manager.impl;

import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.entity.TaskHistory;
import cn.cvte.entity.TaskRecord;
import cn.cvte.entity.UserScore;
import cn.cvte.manager.UserTaskDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

//@Component
public class UserTaskDataManagerImpl implements UserTaskDataManager {

    @Autowired
    private UserScoreDao userScoreDao;

    public TaskRecord getTaskRecord() {
        return null;
    }

    @Cacheable(value="user_score", key="#uid")
    public UserScore getUserScore(String uid) {
        return userScoreDao.getUserByUid(uid);
    }

    @Cacheable(value="user_score", key="#uid")
    public TaskHistory getTaskHistory() {
        return null;
    }

    public void clearUserCache() {

    }

    public void updateTaskRecord() {

    }

    public void updateUserScore() {

    }

    public void updateTaskHistory() {

    }
}
