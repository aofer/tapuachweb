/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer;

import Forum.DomainLayer.Interfaces.AdminInterface;
import Forum.Exceptions.UserNotExistException;
import Forum.PersistentLayer.Data.MemberData;
import Forum.PersistentLayer.Interfaces.eMemberType;

/**
 *
 * @author amit
 */
public class Admin extends Moderator implements AdminInterface {

	/**
	 * Constructor for the Admin class
	 * @param data
	 */
	public Admin(MemberData data) {
        super(data);
    }

	/**
     * This method allow the admin to upgrade other users
     * @param username, the user need to be upgraded
    */
    public void upgradeUser(String username) throws UserNotExistException {
        Forum.getInstance().upgradeUser(username);
    }

    /**
     * This method returns the type of the user
     */
    public eMemberType getType() {
        return eMemberType.Admin;
    }
}
