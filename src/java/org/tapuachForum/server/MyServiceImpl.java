/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.server;
import org.tapuachForum.server.DomainLayer.Forum;
import org.tapuachForum.server.DomainLayer.ForumFascade;
import org.tapuachForum.server.Exceptions.BadPasswordException;
import org.tapuachForum.server.Exceptions.NicknameExistsException;
import org.tapuachForum.server.Exceptions.UserExistsException;
import org.tapuachForum.server.Exceptions.UserPrivilegeException;
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


    public String myMethod(String s) {
        // Do something interesting with 's' here on the server.
        return "Server sayssh: " + s;
    }

    public String register(String firstname, String lastName, String email, String nickname, String username, String pass) {
       Date tDate = new Date();
        String res ;
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
        return  " " + res;
    }
    public Vector<MessageInterface> viewForum(){
        return Forum.getInstance().viewForum();
    }
}