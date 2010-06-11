/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.Exceptions;

/**
 *
 * @author Fishman Yevgeni
 * @serial Aug 11, 2009
 */
public class NoSuchUserException extends ForumException{

    public NoSuchUserException(String message){
        super(message);
    }

}
