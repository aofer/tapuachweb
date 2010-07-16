/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author amit
 */
public class RefreshEvent extends ApplicationEvent{

    @Override
    public String getDescription() {
        return "refresh event";
    }

    public RefreshEvent(ApplicationEventSource source){
        super(source);
    }
}
