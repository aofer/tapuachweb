/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Vector;
import org.tapuachForum.shared.MessageInterface;

/**
 *
* @author Liron Katav
 */
@RemoteServiceRelativePath("myservice")
public interface MyService extends RemoteService {

    public String myMethod(String s);

    public String  register(String firstname, String lastName, String email, String nickname, String username, String pass);

    public Vector<MessageInterface> viewForum();
}
