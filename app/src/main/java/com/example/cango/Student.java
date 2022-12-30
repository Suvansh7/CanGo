package com.example.cango;

public class Student {
    String Name,Reason,imageUrl;
    Student(){

    }
    public Student(String name, String reason,String imageUrl) {
        Name = name;
      Reason = reason;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String purl) {
        this.imageUrl = purl;
    }
}
