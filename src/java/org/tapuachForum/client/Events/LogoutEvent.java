/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author Arseny
 */
public class LogoutEvent  extends ApplicationEvent{
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




       public LogoutEvent(ApplicationEventSource source){
        super(source);

    }

    @Override
    public String getDescription() {
         return "user " + " logged out successfully.";
    }


}


