

package org.tapuachForum.client.Events;

import java.util.ArrayList;

public class ApplicationEventListenerCollection extends ArrayList<ApplicationEventListener> {

    /**
     * Fires the given event to all the contained listeners.
     *
     * @param event The event to be fired.
     */
    public void fireEvent(ApplicationEvent event) {
        for (ApplicationEventListener listener : this) {
            listener.handle(event);
        }
    }

}
