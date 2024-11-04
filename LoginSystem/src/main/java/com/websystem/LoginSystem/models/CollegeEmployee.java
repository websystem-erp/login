package com.websystem.LoginSystem.models;
import com.websystem.LoginSystem.Repository.UserDetails;
import jakarta.persistence.*;

@Entity
@Table(name = "college_employees")
public class CollegeEmployee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;          // e.g., Lecturer, Assistant Professor, etc.
//    private String department;    // Department assigned
    private String password;
    @Lob // Use this annotation to store large objects like images
    private byte[] image;

    // Getters and Setters
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
