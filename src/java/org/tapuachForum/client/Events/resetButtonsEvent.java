/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author amit
 */
public class resetButtonsEvent extends ApplicationEvent {

    public resetButtonsEvent(ApplicationEventSource source){
        super(source);
    }

    public String getDescription() {
        return "next button";
    }

}
