/**
 * 
 */
package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.*;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class AddMessageMessage extends ClientMessage {

	private static final long serialVersionUID = 8912617401305761411L;
	
	/* The content of the message to add. */
	private String m_subject;
        private String m_content;

	public AddMessageMessage(String subject,String content) {
	   this.m_subject = subject;
            m_content = content;
	}

	/* (non-Javadoc)
	 * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
	 */
	@Override
	public ServerResponse doOperation(ForumFascade forum) {
            // parsing of m_content in order to get the nickname** subject and body
            ServerResponse tResponse;
            try {
                forum.addMessage(m_subject, m_content);
                tResponse = new ServerResponse("Message was added  successfully.",true);

            } catch (UserPrivilegeException ex) {
                tResponse = new ServerResponse(ex.getMessage(),false);
            }
		return tResponse;
	}

}
