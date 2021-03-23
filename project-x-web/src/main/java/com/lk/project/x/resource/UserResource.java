package com.lk.project.x.resource;

import java.util.Set;

public class UserResource extends AbstractResource<Long> {


    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Set<RoleResource> roles;

    /**
     * Instantiates a new abstract resource.
     *
     * @param id
     */
    public UserResource(Long id) {
        super(id);
    }

    public UserResource(){
        super(null);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@Override
    public String toString() {
        return "UserResource{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }*/
}
