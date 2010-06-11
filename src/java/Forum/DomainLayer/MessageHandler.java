/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.MessageInterface;
import Forum.DomainLayer.Logger.TapuachLogger;
import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.MessageOwnerException;
import Forum.PersistentLayer.Data.MessageData;
import Forum.PersistentLayer.Interfaces.ForumHandlerInterface;
import Forum.PersistentLayer.Interfaces.XMLMessageInterface;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if (nickname.equals(tNickname)) {
            this._XmlForum.editMessage(messageId, subject, body, tDate);
        } else {
            throw new MessageOwnerException();
        }
    }
    /**
     * This method delete a message 
     * @param messageId
     * @throws MessageNotFoundException
     */
    public void deleteMessage(int messageId) throws MessageNotFoundException {
        this._XmlForum.deleteMessage(messageId);
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
