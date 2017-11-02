package cn.cvte.manager;

import cn.cvte.entity.TaskModel;

import java.util.List;
import java.util.Map;

public interface TaskModelManager {
    TaskModel getTaskModelByTid(int tid);
    List<TaskModel> getAll();
}
