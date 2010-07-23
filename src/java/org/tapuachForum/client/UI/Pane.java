/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;


import com.google.gwt.user.client.ui.Composite;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ApplicationEventListenerCollection;
import org.tapuachForum.client.Events.ApplicationEventSource;

/**
 *
 * @author amit
 */
public abstract  class Pane  extends Composite implements ApplicationEventSource{
    
    protected ApplicationEventListenerCollection _listeners;

    public Pane(){
        this._listeners = new ApplicationEventListenerCollection();
    }

    public void addListener(ApplicationEventListener listener) {
        this._listeners.add(listener);
    }

    public void removeListener(ApplicationEventListener listener) {
        this._listeners.remove(listener);
    }

    public void clearListeners() {
        this._listeners.clear();
    }
    public void fireEvent(ApplicationEvent event){
        this._listeners.fireEvent(event);
    }
}
