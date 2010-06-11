package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.MessageOwnerException;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class ModifyMessageMessage extends ClientMessage {

	private static final long serialVersionUID = -4738980852130566587L;
	
	/**
	 * The id of the message which the client wants to modify.
	 * (The forum is nested).
	 */
	private long m_messageId;
	
	/**
	 * The new content of the message.
	 */
	private String m_content;
        private String m_subject;

	public ModifyMessageMessage(long messageId, String subject,String content) {
		m_messageId = messageId;
                m_subject = subject;
		m_content = content;
	}

	/* (non-Javadoc)
	 * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
	 */
	@Override
	public ServerResponse doOperation(ForumFascade forum) {
            ServerResponse tResponse;
            try {
                forum.editMessage((int) m_messageId, m_subject, m_content);
                tResponse = new ServerResponse("Message was modified successfully.", true);
            } catch (MessageNotFoundException ex) {
                String tAns = "Message " + m_messageId + " does not exist.";
                tResponse = new ServerResponse(tAns, false);
            } catch (MessageOwnerException ex) {
                String tAns = "You are not the owner of this message.";
                tResponse = new ServerResponse(tAns,false);
            } catch (UserPrivilegeException ex) {
                tResponse = new ServerResponse(ex.getMessage(),false);
            }
		return tResponse;
	}

}
