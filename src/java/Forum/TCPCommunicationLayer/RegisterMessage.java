package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.Exceptions.BadPasswordException;
import Forum.Exceptions.NicknameExistsException;
import Forum.Exceptions.UserExistsException;
import Forum.Exceptions.UserPrivilegeException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomer Heber
 *
 */
public class RegisterMessage extends ClientMessage {

	private static final long serialVersionUID = -3267419208356408002L;
	
	/**
	 * The users real first name.
	 */
	private String m_firstName;
        /**
	 * The users real last name.
	 */
	private String m_lastName;;
	
	/**
	 * The e-mail of the user.
	 */
	private String m_email;
	
	/** 
	 * The username of the user. 
	 */
	private String m_username;
        /**
         *  the user nickname.
         */
        private String m_nickname;
	/** 
	 * The password of the user. 
	 */
	private String m_password;


	public RegisterMessage(String firstName,String lastName,String nickname, String email, String username, String password) {
		m_firstName = firstName;
                m_lastName = lastName;
		m_email = email;
		m_username = username;
		m_password = password;
                m_nickname = nickname;
	}

	/* (non-Javadoc)
	 * @see forum.tcpcommunicationlayer.ClientMessage#doOperation(forum.server.domainlayer.ForumFacade)
	 */
	@Override
	public ServerResponse doOperation(ForumFascade forum) {
            Date tDate = new Date();
            ServerResponse tResponse;
        try {
            forum.register(m_username, m_password, m_nickname, m_email, m_firstName, m_lastName, tDate);
            tResponse = new ServerResponse("Registeration was successful.", true);
        } catch (UserExistsException ex) {
            String tAns = m_username + " already exist.";
            tResponse = new ServerResponse(tAns, false);
        } catch (NicknameExistsException ex) {
            String tAns = "nicknameTBA" + " already exist.";
            tResponse = new ServerResponse(tAns, false);
        } catch (BadPasswordException ex) {
            tResponse = new ServerResponse("password does not meet the policy, please choose a different password.", false);
        }catch (UserPrivilegeException ex) {
            tResponse = new ServerResponse(ex.getMessage(), false);
        }
            return tResponse;
	}

}
