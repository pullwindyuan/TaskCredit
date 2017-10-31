package cn.cvte.entity;

import org.springframework.util.StringUtils;

public class TaskModel {
    private int tid;
    private String desc;
    private int step;
    private String score;

    public TaskModel() {}

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getScoreByStep(int step) {
        String[] scores = this.score.split(",");
        return step<=0 ? 0 : Integer.valueOf(scores[step-1]);
    }

    public int getTotalScore() {
        String[] scores = this.score.split(",");
        int result = 0;
        for (String score : scores) {
            result += Integer.valueOf(score);
        }
        return result;
    }

}
