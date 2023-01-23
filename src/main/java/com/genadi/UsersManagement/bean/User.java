package com.genadi.UsersManagement.bean;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String userName;
    @Column(name = "AGE", nullable = false)
    private int age;
    @Column(name = "ADDRESS", nullable = false)
    private String address;
    @Column
    private String phone;
    @Column
    private String password;

    @Column
    private  Long companyId;

    @Column
    @Enumerated(EnumType.STRING)
    private UserTypes userType;


    public User() {
    }

    public User(String userName, int age, String address, String phone, long companyId, UserTypes userType) {
        this.userName = userName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.companyId = companyId;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }
}
