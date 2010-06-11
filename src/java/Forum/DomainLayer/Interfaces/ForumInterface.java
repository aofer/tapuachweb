/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer.Interfaces;

import Forum.DomainLayer.Message;
import Forum.Exceptions.*;
import Forum.PersistentLayer.Data.MemberData;
import Forum.PersistentLayer.ForumHandler;
import Forum.PersistentLayer.Interfaces.XMLMessageInterface;
import Forum.PersistentLayer.XMLMemberHandler;
import Forum.PersistentLayer.XMLMessageHandler;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Amit Ofer
 */
public interface ForumInterface {

    /**
     * Setting the Handlers of which the forum will read and write data
     * @param messageHandler
     * @param forumHandler
     * @param memberHandler
     */
    public void setDBHandlers(XMLMessageHandler messageHandler, ForumHandler forumHandler, XMLMemberHandler memberHandler);

    /**
     *  this methods is used to get a message from the database
     * @param messageId - the id of the required message
     * @return the message that is required
     * @throws MessageNotFoundException - is thrown when the message does not exist
     */
    public MessageInterface getMessage(int messageId) throws MessageNotFoundException;
    /**
    * used for reading all of the forum messages (might be replaced later when we decide how many messages the forum keeps in memory)
    * @return
    */
    public Vector<MessageInterface> viewForum();
   public void register(String username,String password,String nickname,
            String email,String firstName,String lastName,Date dateOfBirth) throws UserExistsException, NicknameExistsException, BadPasswordException ;
    /**
     * this method is used when the user wants to log in
     * @param username -  the user name for the login
     * @param password - the password that is used for login
     * @throws NoSuchUserException - is thrown when there is no such user
     * @throws WrongPasswordException - is thrown when the password doesn't match
     */
    public void login(String username, String password) throws NoSuchUserException, WrongPasswordException;

    /**
     * this method is used for logging out of the forum
     * @param username - the user name of the user that wants to log out
     */
    public void logout(String username);

    /**
     * this method is used for adding a new message for the forum
     * @param nickname - the nickname of the user that writes the message
     * @param Subject - the subject of the message
     * @param body - the body of the message
     */
    public int addMessage(String nickname, String Subject, String body);

    /**
     * this method is used for adding a reply to  a new message in the forum
     * @param nickname - the nickname of the user that writes the message
     * @param Subject - the subject of the message
     * @param body - the body of the message
     */
    public void addReply(int replyId, String nickname, String Subject, String body) throws MessageNotFoundException, MessageOwnerException;

    /**
     * this method is used for editing a message
     * @param nickname - the nickname of the writer
     * @param messageId -  the message id of the message that needs to be edited
     * @param newSubject -  the new subject
     * @param newBody - the new body
     * @throws MessageNotFoundException - is thrown when the message was not found
     * @throws MessageOwnerException - is thrown when the user that wants to edit the message is not the owner
     * of the message
     */
    public void editMessage(String nickname, int messageId, String newSubject, String newBody) throws MessageNotFoundException, MessageOwnerException;

    public void deleteMessage(int messageId) throws MessageNotFoundException;

    /**
     * get a vector with all the member who are login in the forum
     * @return  a vector with all the member who are login in the forum
     */
    public Vector<MemberInterface> getOnlineMembers();

    public void upgradeUser(String username) throws UserNotExistException;

    public MemberInterface getMember(String username);
}
