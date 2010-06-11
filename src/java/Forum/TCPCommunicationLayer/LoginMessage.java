package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.Member;
import Forum.DomainLayer.User;
import Forum.Exceptions.NoSuchUserException;
import Forum.Exceptions.UserPrivilegeException;
import Forum.Exceptions.WrongPasswordException;
import Forum.PersistentLayer.Interfaces.eMemberType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class LoginMessage extends ClientMessage {

    private static final long serialVersionUID = -2723317717299435031L;
    /**
     * The username of the user.
     */
    private String m_username;
    /**
     * The password of the user.
     */
    private String m_password;

    public LoginMessage(String username, String password) {
        m_username = username;
        m_password = password;
    }

    /* (non-Javadoc)
     * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
     */
    @Override
    public ServerResponse doOperation(ForumFascade forum) {
        ServerResponse tResponse;
        try {
            forum.login(m_username, m_password);
            forum.setUser((User) Forum.getInstance().getMember(m_username));
            eMemberType memberType=forum.getUser().getType();
            String tAns = memberType.toString();
            if (memberType!=eMemberType.guest)
                tAns+= ";" + ((Member)forum.getUser()).getNickName();
            else
                tAns+= ";Null";
            
            tResponse = new ServerResponse(tAns, true);
        } catch (NoSuchUserException ex) {
            String tAns = m_username + " does not exist.";
            tResponse = new ServerResponse(tAns, false);
        } catch (WrongPasswordException ex) {
            String tAns = "Wrong password.";
            tResponse = new ServerResponse(tAns, false);
        } catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(), false);
        }
        return tResponse;
    }
}
