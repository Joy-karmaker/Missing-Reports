package com.example.zc.missingreports2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CustomCardview {

    private String name;
    private String person;
    private String age;
    private String gender;
    private String height;
    private String phone;
    private String skin;
    private String location;
    private String option;

    public CustomCardview(String name, String person, String age, String gender, String height, String phone, String skin, String location, String option) {
        this.name = name;
        this.person = person;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.phone = phone;
        this.skin = skin;
        this.location = location;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public String getPerson() {
        return person;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getPhone() {
        return phone;
    }

    public String getSkin() {
        return skin;
    }

    public String getLocation() {
        return location;
    }

    public String getOption() { return option; }
}
