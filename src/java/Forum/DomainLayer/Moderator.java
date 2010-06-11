/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.ModeratorInterface;
import Forum.Exceptions.MessageNotFoundException;
import Forum.PersistentLayer.Data.MemberData;
import Forum.PersistentLayer.Interfaces.eMemberType;

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
