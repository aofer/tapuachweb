package org.tapuachForum.server.PersistentLayer;
// Generated 20:27:56 09/05/2010 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Members generated by hbm2java
 */
public class Members  implements java.io.Serializable {


     private String userName;
     private String firstName;
     private String lastName;
     private String password;
     private Date dataOfBirth;
     private String nickName;
     private Date dateOfJoin;
     private String email;
     private boolean isAdmin;
     private boolean isModerator;
     private boolean isLogin;

    public Members() {
    }

    public Members(String userName, String firstName, String lastName, String password, Date dataOfBirth, String nickName, Date dateOfJoin, String email, boolean isAdmin, boolean isModerator, boolean isLogin) {
       this.userName = userName;
       this.firstName = firstName;
       this.lastName = lastName;
       this.password = password;
       this.dataOfBirth = dataOfBirth;
       this.nickName = nickName;
       this.dateOfJoin = dateOfJoin;
       this.email = email;
       this.isAdmin = isAdmin;
       this.isModerator = isModerator;
       this.isLogin = isLogin;
    }
   
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDataOfBirth() {
        return this.dataOfBirth;
    }
    
    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }
    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Date getDateOfJoin() {
        return this.dateOfJoin;
    }
    
    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public boolean isIsModerator() {
        return this.isModerator;
    }
    
    public void setIsModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }
    public boolean isIsLogin() {
        return this.isLogin;
    }
    
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }




}


