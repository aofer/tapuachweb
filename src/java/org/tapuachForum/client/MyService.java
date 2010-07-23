/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
@RemoteServiceRelativePath("myservice")
public interface MyService extends RemoteService {


    public String  register(String firstname, String lastName, String email, String nickname, String username, String pass, Date tDate);

   public Integer addMessage (String _nickName, String subject, String body);

    public String addReply(int parentId, String nickname, String subject, String body);

    public String editMessage(String nickname, int messageId, String subject, String body);

   public MemberInterface login(String username, String password);

    public MemberInterface logout (String username);

   public String  deleteMessage(int messageId);

    public Vector<MessageInterface> viewForum();

     public Vector<MemberInterface> onLineUsers();

    public SearchHit[] searchByAuthor(String searchValue, int from, int to);

    public SearchHit[] searchByContext(String searchValue, int from, int to);

       public String upgradeUser(String username);

                public  List<Member> getMembers();
}
