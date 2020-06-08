package com.example.vidyawani;


public class Users {

    String name;
    String mail;
    String phno;
    String pass;

    public Users() {
    }

    public Users(String name, String mail, String phno, String pass) {
        this.name = name;
        this.mail = mail;
        this.phno = phno;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

