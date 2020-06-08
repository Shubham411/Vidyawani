package com.example.vidyawani;

public class University_Programs {

    int id;
    String name,stime,etime,desc;

    public University_Programs() {
    }

    public University_Programs(int id, String name, String stime, String etime, String desc) {
        this.id = id;
        this.name = name;
        this.stime = stime;
        this.etime = etime;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
