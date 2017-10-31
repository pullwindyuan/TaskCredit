package cn.cvte.service;

import cn.cvte.dto.ResponseDto;
import cn.cvte.dto.Task;

import java.util.List;

public interface TaskService {

    public ResponseDto receiveTask(String uid, int tid);

    public ResponseDto doTask(String uid, int tid);

    public ResponseDto getTaskList(String uid);

    public ResponseDto getTaskDetail(String uid, int tid);
}
