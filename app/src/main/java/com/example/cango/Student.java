package com.example.cango;

public class Student {
    String Name,Reason,imageUrl;
    String PhoneNo;
    Student(){

    }
    public Student(String name, String reason,String imageUrl, String phoneNo) {
        Name = name;
        Reason = reason;
        PhoneNo=phoneNo;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
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