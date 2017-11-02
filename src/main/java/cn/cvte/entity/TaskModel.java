package cn.cvte.entity;

import org.springframework.util.StringUtils;

public class TaskModel implements Comparable<TaskModel>{
    private int tid;
    private String desc;
    private int type;
    private int step;
    private String score;

    public static final int TYPE_ONCE = 0;  // 一次性任务
    public static final int TYPE_DAY = 1;   // 周期一天任务
    public static final int TYPE_WEEK = 2;  // 周期一周任务
    public static final int TYPE_MONTH = 3; // 周期一个月任务


    public TaskModel() {}

    public int getTid() {
        return tid;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public int getStep() {
        return step;
    }

    public String getScore() {
        return score;
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

    public int compareTo(TaskModel o) {
        return this.tid - o.getTid();
    }
}
