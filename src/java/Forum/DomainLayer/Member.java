package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.MemberInterface;
import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.MessageOwnerException;
import Forum.PersistentLayer.Data.MemberData;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.util.Date;

/**
 *this class represents a registered member in the forum
 * @author Amit Ofer
 */
public class Member extends User implements MemberInterface {

    private MemberData _data;

    /**
     *constructor
     * @param forum
     * @param userName
     * @param nickName
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param dateOfBirth
     */
    public Member(MemberData data) {
        super();
        this._data = data;
        this._data.setDateJoined(new Date());
    }

    /**
     *getter for the username
     * @return member's username
     */
    public String getUserName() {
        return _data.getUserName();
    }

    /**
     *getter for the password
     * @return member's password
     */
    public String getPassword() {
        return _data.getPassword();
    }

    /**
     *getter for nickName
     * @return member's nickname
     */
    public String getNickName() {
        return _data.getNickName();
    }

    /**
     *getter for the lastName
     * @return member's last name
     */
    public String getLastName() {
        return _data.getLastName();
    }

    /**
     *getter for the first name
     * @return member's first name
     */
    public String getFirstName() {
        return _data.getFirstName();
    }

    /**
     *getter for the email
     * @return member's email address
     */
    public String getEmail() {
        return _data.getEmail();
    }

    /**
     *getter for the date of  birth
     * @return member's date of birth
     */
    public Date getDateOfBirth() {
        return _data.getDateOfBirth();
    }

    /**
     *getter for the date the member registered the forum
     * @return member's registration date
     */
    public Date getDateJoined() {
        return _data.getDateJoined();
    }

    /**
     * the mothod is used when the member decides to log out of the forum
     * when the user logs out he becomes a guest and therefore can no longer write messages
     */
    public void logOut() {
        Forum.getInstance().logout(this.getUserName());
    }

    /**
     * this method is used when the member want to write a new message
     * @param subject - the subject of the message
     * @param body - the body of the message
     */
    public void writeMessage(String subject, String body)  {
        Forum.getInstance().addMessage(this.getNickName(), subject, body);
    }

    /**
     * this method is used to add a new reply to a message
     * @param parentId - the id of the parent message
     * @param subject - the subject of the new reply
     * @param body - the body of the message
     */
    public void writeReply(int parentId, String subject, String body) throws MessageNotFoundException {
        Forum.getInstance().addReply(parentId, this.getNickName(), subject, body);
    }

    /**
     * edit a message by changing the subject or the body of the message
     * @param messageId - the Id of the number that needs to be edited
     * @param subject - the new subject
     * @param body - the new body
     */
    public void editMessage(int messageId, String subject, String body) throws MessageNotFoundException, MessageOwnerException {
        Forum.getInstance().editMessage(this.getNickName(), messageId, subject, body);
    }

    /**
     * This method returns the type of the user
     */
    public eMemberType getType() {
        return eMemberType.member;
    }
}
