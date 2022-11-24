package com.jnu.exp7;


public class Book {
    public String title; // 标题
    public int CoverResourceId; // 内容
    Book(String title,int id)
    {
        this.setTitle(title);
        this.setCoverResourceId(id);
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCoverResourceId(int id){
        this.CoverResourceId = id;
    }
}

