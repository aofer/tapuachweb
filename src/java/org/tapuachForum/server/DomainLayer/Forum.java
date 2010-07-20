/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer;

import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.NoSuchUserException;
import org.tapuachForum.shared.UserLoggedException;
import org.tapuachForum.shared.BadPasswordException;
import org.tapuachForum.shared.Message;
import org.tapuachForum.server.PersistentLayer.SQLMemberHandler;
import org.tapuachForum.server.PersistentLayer.XMLMessageHandler;
import org.tapuachForum.server.PersistentLayer.SQLForumHandler;
import org.tapuachForum.server.PersistentLayer.XMLMemberHandler;
import org.tapuachForum.server.PersistentLayer.SQLMessageHandler;
import org.tapuachForum.server.PersistentLayer.ForumHandler;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.server.Exceptions.*;
import org.tapuachForum.server.DomainLayer.Interfaces.ForumInterface;
import org.tapuachForum.server.DomainLayer.SearchEngine.SearchEngineHandler;
import org.tapuachForum.shared.SearchHit;
import org.tapuachForum.server.PersistentLayer.Interfaces.ForumHandlerInterface;
import org.tapuachForum.server.PersistentLayer.Interfaces.XMLMemberInterface;
import org.tapuachForum.server.PersistentLayer.Interfaces.XMLMessageInterface;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.server.DomainLayer.Logger.*;
import org.tapuachForum.server.DomainLayer.SearchEngine.CompassSearchHandler;
import org.tapuachForum.server.DomainLayer.SearchEngine.SearchEngineInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *this class is the main logics of our forum
 * it communicates with the persistent layer through a pre defined interfaces
 * @author Amit Ofer
 */
public class Forum implements ForumInterface {

    private MessageHandler _messageHandler;
    private UserHandler _userHandler;
    private SearchEngineInterface _searchHandler;
    private static Forum _forum;

    /**
     * Singleton design implementation
     * @return Forum instance if exist, or new if it doesn't
     */
    public static Forum getInstance() {
        if (_forum == null) {
            _forum = new Forum();

        }
        return _forum;
    }

    /**
     * private constructor for the Singleton design
     */
     private Forum() {   //added stuff to the constructor in order to make it work
        //XMLFileHandler xf = new XMLFileHandler("testforum.xml"); //currently using the testforum xml file
        ForumHandlerInterface xmlForumHandler = new SQLForumHandler();
        XMLMessageInterface xmlMessageHandler = new SQLMessageHandler();
        XMLMemberInterface xmlMemberHandler = new SQLMemberHandler();
        this._messageHandler = new MessageHandler(xmlForumHandler, xmlMessageHandler);
        this._userHandler = new UserHandler(xmlForumHandler, xmlMemberHandler);
        this._searchHandler = new CompassSearchHandler();
        //this._searchHandler = new SearchEngineHandler();
        Date tDate = new Date();
       addAdmin("admin", "admin", "adminy", "thebestadmin@gmail.com", "ad", "min", tDate);


    }

    /**
     * Set the DBHandlers
     * @param messageHandler
     * @param forumHandler
     * @param memberHandler
     */
    public void setDBHandlers(XMLMessageHandler messageHandler, ForumHandler forumHandler, XMLMemberHandler memberHandler) {
        this._messageHandler = new MessageHandler(forumHandler, messageHandler);
        this._userHandler = new UserHandler(forumHandler, memberHandler);
    }

    /**
     * this is the getter for the vector of online members
     * @return a vector containing all online members
     */
    public Vector<MemberInterface> getOnlineMembers() {
        return _userHandler.getOnlineMembers();
    }

    /**
     * This method returns a message by its messageId
     * @param messageId
     * @return message
     * @throws MessageNotFoundException
     */
    public synchronized MessageInterface getMessage(int messageId) throws MessageNotFoundException {
        return this._messageHandler.getMessage(messageId);
    }

// omri's version
/*    public XMLMemberInterface register(MemberData newMember) throws UserExistsException, NicknameExistsException, BadPasswordException {
    return _userHandler.register(newMember);
    }*/
// amit's version
    /**
     * This method transpot the user's detail to the userHandler class
     * @param username
     * @param password
     * @param nickname
     * @param email
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @throws UserExistsException
     * @throws NicknameExistsException
     * @throws BadPasswordException
     */
    public synchronized void register(String username, String password, String nickname,
            String email, String firstName, String lastName, Date dateOfBirth) throws UserExistsException, NicknameExistsException, BadPasswordException {
        TapuachLogger.getInstance().info("user:  " + username + " registered to the forum");
        this._userHandler.register(username, password, nickname, email, firstName, lastName, dateOfBirth);
    }

    /**
     * This method transpot the username and  password for login to the 
     * userHandler class
     * @param username
     * @param password
     * @throws NoSuchUserException
     * @throws WrongPasswordException
     */
    public synchronized void login(String username, String password) throws NoSuchUserException, WrongPasswordException,UserLoggedException {
        TapuachLogger.getInstance().info("user:  " + username + " logged in");
        this._userHandler.login(username, password);
    }

    /**
     * This method transpot the username for logout to the userHandler class
     * @param username
     */
    public synchronized void logout(String username) {
        TapuachLogger.getInstance().info("user:  " + username + " logged out");
        this._userHandler.logout(username);
    }

    /**
     * This method transpot the new message's details messageHandler class
     * @param nickname
     * @param Subject
     * @param body
     */
    public synchronized int addMessage(String nickname, String Subject, String body) { //might needs to throw NoSuchUserException in case there is no user with that nickname
        TapuachLogger.getInstance().info("nickname: " + nickname + " added new message");
        int id = this._messageHandler.addMessage(nickname, Subject, body);
        try {
            Message msg = this._messageHandler.getMessage(id);
            this._searchHandler.addMessage(msg);
        } catch (MessageNotFoundException ex) {
        }
        return id;
    }

    /**
     * This method transpot the new reply's details messageHandler class
     * @param parentId - the id of the message that we want to add the reply to
     * @param nickname
     * @param Subject
     * @param body
     * @throws MessageNotFoundException
     */
    public synchronized void addReply(int parentId, String nickname, String Subject, String body) throws MessageNotFoundException {
        TapuachLogger.getInstance().info("nickname: " + nickname + " add reply to message number:" + parentId);
        int id=this._messageHandler.addReply(parentId, nickname, Subject, body);
                try {
            Message msg = this._messageHandler.getMessage(id);
            this._searchHandler.addMessage(msg);
        } catch (MessageNotFoundException ex) {
        }
    }

    /**
     * This method transpot the edited messgage to the messageHandler class
     * @param nickname
     * @param messageId
     * @param newSubject
     * @param newBody
     * @throws MessageNotFoundException
     * @throws MessageOwnerException
     */
    public synchronized void editMessage(String nickname, int messageId, String newSubject, String newBody) throws MessageNotFoundException, MessageOwnerException {
        TapuachLogger.getInstance().info("nickname: " + nickname + " edit message number:" + messageId);
        this._messageHandler.editMessage(nickname, messageId, newSubject, newBody);
        Message msg = this._messageHandler.getMessage(messageId);
        this._searchHandler.updateMessage(msg);
    }

    /**
     *  This method transpot the id's messgage that need to delete to the 
     *  messageHandler class
     *  @param messageId
     *  @throws MessageNotFoundException
     */
    public synchronized void deleteMessage(int messageId) throws MessageNotFoundException {
        TapuachLogger.getInstance().info("message number:" + messageId + " deleted");
        Message msg = _messageHandler.getMessage(messageId);
        this._searchHandler.removeMessage(msg);
        this._messageHandler.deleteMessage(messageId);
    }

    /**
     *  This method transpot the username, who need to be upgraded to the 
     *  userHandler class
     *  @param username
     *  @throws UserNotExistException
     */
    public synchronized void upgradeUser(String username) throws UserNotExistException {
        TapuachLogger.getInstance().info("user:  " + username + " has been upgraded");
        this._userHandler.upgradeUser(username);
    }

    /**
     * This method returns all the messages in the forum
     * @return Vector<XMLMessageInterface>
     */
    public synchronized Vector<MessageInterface> viewForum() {
        return this._messageHandler.viewForum();
    }

    /**
     * This method transpot the context need to search to the searchHandler class
     * @param m - a message
     * @param phrase 
     * @param from
     * @param to
     * @return SearchHit[]
     */
    public synchronized SearchHit[] searchByContent(String phrase, int from, int to) {
        return _searchHandler.searchByContent(phrase, from, to);
    }

    /**
     * This method transpot the Author's details to the searchHandler class
     * @param username
     * @param from
     * @param to
     * @return SearchHit[]
     */
    public synchronized SearchHit[] searchByAuthor(String username, int from, int to) {
        return _searchHandler.searchByAuthor(username, from, to);
    }

    /**
     * This method transpot the username userHandler class
     *  @param username
     *  @return XMLMemberInterface
     *  */
    public synchronized MemberInterface getMember(String username) {
        return _userHandler.getMember(username);
    }

    /**
     * This method transpot the new admin's details to the userHandler class
     * @param username
     * @param password
     * @param nickname
     * @param email
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     */
    private void addAdmin(String username, String password, String nickname,
            String email, String firstName, String lastName, Date dateOfBirth) {
        TapuachLogger.getInstance().info(username + " registered to the forum as Admin");
        this._userHandler.addAdmin(username, password, nickname, email, firstName, lastName, dateOfBirth);
    }
    public synchronized List<Member> getMembers(){
        return this._userHandler.getMembers();
    }

    public  void forumSetup(){
        SQLForumHandler.forumInit();
        
    }


}
