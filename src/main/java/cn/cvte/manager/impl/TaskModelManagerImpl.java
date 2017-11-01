package cn.cvte.manager.impl;

import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.entity.TaskModel;
import cn.cvte.manager.TaskModelManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskModelManagerImpl implements TaskModelManager {

    @Autowired
    private TaskModelDao taskModelDao;

    @Cacheable(value="task", key = "task")
    public  Map<Integer, TaskModel> getMap() {
        Map<Integer, TaskModel> map = new HashMap<Integer, TaskModel>();
        List<TaskModel> taskModelList = taskModelDao.getAll();
        for (TaskModel taskModel : taskModelList) {
            map.put(taskModel.getTid(), taskModel);
        }
        return map;
    }

    public TaskModel getTaskModelByTid(int tid) {
        return getMap().get(tid);
    }

    public List<TaskModel> getAll() {
        Map<Integer, TaskModel> map = getMap();
        List<TaskModel> list = new ArrayList<TaskModel>();
        for (TaskModel model : map.values()) {
            list.add(model);
        }
        return list;
    }

}
