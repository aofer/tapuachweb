/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.Events;

/**
 *
 * @author amit
 */
public class EditMessageEvent extends ApplicationEvent {

    private String _subject;
    private String _body;
    public EditMessageEvent(ApplicationEventSource source,String subject,String body){
        super(source);
        this._subject = subject;
        this._body = body;
    }

    public String getDescription() {
        return "message was edited";
    }

    /**
     * @return the _subject
     */
    public String getSubject() {
        return _subject;
    }

    /**
     * @return the _body
     */
    public String getBody() {
        return _body;
    }

}
