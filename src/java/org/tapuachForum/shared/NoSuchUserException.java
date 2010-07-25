/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.shared;

import java.io.Serializable;

/**
 *
 * @author Fishman Yevgeni
 * @serial Aug 11, 2009
 */
public class NoSuchUserException extends ForumException implements Serializable{

    public NoSuchUserException(String message){
        super(message);
    }
    public NoSuchUserException(){
        super("");
    }
}
