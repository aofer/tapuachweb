/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.MemberInterface;
import Forum.DomainLayer.Logger.TapuachLogger;
import Forum.Exceptions.BadPasswordException;
import Forum.Exceptions.NicknameExistsException;
import Forum.Exceptions.NoSuchUserException;
import Forum.Exceptions.UserExistsException;
import Forum.Exceptions.UserNotExistException;
import Forum.Exceptions.WrongPasswordException;
import Forum.PersistentLayer.Data.MemberData;
import Forum.PersistentLayer.Interfaces.ForumHandlerInterface;
import Forum.PersistentLayer.Interfaces.XMLMemberInterface;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kipi
 */
public class UserHandler {

    private ForumHandlerInterface _XmlForum;
    private XMLMemberInterface _XmlMember;
    private Vector<MemberInterface> _onlineMembers;

    /**
     * constructor
     * @param xmlForum
     * @param xmlMember
     */
    public UserHandler(ForumHandlerInterface xmlForum, XMLMemberInterface xmlMember) {
        this._onlineMembers = new Vector<MemberInterface>();
        this._XmlForum = xmlForum;
        this._XmlMember = xmlMember;
    }

    /**
     * This method registrate a new member to the forum
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
    public void register(String username, String password, String nickname,
            String email, String firstName, String lastName, Date dateOfBirth) throws UserExistsException, NicknameExistsException, BadPasswordException {
        //Member newMember = new Member(newMemberData);
        if (this._XmlForum.checkUsername(username)) {
            throw new UserExistsException();
        } else if (this._XmlForum.checkNickname(nickname)) {
            throw new NicknameExistsException();
        } else if (!checkPasswordPolicy(password)) {
            throw new BadPasswordException();
        } else {
            String encryptedPassword = this.encryptPassword(password);
            this._XmlForum.register(username, nickname, encryptedPassword, email, firstName, lastName, dateOfBirth);
        }
    }

    /**
     * This method makes the user logged in to the forum
     * @param username
     * @param password
     * @throws NoSuchUserException
     * @throws WrongPasswordException
     */
    public void login(String username, String password) throws NoSuchUserException, WrongPasswordException {
        String encryptedPassword = this.encryptPassword(password);
        String tPassword = this._XmlForum.userExists(username);
        if (tPassword == null) {
            throw new NoSuchUserException(username);
        } else if (!tPassword.equals(encryptedPassword)) {
            throw new WrongPasswordException();
        } else {
            MemberData data = this._XmlMember.getMember(username);
            MemberInterface tMember;
            eMemberType type = this._XmlMember.getMemberType(username);
            switch (type) {
                case Admin:
                    tMember = new Admin(data);
                    this.addMember(tMember);
                    break;
                case Moderator:
                    tMember = new Moderator(data);
                    this.addMember(tMember);
                    break;
                case member:
                    tMember = new Member(data);
                    this.addMember(tMember);
                    break;
            }
        }

    }

    /**
     * This method log out the user
     * @param username
     */
    public void logout(String username) {
        for (int i = 0; i < this._onlineMembers.size(); i++) {
            if (this._onlineMembers.elementAt(i).getUserName().equals(username)) {
                /// ADD BY NIR.   TO MAKE USER OFFLINE ALSO ON THE XML@@@\@@!!!!!
                _XmlForum.logoff(username);
                this._onlineMembers.removeElementAt(i);
                break;
            }
        }
    }

    /**
     * This method allows the admin to upgrade other users
     * @param username, the user need to be upgraded
     * @throws UserNotExistException
     */
    public void upgradeUser(String username) throws UserNotExistException {
        this._XmlForum.upgradeUser(username);
        //Forum.getInstance().logout(username);
        //MemberData tData = this._XmlMember.getMember(username);
        //Moderator tModerator = new Moderator(tData);
        //addMember(tModerator);
    }

    /**
     * This method getting all the online members
     * @return Vector<XMLMemberInterface>
     */
    public Vector<MemberInterface> getOnlineMembers() {
        return _onlineMembers;
    }

    /**
     *this method checks if the entered user meets our password policy
     * @param password
     * @return
     */
    private boolean checkPasswordPolicy(String password) {
        return password.length() >= 8;
    }

    /**
     *add an online  member to the forum
     * @param member
     */
    private void addMember(MemberInterface member) {
        /// ADD BY NIR.   TO MAKE USER ONLINE ALSO ON THE XML@@@\@@!!!!!
        _XmlForum.login(member.getUserName());
        this._onlineMembers.add(member);
    }

    /**
     * this method encrypts a password that will later be saved in the persistent layer
     * @param password - the original password that needs to be encrypted
     * @return - the encrypted password using the chosen algorithm
     * @throws NoSuchAlgorithmException - when the ecryption algorithm isn't known in this version of java
     */
    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String encryptedPassword = "";
            byte[] b = password.getBytes();
            md.update(b);
            b = md.digest();
            for (int i = 0; i < b.length; i++) {
                encryptedPassword += String.format("%02x", 0xFF & b[i]);
            }
            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    /**
     * This method gets a memeber instance by it's username
     * @param username 
     */
    public MemberInterface getMember(String username) {
        MemberData data = this._XmlMember.getMember(username);
        MemberInterface tMember = null;
        eMemberType type = this._XmlMember.getMemberType(username);
        switch (type) {
            case Admin:
                tMember = new Admin(data);
                break;
            case Moderator:
                tMember = new Moderator(data);
                break;
            case member:
                tMember = new Member(data);
                break;
        }
        return tMember;
    }

    /**
     * This method adds a new admin to the forum
     * @param username
     * @param password
     * @param nickname
     * @param email
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     */
    public void addAdmin(String username, String password, String nickname,
            String email, String firstName, String lastName, Date dateOfBirth) {
        String encryptedPassword = encryptPassword(password);
        this._XmlForum.registerAdmin(username, nickname, encryptedPassword, email, firstName, lastName, dateOfBirth);

    }

   
    public List<Member> getMembers(){
        List<MemberData> membersData = this._XmlMember.getMember();
        List<Member> res = new LinkedList<Member>();
        eMemberType type;
        Member tMember = null;
        for (int i = 0;i < membersData.size();i++){
            MemberData data = membersData.get(i);
            type = this._XmlMember.getMemberType(data.getUserName());
            if (type == eMemberType.Admin){
            }
            else if (type == eMemberType.member){
                tMember = new Member(data);
                res.add(tMember);
            }
            else{

            }
        }
        return res;
    }
}
