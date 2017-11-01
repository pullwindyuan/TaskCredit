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

    /**
     * 领取任务
     * @param uid
     * @param tid
     * @return
     */
    public ResponseDto receiveTask(String uid, int tid) {
        TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        TaskModel model = taskModelDao.getByTid(tid);
        if (model == null)
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

        return ResponseDto.success();
    }

    // TODO 领取完任务后，前端的数据状态如何更新，是否重新获取一次数据状态


    public ResponseDto doTask(String uid, int tid) {
        TaskRecord taskRecord = taskRecordDao.getRecordByUidAndTid(uid, tid);
        TaskModel taskModel = taskModelDao.getByTid(tid);
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

        // TODO 加入人数可以redis缓存
        int joinNum = taskRecordDao.getNumByTid(tid);
        new Task(taskRecord, taskModel, joinNum);
        return ResponseDto.success();
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
            // TODO 获得参加任务总人数
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
