package com.example.meetmelive.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {

    // static variable single_instance of type Singleton
    private static User MyUser = null;

    @PrimaryKey
    @NonNull
    public String id = "";
    public String name = "";
    public String description="";// about me
    public String birthday;
    public String gender="";
    public String lookingForGender ="";
    public String currentLocation="";
    public String email = "";
    public long lastUpdated;
    public String password;
    public String city;
   // List<String> listNotification;// need to be tested
    //List<ChatMessage> listFriends;
    public String profilePic;
    public String pic1;
    public String pic2;
    public String pic3;

    public boolean isActive;

    public User()
    {
        email = null;
        name = null;
        password = null;
        city = null;
        birthday=null;
        id = null;
        description=null;
        gender=null;
        lookingForGender =null;
        currentLocation=null;
//        listNotification=null;
//        listFriends=null;
        profilePic=null;
        pic1=null;
        pic2=null;
        pic3=null;
        isActive=true;
    }


    public Map<String, Object> toMap() {
        Map<String,Object> data = new HashMap<>();
        data.put("profileImageUrl", profilePic);
        data.put("username", name);
        data.put("email", email);
        data.put("gender", gender);
        data.put("looking for", lookingForGender);
        data.put("current Location",null);
        data.put("birthDate",birthday);
        data.put("info",description);
        data.put("city",city);
        data.put("picture 1", pic1);
        data.put("picture 2", pic2);
        data.put("picture 3", pic3);
        return data;
    }


    public User(String id,String name,String description,String lookingForGender,String city,String profilePic,String pic1,String pic2,String pic3) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.description=description;
        this.lookingForGender =lookingForGender;
        this.profilePic=profilePic;
        this.pic1=pic1;
        this.pic2=pic2;
        this.pic3=pic3;
        this.isActive=true;
//        lastUpdated=FieldValue.serverTimestamp();
    }

    // create instance of Singleton class
    public static User getInstance()
    {
        if (MyUser == null)
            MyUser = new User();

        return MyUser;
    }

}
