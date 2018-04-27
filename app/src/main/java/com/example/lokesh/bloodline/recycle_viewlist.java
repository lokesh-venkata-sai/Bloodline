package com.example.lokesh.bloodline;

public class recycle_viewlist {

    private String name;
    private String phone;
   private String blood_group;
    private String city;

    public recycle_viewlist(){}

    public recycle_viewlist(String name, String phone, String blood_group, String city) {
        this.name = name;
        this.phone = phone;
        this.blood_group = blood_group;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public String getCity() {
        return city;
    }
}
