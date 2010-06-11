/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.GuestInterface;
import Forum.Exceptions.*;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.util.Date;

/**
 * this class is for the guest user
 * @author Amit Ofer
 */
public class Guest extends User implements GuestInterface {

    /**
     *constructor
     * @param forum
     */
    public Guest() {
    }

    /**
     * this method is used for registering a new user to the forum
     * @param username - the desired username
     * @param password - the desired passowrd
     * @param nickname - the desired nickname
     * @param email - the user's email address
     * @param firstName - the user's firstname
     * @param lastName - the user's lastname
     * @param dateOfBirth - the user's date of birth
     * @throws UserExistsException - is thrown when there is already a member with the same username
     * @throws NicknameExistsException - is thrown when there is already a member with the same nickname
     * @throws BadPasswordException - is thrown when the password doesn't meet the required policy
     */
    // omri's version
    /*
    public MemberInterface register(MemberData data)
            throws UserExistsException, NicknameExistsException, BadPasswordException {
        return Forum.getInstance().register(data);
    }*/

    //amit's version
    public void register(String username,String password,String nickname,String email,
            String firstName,String lastName,Date dateOfBirth)
            throws UserExistsException, NicknameExistsException, BadPasswordException {
        Forum.getInstance().register(username,password,nickname,email,firstName,lastName,dateOfBirth);
    }
    /**
     * this method is used when a user wants to log into the forum
     * @param username - the user's username
     * @param password - the user's password
     * @throws NoSuchUserException - is thrown when there is no registered user that matches the username
     * @throws WrongPasswordException - is thrown when the user typed the wrong password
     */
    public void logIn(String username, String password) throws NoSuchUserException, WrongPasswordException {
        Forum.getInstance().login(username, password);
    }

    /**
     * This method returns the type of the user
     */
    public eMemberType getType() {
        return eMemberType.guest;
    }
}
