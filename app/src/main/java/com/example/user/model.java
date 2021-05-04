package com.example.user;

public class model {
    String name,email,mno;

    public model() {
    }

    public model(String name, String email, String mno) {

        this.name = name;
        this.email = email;
        this.mno = mno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }
}
