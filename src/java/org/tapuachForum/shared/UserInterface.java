/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.shared;

import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author Kipi
 */
public interface UserInterface {

    /**
     * this method is used when the user requests to read a certain message, by supplying the id of the message
     * that needs to be read
     * @param messageId - the id of the message
     * @return a string containing the subject and content of the mesage
     * @throws MessageNotFoundException - when there is no message with the given messageId
     */
//    public String readMessage(int messageId) throws MessageNotFoundException;
    public abstract eMemberType getType();
}
