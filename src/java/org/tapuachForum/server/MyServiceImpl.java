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
import org.tapuachForum.server.Exceptions.MessageOwnerException;
import org.tapuachForum.server.Exceptions.NicknameExistsException;
import org.tapuachForum.server.Exceptions.NoSuchUserException;
import org.tapuachForum.server.Exceptions.UserExistsException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.server.Exceptions.WrongPasswordException;
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
        int messageNum = forum.addMessage(_nickName, subject + " cool", body);
        Integer res = new Integer(messageNum);
        return res;
    }

    public String addReply(int parentId, String nickname, String subject, String body) {
        Forum forum = Forum.getInstance();
        String res = "good";
        try {
            forum.addReply(parentId, nickname, subject, body);
        } catch (MessageNotFoundException ex) {
           res = "MessageNotFoundException";
        }
        return res;
    }

    public String editMessage(String nickname, int messageId, String subject, String body) {
         String res = "good";
        Forum forum = Forum.getInstance();
        try {
            forum.editMessage(nickname, messageId, subject, body);
        } catch (MessageNotFoundException ex) {
           res ="MessageNotFoundException";
        } catch (MessageOwnerException ex) {
          res = "MessageOwnerException";
        }
        return res;
    }



    //}

    public String login(String username, String password) {
        String res ="good";
        Forum forum = Forum.getInstance();
        try {
            forum.login(username, password);
        } catch (NoSuchUserException ex) {
            res = "There is not such user in the system";
        } catch (WrongPasswordException ex) {
           res = "The Password is wrong. Please Re-Type it.";
        }
        return res;
    }
    
    public String logout(String username) {
        Forum forum = Forum.getInstance();
        forum.logout(username);
        return "cool";
    }

    public String  deleteMessage(int messageId){
       String res = "good";
        Forum forum = Forum.getInstance();
        try {
            forum.deleteMessage(messageId);
        } catch (MessageNotFoundException ex) {
           res = "MessageNotFoundException";
        }
        return res;
    }

    public Vector<MessageInterface> viewForum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
