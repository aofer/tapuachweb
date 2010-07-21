/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.SearchHit;

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

    public void login(String username, String password, AsyncCallback<MemberInterface> callback) ;

    public void logout (String username, AsyncCallback<MemberInterface> callback);

    public void  deleteMessage(int messageId, AsyncCallback<String> callback);

    public void viewForum(AsyncCallback<Vector<MessageInterface>> asyncCallback);

    public void searchByAuthor(String searchValue, int i, int i0, AsyncCallback<SearchHit[]> callback);

    public void searchByContext(String searchValue, int i, int i0, AsyncCallback<SearchHit[]> callback);

   public void onLineUsers(AsyncCallback<Vector<MemberInterface>> asyncCallback);

    public void  getMembers(AsyncCallback<List<Member>> callback);

    public void upgradeUser(String username, AsyncCallback<String> asyncCallback);

      }
