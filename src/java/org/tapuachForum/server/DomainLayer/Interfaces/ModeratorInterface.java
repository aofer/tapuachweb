/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer.Interfaces;

import org.tapuachForum.server.Exceptions.MessageNotFoundException;

/**
 *
 * @author amit
 */
public interface ModeratorInterface extends MemberInterface {

    /**
     * delete a message from the forum
     * @param messageId - the id of the message to be deleted
     */
    public void deleteMessage(int messageId) throws MessageNotFoundException;
}
