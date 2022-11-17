package com.jnu.final_work;

interface BookDao {
    public void addBook(Book book);//增添书籍

    public String findBook(String bookName);//查询书籍

    public boolean alterBook(String BookName,String newstr);//修改书籍内容

    public void cancelBook(String bookName,String ID);//删除书籍
}