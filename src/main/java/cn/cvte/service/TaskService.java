package cn.cvte.service;

import cn.cvte.dto.Task;

import java.util.List;

public interface TaskService {

    public boolean receiveTask(String uid, int tid) throws Exception;

    public boolean doTask(String uid, int tid);

    public List<Task> getTaskList(String uid);

    public Task getTaskDetail(String uid, int tid);
}
