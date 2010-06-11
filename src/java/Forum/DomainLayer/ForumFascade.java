/*
 * this class implements all of the forum fascade interface method by calling the forum singleton
 * it also holds the information of the user that the server communicates with
 */

package Forum.DomainLayer;


import Forum.DomainLayer.Interfaces.MemberInterface;
import Forum.DomainLayer.Interfaces.MessageInterface;
import Forum.DomainLayer.SearchEngine.SearchHit;
import Forum.Exceptions.*;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author amit ofer
 */
public class ForumFascade {

    private User _user;

    /**
     * constractor
     */
    public ForumFascade(){
        this._user = new Guest();

    }

    /**
     * This method get a string of the given message by it's id number
     * @param messageId
     * @return String
     * @throws MessageNotFoundException
     */
    public String readMessage(int messageId) throws MessageNotFoundException {
        return this._user.readMessage(messageId);
    }

    /**
     * This method returns all the messages in the forum
     * @return Vector<MessageInterface>
     */
    public Vector<MessageInterface> viewForum() {
        return Forum.getInstance().viewForum();
    }

    /**
     * This method registerate the user
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
     * @throws UserPrivilegeException
     */ 
    public void register(String username, String password, String nickname, String email, String firstName, String lastName, Date dateOfBirth) throws UserExistsException, NicknameExistsException, BadPasswordException,UserPrivilegeException {
        if (this._user.getType() == eMemberType.guest){
            ((Guest)this._user).register(username, password, nickname, email, firstName, lastName, dateOfBirth);
        }
        else{
            throw new UserPrivilegeException("Only Guests can register.");
        }
    }

    /**
     * This method makes the user loged in to the forum
     * @param username
     * @param password
     * @throws NoSuchUserException
     * @throws WrongPasswordException
     * @throws UserPrivilegeException
     */
    public void login(String username, String password) throws NoSuchUserException, WrongPasswordException,UserPrivilegeException {
        if (this._user.getType() == eMemberType.guest){
            ((Guest)this._user).logIn(username, password);
        }
        else{
            throw new UserPrivilegeException("You are already logged in.");
        }
    }

    /**
     * This makes the user loged out
     * @param username
     * @throws UserPrivilegeException
     */
    public void logout() throws UserPrivilegeException {
        if (this._user.getType() == eMemberType.guest){
            throw new UserPrivilegeException(("you are not logged in"));
        }
        else{
            ((Member)this._user).logOut();
        }
    }
    
    /**
     * This method allows a logged-in user to add new message to the forum
     * @param nickname
     * @param Subject
     * @param body
     * @throws UserPrivilegeException
     */
    public void addMessage(String Subject, String body) throws UserPrivilegeException  {
        if (this._user.getType() == eMemberType.guest){
            throw new UserPrivilegeException("Guests can't write messages, please login or register first.");
        }
        else{
            ((Member)this._user).writeMessage(Subject, body);
        }
    }
    
    /**
     * This method allows a logged-in user add reply to exists message
     * @param parentId - the id of the message that we want to add the reply to
     * @param nickname
     * @param Subject
     * @param body
     * @throws MessageNotFoundException
     * @throws UserPrivilegeException
     */
    public void addReply(int parentId,String Subject, String body) throws MessageNotFoundException,  UserPrivilegeException {
        if (this._user.getType() == eMemberType.guest){
            throw new UserPrivilegeException("Guests can't write replies, please login or register first.");
        }
        else{
            ((Member)this._user).writeReply(parentId, Subject, body);
        }
    }

    
    /**
     * This method allows a logged-in user to edit it's own message 
     * @param nickname
     * @param messageId
     * @param newSubject
     * @param newBody
     * @throws MessageNotFoundException
     * @throws MessageOwnerException
     * @throws UserPrivilegeException
     */
    public void editMessage(int messageId, String newSubject, String newBody) throws MessageNotFoundException, MessageOwnerException, UserPrivilegeException {
        if (this._user.getType() == eMemberType.guest){
            throw new UserPrivilegeException("Guests can't edit messages, please login or register first.");
        }
        else{
            ((Member)this._user).editMessage(messageId, newSubject, newBody);
        }
    }

    /**
     *  This method allows a only moderator to delete messages
     *  @param messageId
     *  @throws MessageNotFoundException
     *  @throws UserPrivilegeException
     */
    public void deleteMessage(int messageId) throws MessageNotFoundException, UserPrivilegeException {
        if (this._user.getType() == eMemberType.Moderator || this._user.getType() == eMemberType.Admin){
            ((Moderator)this._user).deleteMessage(messageId);
        }
        else {
            throw new UserPrivilegeException("Only Moderators/Admins can delete messages.");
        }
    }


    /**
     *  This method allows a only Admin to upgrade other users
     *  @param username
     *  @throws UserNotExistException
     *  @throws UserPrivilegeException
     */
    public void upgradeUser(String username) throws UserNotExistException, UserPrivilegeException {
        if (this._user.getType() == eMemberType.Admin){
            ((Admin)this._user).upgradeUser(username);
        }
        else{
            throw new UserPrivilegeException("Only Admins can upgrade users.");
        }
    }
    
    /**
     * setter for the user
     * @param messageId
     */
    public void setUser(User user){
        this._user = user;
    }
    
    /**
     * getter for the user
     * @return User
     */
    public User getUser(){
        return this._user;
    }

    /**
     * 
     * @param username
     * @param from
     * @param to
     * @return SearchHit[]
     */
    public SearchHit[] searchByAuthor(String m_nickname, int m_from, int m_to) {
        return Forum.getInstance().searchByAuthor(m_nickname, m_from, m_to);
    }

    /**
     * 
     * @param m - a message
     * @param phrase 
     * @param from
     * @param to
     * @return SearchHit[]
     */
    public SearchHit[] searchByContent(String m_phrase, int m_from, int m_to) {
        return Forum.getInstance().searchByContent(m_phrase, m_from, m_to);
    }
        public List<Member> getMembers(){
        return Forum.getInstance().getMembers();
    }
        public Vector<MemberInterface> getOnlineMembers() {
            return Forum.getInstance().getOnlineMembers();
    }
}
