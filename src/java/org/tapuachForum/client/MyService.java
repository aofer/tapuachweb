/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.shared.MessageInterface;

/**
 *
* @author Liron Katav
 */
@RemoteServiceRelativePath("myservice")
public interface MyService extends RemoteService {

    public String myMethod(String s);

    public String  register(String firstname, String lastName, String email, String nickname, String username, String pass, Date tDate);

   public Integer addMessage (String _nickName, String subject, String body);

    public String addReply(int parentId, String nickname, String subject, String body);

    public void editMessage(String nickname, int messageId, String subject, String body);


    public Vector<MessageInterface> viewForum();
}
