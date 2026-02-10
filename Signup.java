package com.example.DDamo;

import jakarta.persistence.*;

@Entity
public class Signup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int otp;
    String username,password,email;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Signup(int id, int otp, String username, String password, String email) {
        this.id = id;
        this.otp = otp;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Signup() {
    }
}
