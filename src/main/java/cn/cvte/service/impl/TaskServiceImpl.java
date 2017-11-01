package cn.cvte.service.impl;

import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.dao.mapper.TaskRecordDao;
import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.ResponseDto;
import cn.cvte.dto.Task;
import cn.cvte.entity.TaskModel;
import cn.cvte.entity.TaskRecord;
import cn.cvte.entity.UserScore;
import cn.cvte.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskModelDao taskModelDao;

    @Autowired
    private UserScoreDao userScoreDao;

    @Autowired
    private TaskRecordDao taskRecordDao;

    public ResponseDto receiveTask(String uid, int tid) {
        //TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        TaskModel model = taskModelDao.getByTid(tid);
        if (model == null)
            return ResponseDto.fail();
        TaskRecord taskRecord = TaskRecord.initCreateRecord(uid, tid);
        taskRecord.setState(TaskRecord.STATE_ING);
        int insertCount = taskRecordDao.insert(taskRecord);
        if (insertCount <= 0)
            return ResponseDto.fail();
        return ResponseDto.success();
//        if (1==1) {
//            //taskRecordDao.updateRecord();
//            return true;
//        } else {
//            return false;
//        }

    }

    public ResponseDto doTask(String uid, int tid) {
        return null;
    }

    public ResponseDto getTaskList(String uid) {
        List<TaskModel> modelList = taskModelDao.getAll();
        List<Task> taskList = new ArrayList<Task>();
        for (TaskModel taskModel : modelList) {
            int tid = taskModel.getTid();
            TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
            // 为空则创建一个初始值，不写入数据
            if (taskRecord == null) {
                taskRecord = TaskRecord.initCreateRecord(uid, tid);
            }
            // 获得参加任务总人数
            int joinNum = taskRecordDao.getNumByTid(tid);
            taskList.add(new Task(taskRecord, taskModel, joinNum));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taskList", taskList);
        return ResponseDto.success(new HashMap<String, Object>());
    }

    public ResponseDto getTaskDetail(String uid, int tid) {
        return ResponseDto.success();
    }
}
