/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.DomainLayer;

import java.io.Serializable;
import org.tapuachForum.shared.Member;
import org.tapuachForum.server.DomainLayer.Interfaces.ModeratorInterface;
import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.shared.MemberData;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class Moderator extends Member implements ModeratorInterface,Serializable {

  public Moderator() {

    }

    /**
	 * Constructor for the Moderator class
	 * @param data
	 */
    public Moderator(MemberData data) {
        super(data,eMemberType.Moderator);
    }

        public Moderator(MemberData data, eMemberType type) {
        super(data, type);
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
