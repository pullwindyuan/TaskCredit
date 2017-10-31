package cn.cvte.manager;

import cn.cvte.entity.TaskHistory;
import cn.cvte.entity.TaskRecord;
import cn.cvte.entity.UserScore;

public interface UserTaskDataManager {
    TaskRecord getTaskRecord();
    UserScore getUserScore(String uid);
    TaskHistory getTaskHistory();

    void clearUserCache();

    void updateTaskRecord();

    void updateUserScore();

    void updateTaskHistory();
}
