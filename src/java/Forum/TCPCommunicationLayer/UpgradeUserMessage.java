/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.Exceptions.UserNotExistException;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amit ofer
 */
public class UpgradeUserMessage extends ClientMessage {

    private String m_username;

    public UpgradeUserMessage(String username){
        this.m_username = username;
    }

    public ServerResponse doOperation(ForumFascade forum) {
        ServerResponse tResponse;
        try {
            forum.upgradeUser(m_username); //maybe change to nickname later
            String tAns = "The user " + m_username + " was upgraded successfully.";
            tResponse = new ServerResponse(tAns, true);
        } catch (UserNotExistException ex) {
            tResponse = new ServerResponse("User does not exist.", false);
        } catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(), false);
        }
        return tResponse;
    }

}
