/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.PersistentLayer.Data;

import java.util.Date;

/**
 *
 * @author Kipi
 */
public class MemberData {
    private String _userName;
    private String _nickName;
    private String _password;
    private String _firstName;
    private String _lastName;;
    private String _email;
    private Date _dateJoined;
    private Date _dateOfBirth;
    private boolean _status;
        /**
     * constractor for testing
     */
    public MemberData(String userName,String nickName,String password)
    {
        this(userName, nickName, password, "", "", "", null, null);
    }
    
    public MemberData(String userName,String nickName,String password, String firstName,String lastName, String email,Date birthday)
    {
        this(userName, nickName, password, firstName, lastName, email, null, birthday);
    }
    public MemberData(String userName,String nickName,String password, String firstName,String lastName, String email, Date joined, Date birthday)
    {
        this._userName=userName;
        this._nickName=nickName;
        this._password=password;
        this._firstName=firstName;
        this._lastName=lastName;
        this._email=email;
        this._dateJoined=joined;
        this._dateOfBirth=birthday;
    }
    //Getters
    /**
     * 
     * @return the _userName
     */
    public String getUserName() {
        return _userName;
    }

    /**
     * @return the _nickName
     */
    public String getNickName() {
        return _nickName;
    }

    /**
     * @return the _password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @return the _firstName
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * @return the _lastName
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * @return the _email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * @return the _dateJoined
     */
    public Date getDateJoined() {
        return _dateJoined;
    }

    /**
     * @return the _dateOfBirth
     */
    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    //Setters
    /**
     * @param userName the _userName to set
     */
    public void setUserName(String userName) {
        this._userName = userName;
    }

    /**
     * @param nickName the _nickName to set
     */
    public void setNickName(String nickName) {
        this._nickName = nickName;
    }

    /**
     * @param password the _password to set
     */
    public void setPassword(String password) {
        this._password = password;
    }

    /**
     * @param firstName the _firstName to set
     */
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    /**
     * @param lastName the _lastName to set
     */
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    /**
     * @param email the _email to set
     */
    public void setEmail(String email) {
        this._email = email;
    }

    /**
     * @param dateJoined the _dateJoined to set
     */
    public void setDateJoined(Date dateJoined) {
        this._dateJoined = dateJoined;
    }

    /**
     * @param dateOfBirth the _dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this._dateOfBirth = dateOfBirth;
    }

    /**
     * @param status the _status to set
     */
    public void setStatus(boolean status) {
        this._status = status;
    }

    
}
