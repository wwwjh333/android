package com.jnu.final_work;

public class Book {
    private String ID;//书的编号
    private String Name;//书名
    private String price;//书的价格
    private String autohr;

    public  Book() {
        super();
    }
    public Book(String iD, String name, String price, String author) {
        super();
        ID = iD;
        Name = name;
        autohr = author;
        this.price = price;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getAuthor(){
        return autohr;
    }
    public void setAutohr(String author){
        this.autohr = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;
        if (Name == null) {
            if (other.Name != null)
                return false;
        } else if (!Name.equals(other.Name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (autohr == null) {
            if (other.autohr != null)
                return false;
        } else if (!autohr.equals(other.autohr))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Book [ID=" + ID + ", Name=" + Name + ", price=" + price + ", author=" + autohr +"]";
    }
}