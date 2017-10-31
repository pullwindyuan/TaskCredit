package cn.cvte.entity;

import java.util.Date;

public class TaskHistory {
    private String uid;
    private int tid;
    private int step;       // 当前完成到第几步
    private int score;
    private Date createTime;
    private String desc;

    public TaskHistory(String uid, int tid, int step, int score, String desc) {
        this.uid = uid;
        this.tid = tid;
        this.step = step;
        this.score = score;
        this.createTime = new Date();
        this.desc = desc;
    }
}
