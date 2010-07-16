/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer;

import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.server.PersistentLayer.Interfaces.eMemberType;

/**
 *
 * @author Kipi
 */
public  abstract class User {

    public User() {
    }

    /**
     * this method is used when the user requests to read a certain message, by supplying the id of the message
     * that needs to be read
     * @param messageId - the id of the message
     * @return a string containing the subject and content of the mesage
     * @throws MessageNotFoundException - when there is no message with the given messageId
     */
    public String readMessage(int messageId) throws MessageNotFoundException {
        return Forum.getInstance().getMessage(messageId).toString();
    }
    
    /**
     * This method returns the type of the user
     */
    public abstract eMemberType getType();
}
