/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author amit
 */
public class ChangeStatusEvent extends ApplicationEvent {
    private String _status;

    public ChangeStatusEvent(ApplicationEventSource source,String status){
        super(source);
        this._status = status;
    }
    public String getDescription() {
        return this._status;
    }

}
