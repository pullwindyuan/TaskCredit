package cn.cvte.dto;

public class Task {
    private int tid;
    private String desc;
    private int joinNum;
    private int curScore;
    private int allScore;
    private int state;

    public Task() {
    }

    public Task(int tid, String desc, int joinNum, int curScore, int allScore, int state) {
        this.tid = tid;
        this.desc = desc;
        this.joinNum = joinNum;
        this.curScore = curScore;
        this.allScore = allScore;
        this.state = state;
    }
}
