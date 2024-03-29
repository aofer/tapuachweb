/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer;

import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.server.DomainLayer.Logger.TapuachLogger;
import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.server.Exceptions.MessageOwnerException;
import org.tapuachForum.shared.MessageData;
import org.tapuachForum.server.PersistentLayer.Interfaces.ForumHandlerInterface;
import org.tapuachForum.server.PersistentLayer.Interfaces.XMLMessageInterface;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author Kipi
 */
public class MessageHandler {

    private ForumHandlerInterface _XmlForum;
    private XMLMessageInterface _XmlMessage;

    /**
     * constructor
     * @param xmlForum
     * @param xmlMessage
     */
    public MessageHandler(ForumHandlerInterface xmlForum, XMLMessageInterface xmlMessage) {
        this._XmlForum = xmlForum;
        this._XmlMessage = xmlMessage;
    }

    /**
     * This method recieve messageId and get the message from the presistens layer
     * @param messageId
     * @return Message
     * @throws MessageNotFoundException
     */
    public Message getMessage(int messageId) throws MessageNotFoundException {
        MessageData data = this._XmlMessage.getMessage(messageId);
        Message msg = new Message(data);
        List<Integer> tReplyIds = this._XmlMessage.getRepliesIds(messageId);
        for (int i = 0;i<tReplyIds.size();i++){
            Message  tReply = getMessage(tReplyIds.get(i).intValue());
            msg.addReply(tReply);
        }

        return msg;
    }

    /**
     * This method recieve add new message to the forum
     * @param _nickName
     * @param subject
     * @param body
     */
    public int addMessage(String _nickName, String subject, String body) {
        Date tDate = new Date();
        int id=this._XmlForum.addMessage(0, _nickName, subject, body, tDate, tDate);
        return id;
    }

    /**
     * this method adds a new reply to the forum
     * @param parentId - the id of the message that we want to add the reply to
     * @param nickname
     * @param Subject
     * @param body
     * @throws MessageNotFoundException
     */
    public int addReply(int parentId, String nickname, String subject, String body) throws MessageNotFoundException {
        Date tDate = new Date();
        int id=this._XmlForum.addMessage(parentId, nickname, subject, body, tDate, tDate);
        return id;
    }

    /**
     * This method allow to esit an exist message
     * @param nickname
     * @param messageId
     * @param Subject
     * @param body
     * @throws MessageNotFoundException
     * @throws MessageOwnerException 
     */
    public void editMessage(String nickname, int messageId, String subject, String body) throws MessageNotFoundException, MessageOwnerException {
        Date tDate = new Date();
        String tNickname = this._XmlMessage.getMessage(messageId).getNickname();
        if (nickname.equals(tNickname) || Forum.getInstance().getMember(nickname).getType() == eMemberType.Moderator
                ||Forum.getInstance().getMember(nickname).getType() == eMemberType.Admin) {
            this._XmlForum.editMessage(messageId, subject, body, tDate);
        }
        else {
            throw new MessageOwnerException();
        }
    }

    public void getMessageForDelete(int messageId) throws MessageNotFoundException {
        List<Integer> tReplyIds = this._XmlMessage.getRepliesIds(messageId);
        for (int i = 0; i < tReplyIds.size(); i++) {
            getMessageForDelete(tReplyIds.get(i).intValue());
        }
        this._XmlForum.deleteMessage(messageId);
    }

    /**
     * This method delete a message 
     * @param messageId
     * @throws MessageNotFoundException
     */
    public void deleteMessage(int messageId) throws MessageNotFoundException {
        this.getMessageForDelete(messageId);
    }

    /**
     * This message returns all the message in the forum
     * @return Vector<XMLMessageInterface> -  vector of messages
     */
    public Vector<MessageInterface> viewForum() {
        Vector<MessageInterface> entireForum = new Vector<MessageInterface>();
        List<Integer> threadsIds = this._XmlMessage.getRepliesIds(0);
        for (int i = 0;i< threadsIds.size();i++){
            int tId = threadsIds.get(i).intValue();
            try {
                MessageInterface tMessage = Forum.getInstance().getMessage(tId);
                entireForum.add(tMessage);
            } catch (MessageNotFoundException ex) {
                
            }
        }

        return entireForum;
    }
}
