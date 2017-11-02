package cn.cvte.manager.impl;

import cn.cvte.dao.RedisDao;
import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.entity.TaskModel;
import cn.cvte.manager.TaskModelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TaskModelManagerImpl implements TaskModelManager {

    @Autowired
    private TaskModelDao taskModelDao;

    @Autowired
    private RedisDao redisDao;

    private static final String KEY = "task";

    public TaskModel getTaskModelByTid(int tid) {
        TaskModel taskModel = redisDao.get("task_" +tid);
        if (taskModel == null) {
            taskModel = taskModelDao.getByTid(tid);
            redisDao.put("task_"+tid, taskModel);
        }
        return taskModel;
    }

    public List<TaskModel> getAll() {
        List<TaskModel> taskModelList = redisDao.getAll("task_all");
        if (taskModelList== null || taskModelList.size()==0) {
            taskModelList = taskModelDao.getAll();
            redisDao.putAll("task_all", taskModelList);
        }
        Collections.sort(taskModelList);
        return taskModelList;
    }

}
