package com.example.misdaqia.View.register;

public class RegisterRequest {

    /**
     * email : ert@gmail.com
     * password : 147147
     * password_confirmation : 147147
     * user_type : client
     */

    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String user_type;


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

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }


    public RegisterRequest(String name ,String email, String password, String password_confirmation, String user_type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
        this.user_type = user_type;
    }
}
