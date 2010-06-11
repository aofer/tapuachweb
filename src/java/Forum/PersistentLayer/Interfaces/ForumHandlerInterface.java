package Forum.PersistentLayer.Interfaces;

import Forum.Exceptions.UserNotExistException;
import Forum.Exceptions.MessageNotFoundException;
import java.util.Date;

/**
*
* @author Liron Katav
*/

public interface ForumHandlerInterface {
	
	/**
     * add a new member to the forum
         * @param userName
         * @param password
         * @param nickName
         * @param eMail
         * @param lastName
         * @param firstName
         * @param dateOfBirth
         */
	public void register ( 	String userName , 
							String nickName,
							String password,
							String eMail,	
							String firstName,	
							String lastName,
							Date dateOfBirth);
	
	/**
     * check if the username already exists 
     * @param username 
     * @return username password if exists or NULL if not*/
	public String userExists (String username);

          /**
     * check if the username exists
     * @param username
     * @return true if the username already exists */
	public boolean checkUsername (String username);
        
	/**
     * check if the nickname exists
     * @param nickname 
     * @return true if the nickname already exists */
	public boolean checkNickname (String nickname);
	
	/**
     * check if the password  exists
     * @param password 
     * @return true if the password already exists */
	public boolean checkPassword (String password);
	
	/**
     * set the status of the user to be online
         * @param username
         */
	public void login (String username);
	
	/**
     * set the status of the user to be offline
     * @param username a message id */
	public void logoff (String username);
		
	/**
     * add a new message to the forum
         * @param parentId
         * @param body
         * @param createdBy
         * @param subject
         * @param DateAdded
         * @param modifyDate
         */
	public int addMessage ( int parentId, String createdBy, String subject,String body, Date DateAdded, Date modifyDate);
        /**
         * edits a message
         * @param messageId - the id of the message to be edited
         * @param newSubject - the new subject
         * @param newBody - the new body
         * @param dateModified - current date
         */
        public void editMessage(int messageId,String newSubject,String newBody,Date dateModified);

        /**
         * used for deleting a message from the forum
         * @param messageId - the id of the message that will be deleted
         * @throws MessageNotFoundException - is thrown when the message was not found
         */
        public void deleteMessage(int messageId) throws MessageNotFoundException;

        /**
         * upgrade a user into a moderator
         * @param username - the username of the user we want to upgrade
         * @throws UserNotExistException - is thrown when the user does not exist
         */
        public void upgradeUser(String username) throws UserNotExistException;
       /**
        * add a Admin to the forum
        * @param userName
        * @param password
        * @param nickName
        * @param eMail
        * @param lastName
        * @param firstName
        * @param dateOfBirth
        */
        public void registerAdmin(String userName, String nickName, String password, String eMail, String firstName, String lastName, Date dateOfBirth);

        /**
         * only init the file to be clean.
         */
        public void initForum();

}
