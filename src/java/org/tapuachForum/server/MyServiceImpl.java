/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server;

import org.tapuachForum.server.DomainLayer.Forum;
import org.tapuachForum.server.DomainLayer.ForumFascade;
import org.tapuachForum.shared.BadPasswordException;
import org.tapuachForum.server.Exceptions.MessageNotFoundException;
import org.tapuachForum.server.Exceptions.MessageOwnerException;
import org.tapuachForum.server.Exceptions.NicknameExistsException;
import org.tapuachForum.shared.NoSuchUserException;
import org.tapuachForum.server.Exceptions.UserExistsException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
//import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.MyService;
import org.tapuachForum.shared.SearchHit;
import org.tapuachForum.shared.UserLoggedException;
import org.tapuachForum.server.Exceptions.WrongPasswordException;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.MemberData;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageData;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.eMemberType;

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
            res = "MessageNotFoundException";
        } catch (MessageOwnerException ex) {
            res = "MessageOwnerException";
        }
        return res;
    }

    //}
  public MemberInterface login(String username, String password) {
        MemberInterface clientUser;
        String res = "good";
        Forum forum = Forum.getInstance();
        try {
            forum.login(username, password);
        } catch (NoSuchUserException ex) {
            res = "There is not such user in the system";
            clientUser = new Member(new MemberData(res, "exseption", "exseption"), eMemberType.guest);
            return clientUser;
        } catch (WrongPasswordException ex) {
            res = "The Password is wrong. Please Re-Type it.";
            clientUser = new Member(new MemberData(res, "exseption", "exseption"), eMemberType.guest);
            return clientUser;
        } catch (UserLoggedException ex) {
            res = "user is alreay login. You can't login with this user!";
            clientUser = new Member(new MemberData(res, "exseption", "exseption"), eMemberType.guest);
            return clientUser;
        }
        clientUser = forum.getMember(username);
        return clientUser;
    }

    public MemberInterface logout(String username) {
         MemberInterface clientUser = null;
        Forum forum = Forum.getInstance();
        forum.logout(username);
  //      return "cool";
        return clientUser;
    }

    public String deleteMessage(int messageId) {
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
        return Forum.getInstance().viewForum();
    }

    public SearchHit[] searchByAuthor(String searchValue, int from, int to) {
        Forum forum = Forum.getInstance();
        SearchHit[] results = forum.searchByAuthor(searchValue, from, to);
    //    return new Vector(Arrays.asList(results));
//                             Date tDate = new Date();
//        SearchHit[] sh = new SearchHit[2];
//           sh[0] = new SearchHit(new Message(new MessageData("first impl","hey there","hey body", tDate, tDate)), 18);
//           sh[1] = new SearchHit(new Message(new MessageData("second impl l","hey there","hey body", tDate, tDate)), 12);
//      return sh;
         return results;

    }

    public SearchHit[] searchByContext(String searchValue, int from, int to) {
       Forum forum = Forum.getInstance();
        SearchHit[] results = forum.searchByContent(searchValue, from, to);
     //   return new Vector(Arrays.asList(results));
         return results;

    }

     public Vector<MemberInterface> onLineUsers(){
         return Forum.getInstance().getOnlineMembers();
     }
}
