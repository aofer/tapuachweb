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
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class MessageTreeItem extends TreeItem {

    private Button _ReplyButton;
    private Button _ModifyButton;
    private Button _DeleteButton;
    private HorizontalPanel _buttonHPanel;
    private TextBox _body;
    private TextArea _body2;
    private Label _info;

    public MessageTreeItem(final MessageInterface msg) {
        super(msg.getSubject() + "   -   " + msg.getNickname() + "  -   " + msg.getWriteDate().toString());
        _buttonHPanel = new HorizontalPanel();
        _body = new TextBox();
        _body2 = new TextArea();
        _info = new Label("");
        this._ReplyButton = new Button("Add reply");
        // this._ReplyButton.setSize("100", "25");
        this._ModifyButton = new Button("Modify message");
        this._ModifyButton.setEnabled(true);
        this._DeleteButton = new Button("Delete message");
        this._DeleteButton.setEnabled(true);
        if (ClientUser.getClient().getType() == eMemberType.guest) {
            this._DeleteButton.setEnabled(false);
            this._ModifyButton.setEnabled(false);
            this._ReplyButton.setEnabled(false);
        } else if (ClientUser.getClient().getType() == eMemberType.member && !ClientUser.getClient().getNickName().equals(msg.getNickname())) {
            this._DeleteButton.setEnabled(false);
            this._ModifyButton.setEnabled(false);
        }


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

        _ModifyButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                int num = msg.getIndex();
                String subject = msg.getSubject();
                String nickName = msg.getNickname();
                String body = msg.getBody();
                addMessageWindow hr = new addMessageWindow(num, subject, body);
            }
        });
        _ReplyButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                int num = msg.getIndex();
                addMessageWindow aw = new addMessageWindow(num);
            }
        });


    }

    public MessageInterface getMessage() {
        return (MessageInterface) getUserObject();
    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }
}
