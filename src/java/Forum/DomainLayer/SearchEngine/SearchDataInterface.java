/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Interfaces.MessageInterface;
import java.util.List;

/**
 *
 * @author Kipi
 */
public interface  SearchDataInterface {

    /**
     * Add a message to the search dababase
     * @param msg
     */
    public void addMessage(MessageInterface msg);

    /**
     * Remove a message from the search database
     * @param msg
     */
    public void removeMessage(MessageInterface msg);

    /**
     * Return  all the messages indexes the given string appear in there body
     * @param str
     * @return
     */
    public List<Integer> getByContent(String str);
    /**
     * Return  all the messages indexes the given author write them
     * @param str
     * @return
     */
    public List<Integer> getByAuthor(String author);
}
