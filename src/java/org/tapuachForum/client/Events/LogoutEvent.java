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


     private MemberInterface _user;

       public LogoutEvent(ApplicationEventSource source,MemberInterface user){
        super(source);
        this._user = user;
    }

    @Override
    public String getDescription() {
         return "user " + _user.getNickName() + " logged out";
    }

     public MemberInterface getUser(){
        return this._user;
    }
}


