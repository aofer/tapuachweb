/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class Authentication {
    private String _username;
    private String _nickname;
    private eMemberType _type;


    public Authentication(String username,String nickname,eMemberType type){
        this._nickname = nickname;
        this._username = username;
        this._type = type;
    }
    public Authentication(){
        this("","",eMemberType.guest);
    }

    /**
     * @return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return the _nickname
     */
    public String getNickname() {
        return _nickname;
    }

    /**
     * @return the _type
     */
    public eMemberType getType() {
        return _type;
    }

}
