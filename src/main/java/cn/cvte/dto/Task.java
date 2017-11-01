package cn.cvte.dto;

import cn.cvte.entity.TaskModel;
import cn.cvte.entity.TaskRecord;

import java.io.Serializable;

public class Task implements Serializable{
    private int tid;
    private int type;
    private String desc;
    private int joinNum;
    private int curScore;
    private int totalScore;
    private int state;

    public Task() {
    }

    public Task(TaskRecord taskRecord, TaskModel taskModel, int joinNum) {
        this.tid = taskRecord.getTid();
        this.type = taskModel.getType();
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(int joinNum) {
        this.joinNum = joinNum;
    }

    public int getCurScore() {
        return curScore;
    }

    public void setCurScore(int curScore) {
        this.curScore = curScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
