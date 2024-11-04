package com.websystem.LoginSystem.models;

import com.websystem.LoginSystem.Repository.UserDetails;
import jakarta.persistence.*;

import java.util.Arrays;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Encrypted password
    private String role;

    @Enumerated(EnumType.STRING)
    private CampusType campusType;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Lob // Use @Lob for large objects (like images)
    private byte[] image;

    @Lob // Use @Lob for large objects (like images)
    private byte[] adminimage;

    private String campusname;

    private String campuslocation;

    private String campusfoundyear;

    private String branchname;

    private String branchlocation;


    // Getters and Setters

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CampusType getCampusType() {
        return campusType;
    }

    public void setCampusType(CampusType campusType) {
        this.campusType = campusType;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
