/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author amit
 */
public class LoginEvent extends ApplicationEvent {

    private MemberInterface _user;
    public LoginEvent(ApplicationEventSource source,MemberInterface user){
        super(source);
        this._user = user;
    }
    public String getDescription() {
        return "user " + _user.getNickName() + " logged in";
    }
    public MemberInterface getUser(){
        return this._user;
    }
}
