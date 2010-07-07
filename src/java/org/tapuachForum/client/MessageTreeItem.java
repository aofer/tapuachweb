/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;

/**
 *
 * @author amit
 */
public class MessageTreeItem extends TreeItem {
    private Button _ReplyButton;
    private Button _ModifyButton;
    private Button _DeleteButton;
    private HorizontalPanel _buttonHPanel ;
    private TextBox _body;

    public MessageTreeItem(Message msg){
        super(msg.getSubject() + "   -   " + msg.getNickname());
        _buttonHPanel = new HorizontalPanel();
        _body = new TextBox();
        this._ReplyButton = new Button("Add reply");
        this._ReplyButton.setSize("100", "25");
        this._ModifyButton = new Button("Modify message");
        this._DeleteButton = new Button("Delete message");
        this._buttonHPanel.add(_ReplyButton);
        this._buttonHPanel.add(_DeleteButton);
        this._buttonHPanel.add(_ModifyButton);
        this._buttonHPanel.setSpacing(5);
        setTitle(msg.getBody());
        setUserObject(msg);
        _body.setText(msg.getBody());
        _body.setReadOnly(true);
        Label l1 = new Label(msg.getBody());
        addItem(l1);
//        addItem(_body);
        addItem(_buttonHPanel);
    }
    public Message getMessage() {
        return (Message) getUserObject();
    }
}
