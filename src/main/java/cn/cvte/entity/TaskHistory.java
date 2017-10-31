package cn.cvte.entity;

import java.util.Date;

public class TaskHistory {
//    private long id;
    private String uid;
    private int tid;
    private int step;       // 当前完成到第几步
    private int score;
    private Date createTime;
    private String desc;

    public TaskHistory() {
    }

    public TaskHistory(String uid, int tid, int step, int score, String desc) {
        this.uid = uid;
        this.tid = tid;
        this.step = step;
        this.score = score;
        this.createTime = new Date();
        this.desc = desc;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
