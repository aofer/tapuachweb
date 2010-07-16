/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.shared.MessageInterface;

/**
 *
* @author Liron Katav
 */
public interface MyServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void register(String firstname, String lastName, String email, String nickname, String username, String pass, Date tDate, AsyncCallback<String> callback);

     public void addMessage (String _nickName, String subject, String body, AsyncCallback<Integer> callback);

    public void addReply(int parentId, String nickname, String subject, String body, AsyncCallback<String> callback);

    public void editMessage(String nickname, int messageId, String subject, String body, AsyncCallback<String> callback) ;

        public void login(String username, String password, AsyncCallback<String> callback) ;

    public void logout (String username, AsyncCallback<String> callback);

    public void  deleteMessage(int messageId, AsyncCallback<String> callback);

    public void viewForum(AsyncCallback<Vector<MessageInterface>> asyncCallback);

      }
