package cn.cvte.entity;

import java.util.Date;

public class TaskRecord {
    private String uid;
    private int tid;
    private int history;    // 历史任务完成总次数
    private int step;       // 当前完成到第几步
    private int score;      // 当前任务获得积分
    private int state;      // 完成状态 -1未领取 0进行中 1已完成
    private Date createTime;
    private Date updateTime;

    public TaskRecord() {
    }

    public static TaskRecord initCreateRecord(String uid, int tid) {
        return new TaskRecord(uid, tid, 0, 0, 0, 0, new Date(), new Date());
    }

    public TaskRecord(String uid, int tid, int history, int step, int score, int state, Date createTime, Date updateTime) {
        this.uid = uid;
        this.tid = tid;
        this.history = history;
        this.step = step;
        this.score = score;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
