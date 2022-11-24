package com.jnu.exp7;

public class Book {
    public String title; // 标题
    public String content; // 内容
    Book(String title,String content)
    {
        this.setTitle(title);
        this.setContent(content);
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
}

