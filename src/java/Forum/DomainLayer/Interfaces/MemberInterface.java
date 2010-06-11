/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer.Interfaces;

import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.MessageOwnerException;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.util.Date;

/**
 *
 * @author Kipi
 */
public interface MemberInterface {

    /**
     * edit a message by changing the subject or the body of the message
     * @param messageId - the Id of the number that needs to be edited
     * @param subject - the new subject
     * @param body - the new body
     */
    void editMessage(int messageId, String subject, String body) throws MessageNotFoundException, MessageOwnerException;

    /**
     * getter for the date the member registered the forum
     * @return member's registration date
     */
    Date getDateJoined();

    /**
     * getter for the date of  birth
     * @return member's date of birth
     */
    Date getDateOfBirth();

    /**
     * getter for the email
     * @return member's email address
     */
    String getEmail();

    /**
     * getter for the first name
     * @return member's first name
     */
    String getFirstName();

    /**
     * getter for the lastName
     * @return member's last name
     */
    String getLastName();

    /**
     * getter for nickName
     * @return member's nickname
     */
    String getNickName();

    /**
     * getter for the password
     * @return member's password
     */
    String getPassword();

    /**
     * getter for the username
     * @return member's username
     */
    String getUserName();

    /**
     * the mothod is used when the member decides to log out of the forum
     * when the user logs out he becomes a guest and therefore can no longer write messages
     */
    void logOut();

    /**
     * this method is used when the member want to write a new message
     * @param subject - the subject of the message
     * @param body - the body of the message
     */
    void writeMessage(String subject, String body) throws MessageNotFoundException, MessageOwnerException;

    void writeReply(int parentId, String subject, String body) throws MessageNotFoundException, MessageOwnerException;

    eMemberType getType();

}
