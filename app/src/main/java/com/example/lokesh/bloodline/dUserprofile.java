package com.example.lokesh.bloodline;

public class dUserprofile {
    public String user_age;
    public String user_name;
    public String user_email;
    public String user_phone;
    public String user_city;
    public String user_gender;
    public boolean donar_status;
    public String blood_group;

    public  dUserprofile(){

    }

    public dUserprofile(String user_age, String user_name, String user_email, String user_phone, String user_city,String user_gender, boolean donar_status, String blood_group) {
        this.user_age = user_age;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_city = user_city;
        this.user_gender=user_gender;
        this.donar_status =donar_status;
        this.blood_group =blood_group;

    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_age() {
        return user_age;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getUser_city() {
        return user_city;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public boolean isDonar_status() {
        return donar_status;
    }

    public void setDonar_status(boolean donar_status) {
        this.donar_status = donar_status;
    }


    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }
}
