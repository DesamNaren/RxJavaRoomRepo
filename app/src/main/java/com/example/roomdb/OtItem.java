package com.example.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OtItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    int getId() {
        return id;
    }

    private String ots;
    private String strId;
    private String strNo;
    private String photo;
    private String userName;
    private String userPwd;
    private String empId;
    private String designation;
    private String postType;

    OtItem(String ots, String strId, String strNo, String photo,
           String userName, String userPwd, String empId,
           String designation, String postType) {
        this.ots = ots;
        this.strId = strId;
        this.strNo = strNo;
        this.photo = photo;
        this.userName = userName;
        this.userPwd = userPwd;
        this.empId = empId;
        this.designation = designation;
        this.postType = postType;
    }

    void setId(int id) {
        this.id = id;
    }

    String getOts() {
        return ots;
    }

    public void setOts(String ots) {
        this.ots = ots;
    }

    String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    String getStrNo() {
        return strNo;
    }

    public void setStrNo(String strNo) {
        this.strNo = strNo;
    }

    String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
