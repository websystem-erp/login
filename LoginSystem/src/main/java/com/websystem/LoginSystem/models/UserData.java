package com.websystem.LoginSystem.models;

import java.util.Base64;
public class UserData {
    private Long id;
    private String email;
    private String name;
    private CampusType campusType;
    private String role;
    private byte[] image;
    private byte[] adminimage;
    private String campusname;
    private String campuslocation;
    private String campusfoundyear;
    private String branchname;
    private String branchlocation;

    public UserData(Long id, String email, String name, CampusType campusType, String role,  String campusname, String campuslocation, String campusfoundyear, String branchname, String branchlocation,byte[] image, byte[] adminimage) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.campusType = campusType;
        this.role = role;

        this.campusname = campusname;
        this.campuslocation = campuslocation;
        this.campusfoundyear = campusfoundyear;
        this.branchname = branchname;
        this.branchlocation = branchlocation;
        this.image = image;
        this.adminimage = adminimage;

    }

    public UserData(Long id, String email, String name, CampusType campusType, String role, byte[] image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.campusType = campusType;
        this.role = role;
        this.image = image;
    }

    public UserData(Long id, String email, String name, String role, byte[] image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.image = image;
    }

    // Getters and Setters

    public byte[] getAdminimage() {
        return adminimage;
    }

    public void setAdminimage(byte[] adminimage) {
        this.adminimage = adminimage;
    }

    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }

    public String getCampuslocation() {
        return campuslocation;
    }

    public void setCampuslocation(String campuslocation) {
        this.campuslocation = campuslocation;
    }

    public String getCampusfoundyear() {
        return campusfoundyear;
    }

    public void setCampusfoundyear(String campusfoundyear) {
        this.campusfoundyear = campusfoundyear;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchlocation() {
        return branchlocation;
    }

    public void setBranchlocation(String branchlocation) {
        this.branchlocation = branchlocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CampusType getCampusType() {
        return campusType;
    }

    public void setCampusType(CampusType campusType) {
        this.campusType = campusType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}