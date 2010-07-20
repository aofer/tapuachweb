/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.server.Exceptions;

import org.tapuachForum.shared.ForumException;

/**
 *
 * @author Yura
 */
public class MessageNotFoundException extends ForumException{

    public MessageNotFoundException(String message) {
        super(message);
    }
    
}
