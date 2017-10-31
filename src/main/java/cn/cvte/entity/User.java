package cn.cvte.entity;

public class User {
    private String uid;
    private String phone;
    private int score;

    public User(String uid, String phone, int score) {
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
}
