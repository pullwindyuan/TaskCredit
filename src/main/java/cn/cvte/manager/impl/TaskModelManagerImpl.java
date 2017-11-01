package cn.cvte.manager.impl;

import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.entity.TaskModel;
import cn.cvte.manager.TaskModelManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
public class TaskModelManagerImpl implements TaskModelManager, InitializingBean {

    private static final String INDEX_TID = "task_tid_";
    private static final String INDEX_TYPE = "task_type_";
    private static final String INDEX_LIST = "task_list_";

    @Autowired
    private TaskModelDao taskModelDao;


    public void clearUserCache() {

    }

    @Cacheable(value="task", key="#tid")
    public TaskModel getTaskModelByTid(int tid) {
        return taskModelDao.getByTid(tid);
    }

    public List<TaskModel> getTaskModelListByType(int type) {
        return null;
    }

    private void init() {

    }

    public void afterPropertiesSet() throws Exception {

    }
}
