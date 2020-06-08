package com.example.vidyawani;

public class Feedbacks {
    String fname,fdesc;
    Float star;

    public Feedbacks() {
    }

    public Feedbacks(String fname, String fdesc, Float star) {
        this.fname = fname;
        this.fdesc = fdesc;
        this.star = star;
    }

    public Float getStart() {
        return star;
    }

    public void setStart(Float start) {
        this.star = start;
    }

    public Feedbacks(String fname, String fdesc) {
        this.fname = fname;
        this.fdesc = fdesc;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFdesc() {
        return fdesc;
    }

    public void setFdesc(String fdesc) {
        this.fdesc = fdesc;
    }
}
