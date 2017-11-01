package cn.cvte.manager;

import cn.cvte.entity.TaskModel;

import java.util.List;

public interface TaskModelManager {
    TaskModel getTaskModelByTid(int tid);
    List<TaskModel> getAll();
}
