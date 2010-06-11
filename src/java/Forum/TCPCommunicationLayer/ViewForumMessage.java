package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.Interfaces.MessageInterface;
import Forum.DomainLayer.Message;
import java.util.Vector;

/**
 * 
 * @author Tomer Heber 
 */
public class ViewForumMessage extends ClientMessage {

	private static final long serialVersionUID = 923990416696133754L;

	/* (non-Javadoc)
	 * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
	 */
	@Override
	public ServerResponse doOperation(ForumFascade forum) {
                Vector<MessageInterface> tForum = forum.viewForum();
               String tForumString = "";
               for(MessageInterface m : tForum) {
               tForumString += MessagesParser.Encode(m);
               }
                ServerResponse tResponse = new ServerResponse(tForumString, true);
		return tResponse;
	}

}
