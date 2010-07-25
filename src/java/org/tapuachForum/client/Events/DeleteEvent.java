/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author amit
 */
public class DeleteEvent extends ApplicationEvent{

    public DeleteEvent(ApplicationEventSource source){
        super(source);
    }
    public String getDescription() {
        return "deleting message";
    }

}
