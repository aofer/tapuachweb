/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.tapuachForum.server.DomainLayer.Forum;
import org.tapuachForum.server.DomainLayer.ForumFascade;
import org.tapuachForum.server.Exceptions.BadPasswordException;
import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.server.Exceptions.NicknameExistsException;
import org.tapuachForum.server.Exceptions.UserExistsException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author Liron Katav
 */
public class MyServiceImpl extends RemoteServiceServlet implements MyService {
//    ForumFascade forum = new ForumFascade();

    private ForumFascade _forum;

    public MyServiceImpl() {
        super();
        _forum = new ForumFascade();
    }

    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server sayssh: " + s;
    }

    public String register(String firstname, String lastName, String email, String nickname, String username, String pass, Date tDate) {
        String res;
        Forum forum = Forum.getInstance();
//           return "where is " + firstname;
        try {
            forum.register(username, pass, nickname, email, firstname, lastName, tDate);
            res = "Registeration was successful";
        } catch (UserExistsException ex) {
            res = username + " already exist.";
        } catch (NicknameExistsException ex) {
            res = "nicknameTBA" + " already exist.";
        } catch (BadPasswordException ex) {
            res = "password does not meet the policy, please choose a different password.";
        }
        return " " + res;
    }

    public Integer addMessage(String _nickName, String subject, String body) {
        Forum forum = Forum.getInstance();
        int messageNum = forum.addMessage(_nickName, subject+ " cool", body);
        Integer res = new Integer(messageNum);
        return res;
    }

    public String addReply(int parentId, String nickname, String subject, String body) {
             Forum forum = Forum.getInstance();
        try {
            forum.addReply(parentId, nickname, subject, body);
        } catch (MessageNotFoundException ex) {
            Logger.getLogger(MyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
                     return "add replay";
    }

 //   public void editMessage(String nickname, int messageId, String subject, String body) {

    //}

    public Vector<MessageInterface> viewForum() {
        return Forum.getInstance().viewForum();
    }

    public String editMessage(String nickname, int messageId, String subject, String body) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String logout(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String deleteMessage(int messageId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
