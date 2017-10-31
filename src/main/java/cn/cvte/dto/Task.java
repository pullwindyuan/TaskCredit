package cn.cvte.dto;

import cn.cvte.entity.TaskModel;
import cn.cvte.entity.TaskRecord;

public class Task {
    private int tid;
    private String desc;
    private int joinNum;
    private int curScore;
    private int totalScore;
    private int state;

    public Task() {
    }

    public Task(TaskRecord taskRecord, TaskModel taskModel, int joinNum) {
        this.tid = taskRecord.getTid();
        this.desc = taskModel.getDesc();
        this.joinNum = joinNum;
        this.curScore = taskRecord.getScore();
        this.totalScore = taskModel.getTotalScore();
        this.state = taskRecord.getState();
    }

    public Task(int tid, String desc, int joinNum, int curScore, int totalScore, int state) {
        this.tid = tid;
        this.desc = desc;
        this.joinNum = joinNum;
        this.curScore = curScore;
        this.totalScore = totalScore;
        this.state = state;
    }
}
