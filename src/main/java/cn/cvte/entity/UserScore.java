package cn.cvte.entity;

import java.io.Serializable;
import java.util.Date;

public class UserScore implements Serializable {
    private String uid;
    private String phone;
    private int score;
    private Date createTime;
    private Date updateTime;

    public UserScore() {
    }

    public UserScore(String uid, String phone, int score) {
        this.uid = uid;
        this.phone = phone;
        this.score = score;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void alterScore(int score) {
        this.score += score;
    }
}
