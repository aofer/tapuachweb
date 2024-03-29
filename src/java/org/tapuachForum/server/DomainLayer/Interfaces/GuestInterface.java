/*

 * Interface for the guest class
 */
package org.tapuachForum.server.DomainLayer.Interfaces;

import org.tapuachForum.shared.WrongPasswordException;
import org.tapuachForum.shared.UserInterface;
import org.tapuachForum.shared.NoSuchUserException;
import org.tapuachForum.shared.UserLoggedException;
import org.tapuachForum.shared.BadPasswordException;
import org.tapuachForum.server.Exceptions.*;
import org.tapuachForum.shared.MemberData;
import java.util.Date;

/**
 *
 * @author Amit Ofer
 */
public interface GuestInterface extends UserInterface {

    /**
     * registers a new user to the forum
     */
    // omri's version 
    /*public MemberInterface register(MemberData data) throws UserExistsException, NicknameExistsException, BadPasswordException;
    */
    public void register(String username,String password,String nickname,String email,
            String firstName,String lastName,Date dateOfBirth)
            throws UserExistsException, NicknameExistsException, BadPasswordException;
    /**
     *login to the forum (turns the guest into a logged in member)
     * only works if the given username exists the the password matches
     * @param username
     * @param password
     */
    public void logIn(String username, String password) throws NoSuchUserException, WrongPasswordException,  UserLoggedException;
}
