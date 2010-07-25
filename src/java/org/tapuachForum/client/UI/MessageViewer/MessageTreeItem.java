/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ApplicationEventListenerCollection;
import org.tapuachForum.client.Events.ApplicationEventSource;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.manager.LoginManager;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class MessageTreeItem extends TreeItem implements ApplicationEventSource {

    private Button _ReplyButton;
    private Button _ModifyButton;
    private Button _DeleteButton;
    private HorizontalPanel _buttonHPanel;
    private TextBox _body;
    private TextArea _body2;
    private Label _info;
    private ApplicationEventListenerCollection _listeners;
    public MessageTreeItem(final MessageInterface msg) {
        super(msg.getSubject() + "   -   " + msg.getNickname() + "  -   " + msg.getWriteDate().toString());
        _listeners = new ApplicationEventListenerCollection();
        _buttonHPanel = new HorizontalPanel();
        _body = new TextBox();
        _body2 = new TextArea();
        _info = new Label("");
        this._ReplyButton = new Button("Add reply");
        // this._ReplyButton.setSize("100", "25");
        this._ReplyButton.setEnabled(false);
        this._ModifyButton = new Button("Modify message");
        this._ModifyButton.setEnabled(false);
        this._DeleteButton = new Button("Delete message");
        this._DeleteButton.setEnabled(false);


        this._buttonHPanel.add(_ReplyButton);
        this._buttonHPanel.add(_DeleteButton);
        this._buttonHPanel.add(_ModifyButton);
        this._buttonHPanel.add(_info);
        this._buttonHPanel.setSpacing(5);
        setTitle(msg.getBody());
        setUserObject(msg);
        _body.setText(msg.getBody());
        _body2.setText(msg.getBody());
        _body.setReadOnly(true);
        _body2.setReadOnly(true);
        _body2.setSize("450px", "150px");
        if (msg.getModifiedDate().compareTo(msg.getWriteDate()) != 0) {
            Label dateLabel = new Label("The message was modified on " + msg.getModifiedDate().toString());
            addItem(dateLabel);
        }
        addItem(_body2);
        addItem(_buttonHPanel);

        _ModifyButton.addClickHandler(new ModifyHandler());
        _ReplyButton.addClickHandler(new ReplyHandler());
        _DeleteButton.addClickHandler(new DeleteHandler());
        setButtons();

    }

    public MessageInterface getMessage() {
        return (MessageInterface) getUserObject();
    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    public void setButtons() {
        if (LoginManager.getInstance().getAuthentication().getType() == eMemberType.Admin
                || LoginManager.getInstance().getAuthentication().getType() == eMemberType.Moderator) {
            _DeleteButton.setEnabled(true);
            _ModifyButton.setEnabled(true);
            _ReplyButton.setEnabled(true);
        } else if (LoginManager.getInstance().getAuthentication().getType() == eMemberType.member) {
            _ReplyButton.setEnabled(true);
            if (getMessage().getNickname().equals(LoginManager.getInstance().getAuthentication().getNickname())) {
                _ModifyButton.setEnabled(true);
            }
        }
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

    protected class ModifyHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            int num = getMessage().getIndex();
            String subject = getMessage().getSubject();
            String nickName = getMessage().getNickname();
            String body = getMessage().getBody();
            addMessageWindow hr = new addMessageWindow(num, subject, body);
        }
    }

    protected class AddMessageHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    protected class ReplyHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            int num = getMessage().getIndex();
            addMessageWindow aw = new addMessageWindow(num);
        }
    }
    protected class DeleteHandler implements ClickHandler{

        public void onClick(ClickEvent event) {
            MessageTreeItem.this._listeners.fireEvent(new ChangeStatusEvent(MessageTreeItem.this,"Trying to delete the message..."));
            getService().deleteMessage(getMessage().getIndex(), new AsyncCallback(){

                public void onFailure(Throwable caught) {
                    MessageTreeItem.this._listeners.fireEvent(new ChangeStatusEvent(MessageTreeItem.this,"Message could not be deleted."));
                }

                public void onSuccess(Object result) {
                    MessageTreeItem.this._listeners.fireEvent(new ChangeStatusEvent(MessageTreeItem.this,"Message was deleted successfully."));
                }
                
            });
        }
        
    }
}
