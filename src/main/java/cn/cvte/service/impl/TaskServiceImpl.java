package cn.cvte.service.impl;

import cn.cvte.dao.mapper.TaskHistoryDao;
import cn.cvte.dao.mapper.TaskModelDao;
import cn.cvte.dao.mapper.TaskRecordDao;
import cn.cvte.dao.mapper.UserScoreDao;
import cn.cvte.dto.ResponseDto;
import cn.cvte.dto.Task;
import cn.cvte.entity.TaskHistory;
import cn.cvte.entity.TaskModel;
import cn.cvte.entity.TaskRecord;
import cn.cvte.entity.UserScore;
import cn.cvte.manager.TaskModelManager;
import cn.cvte.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService{

//    @Autowired
//    private TaskModelDao taskModelDao;

    @Autowired
    private TaskModelManager taskModelManager;

    @Autowired
    private UserScoreDao userScoreDao;

    @Autowired
    private TaskRecordDao taskRecordDao;

    @Autowired
    private TaskHistoryDao taskHistoryDao;

    /**
     * 领取任务
     * @param uid
     * @param tid
     * @return
     */
    public ResponseDto receiveTask(String uid, int tid) {
        TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        //TaskModel taskModel = taskModelDao.getByTid(tid);
        TaskModel taskModel = taskModelManager.getTaskModelByTid(tid);
        if (taskModel == null)
            return ResponseDto.fail();
        // 为空则插入数据，不为空则刷新任务状态
        if (taskRecord == null) {
            taskRecord = TaskRecord.initCreateRecord(uid, tid);
            taskRecord.setState(TaskRecord.STATE_ING);
            int insertCount = taskRecordDao.insert(taskRecord);
            if (insertCount <= 0)
                return ResponseDto.fail();
        } else {
            if (taskRecord.getState() != TaskRecord.STATE_INIT)
                return ResponseDto.fail();
            // 刷新数据状态为进行中
            taskRecord.setState(TaskRecord.STATE_ING);
            taskRecordDao.updateRecord(taskRecord);
        }
        // 记录操作日志
        taskHistoryDao.insert(new TaskHistory(uid, tid,
                taskRecord.getStep(), taskRecord.getScore(), "领取任务"));
        return ResponseDto.success();
    }

    public ResponseDto doTask(String uid, int tid) {
        TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        // TaskModel taskModel = taskModelDao.getByTid(tid);
        TaskModel taskModel = taskModelManager.getTaskModelByTid(tid);
        UserScore userScore = userScoreDao.getUserByUid(uid);
        if (taskModel == null)
            return ResponseDto.fail();
        // 为空代表没有领取过任务
        if (taskRecord == null)
            return ResponseDto.fail();
        // 已完成，不可执行任务
        if (taskRecord.getState() == TaskRecord.STATE_FINISHED)
            return ResponseDto.fail();
        taskRecord.alterStep(1);
        int addScore = taskModel.getScoreByStep(taskRecord.getStep());
        taskRecord.alterScore(addScore);
        userScore.alterScore(addScore);
        if (taskRecord.getStep() >= taskModel.getStep()) {
            taskRecord.setState(TaskRecord.STATE_FINISHED);
        }

        taskRecordDao.updateRecord(taskRecord);
        userScoreDao.updateByUid(userScore);
        // 记录操作日志
        taskHistoryDao.insert(new TaskHistory(uid, tid,
                taskRecord.getStep(), taskRecord.getScore(), "做任务"));

        int joinNum = taskRecordDao.getNumByTid(tid);
        new Task(taskRecord, taskModel, joinNum);
        return ResponseDto.success();
    }


    public ResponseDto getTaskList(String uid) {
        // List<TaskModel> modelList = taskModelDao.getAll();
        List<TaskModel> modelList = taskModelManager.getAll();
        List<Task> taskList = new ArrayList<Task>();
        List<TaskRecord> taskRecordList = taskRecordDao.getRecordByUid(uid);
        Map<String, TaskRecord> taskRecordMap = trans2Map(taskRecordList);
        for (TaskModel taskModel : modelList) {
            int tid = taskModel.getTid();
            // TODO 设计好sql
            // TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
            TaskRecord taskRecord = getTaskRecord(taskRecordMap, uid, tid);
            // 为空则创建一个初始值，不写入数据库
            if (taskRecord == null) {
                taskRecord = TaskRecord.initCreateRecord(uid, tid);
            }
            int joinNum = taskRecordDao.getNumByTid(tid);
            taskList.add(new Task(taskRecord, taskModel, joinNum));
        }
        return ResponseDto.success(taskList);
    }

    private String getKey(String uid, int tid) {
        return uid + "_" + tid;
    }

    private Map<String, TaskRecord> trans2Map(List<TaskRecord> taskRecordList) {
        Map<String, TaskRecord> map = new HashMap<String, TaskRecord>();
        for (TaskRecord taskRecord : taskRecordList) {
            String key = getKey(taskRecord.getUid(), taskRecord.getTid());
            map.put(key, taskRecord);
        }
        return map;
    }

    private TaskRecord getTaskRecord(Map<String, TaskRecord> taskRecordMap, String uid, int tid) {
        return taskRecordMap.get(getKey(uid, tid));
    }


    public ResponseDto getTaskDetail(String uid, int tid) {
        return ResponseDto.success();
    }
}
