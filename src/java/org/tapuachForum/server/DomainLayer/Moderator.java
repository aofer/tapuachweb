/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer;

import org.tapuachForum.server.DomainLayer.Interfaces.ModeratorInterface;
import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.server.PersistentLayer.Data.MemberData;
import org.tapuachForum.server.PersistentLayer.Interfaces.eMemberType;

/**
 *
 * @author amit
 */
public class Moderator extends Member implements ModeratorInterface {

	/**
	 * Constructor for the Moderator class
	 * @param data
	 */
    public Moderator(MemberData data) {
        super(data);
    }

    /**
     * This method allows the Moderator to delete messages
     * @param messageId, the messageId need to be deleted
    */
    public void deleteMessage(int messageId) throws MessageNotFoundException {
        Forum.getInstance().deleteMessage(messageId);
    }

    /**
     * This method returns the type of the user
     */
    public eMemberType getType() {
        return eMemberType.Moderator;
    }
}
