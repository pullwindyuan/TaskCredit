package cn.cvte.manager;

import cn.cvte.entity.TaskModel;

import java.util.List;

public interface TaskModelManager {

    void clearUserCache();

    TaskModel getTaskModelByTid(int tid);

    List<TaskModel> getTaskModelListByType(int type);

}
