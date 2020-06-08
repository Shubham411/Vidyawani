package com.example.vidyawani;

public class University_Events {
    String id,name,desc,time,loc;

    public University_Events() {
    }

    public University_Events(String id, String name, String desc, String time, String loc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.loc = loc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
