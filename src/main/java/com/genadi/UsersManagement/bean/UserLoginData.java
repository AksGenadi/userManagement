package com.genadi.UsersManagement.bean;

public class UserLoginData {
    private long id;
    private String userName;
    private String password;
    private UserTypes userType;

    private Long companyId;


    public UserLoginData(){}

    public UserLoginData(long id,String userName, String password,UserTypes userType) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.userType=userType;
    }

    public UserLoginData(long id,String userName, String password,UserTypes userType, Long companyId) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.userType=userType;
        this.companyId = companyId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserLoginData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
