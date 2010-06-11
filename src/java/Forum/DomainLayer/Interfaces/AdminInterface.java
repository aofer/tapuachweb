/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer.Interfaces;

import Forum.Exceptions.UserNotExistException;

/**
 *
 * @author amit
 */
public interface AdminInterface extends ModeratorInterface {

    /**
     * upgrade a regular logged in user into a forum moderator
     * @param username - the username of the user that is going to be upgraded
     */
    public void upgradeUser(String username) throws UserNotExistException;
}
