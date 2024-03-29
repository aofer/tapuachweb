/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.manager;

import org.tapuachForum.client.Authentication;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class LoginManager {
    private static LoginManager loginManager;
    private Authentication authentication;

    private LoginManager() {
        authentication = new Authentication();
    }

    public static LoginManager getInstance(){
        if (loginManager == null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }
    /**
     * @return the authentication
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * @param aAuthentication the authentication to set
     */
    public void setAuthentication(Authentication aAuthentication) {
        authentication = aAuthentication;
    }

    public void setAuthentication(MemberInterface user) {
        authentication = new Authentication(user.getUserName(), user.getNickName(), user.getType());
    }



    public void resetAuthentication() {
        this.authentication = new Authentication();
    }
}
