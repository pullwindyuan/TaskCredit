package cn.cvte.manager.impl;

import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.entity.TaskModel;
import cn.cvte.manager.TaskModelManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskModelManagerImpl implements TaskModelManager, InitializingBean {

    private Map<Integer, TaskModel> taskModelMap = new HashMap<Integer, TaskModel>();
    @Autowired
    private TaskModelDao taskModelDao;

    private void init() {
        List<TaskModel> taskModelList = taskModelDao.getAll();
        for(TaskModel taskModel : taskModelList) {
            taskModelMap.put(taskModel.getTid(), taskModel);
        }
    }

    public void afterPropertiesSet() throws Exception {
        init();
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
