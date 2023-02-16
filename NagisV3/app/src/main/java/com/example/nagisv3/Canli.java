package com.example.nagisv3;

public class Canli {


    String id;
    String title;
    String body;


    public Canli(){

    }

    public Canli(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;

    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getbody() {
        return body;
    }

    public void setbody(String body) {
        this.body = body;
    }




}
