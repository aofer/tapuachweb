package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.Guest;
import Forum.Exceptions.UserPrivilegeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class LogoffMessage extends ClientMessage {

	private static final long serialVersionUID = -5965616226069995574L;

	/* (non-Javadoc)
	 * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
	 */
	@Override
	public ServerResponse doOperation(ForumFascade forum) {
            ServerResponse tResponse;
            try {
            forum.logout();
            tResponse = new ServerResponse("user logged off successfully.", true);
            forum.setUser(new Guest());
        } catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(),false);
        }
            return tResponse;
	}

}
