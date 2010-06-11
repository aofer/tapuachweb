/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amit
 */
public class DeleteMessageMessage extends ClientMessage{
    
    private long m_messageId;

    public DeleteMessageMessage(long messageId){
        this.m_messageId = messageId;
    }


    public ServerResponse doOperation(ForumFascade forum) {
        ServerResponse tResponse;
        try {
            forum.deleteMessage((int) m_messageId);
            tResponse = new ServerResponse("Message was deleted successfully.", true);
        }
        catch (MessageNotFoundException ex) {
            tResponse = new ServerResponse("Message does not exist.", false);
        }
        catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(), false);
        }

        return tResponse;
    }
}
