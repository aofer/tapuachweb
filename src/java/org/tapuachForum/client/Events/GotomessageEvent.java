/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author Arseny
 */
public class GotomessageEvent extends  ApplicationEvent{


private int _msgNum;

  public GotomessageEvent(ApplicationEventSource source,int msgIndex){
        super(source);
        _msgNum = msgIndex;
    }

        @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMessageNumber() {
        return _msgNum;
    }
     


}
