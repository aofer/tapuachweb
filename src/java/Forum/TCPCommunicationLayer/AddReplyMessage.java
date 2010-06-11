package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class AddReplyMessage extends ClientMessage {

	private static final long serialVersionUID = 6721172261483674344L;
	
	/**
	 * The id of the message to which the reply message is added to.
	 * (The forum is nested).
	 */
	private long m_parentMessageId;
	
	/**
	 * The content of the reply message.
	 */
	private String m_content;
        private String m_subject;

	public AddReplyMessage(long parentMessageId, String subject,String content) {
		m_parentMessageId = parentMessageId;
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
                forum.addReply((int) m_parentMessageId, m_subject, m_content);
                tResponse = new ServerResponse("Reply was added  successfully.", true);
            }
            catch (MessageNotFoundException ex) {
            tResponse = new ServerResponse("Message was not found, new reply was not added!", false);
            }
            catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(), false);
            }
         return tResponse;
	}

}
