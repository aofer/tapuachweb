/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.MessageViewer;


import org.tapuachForum.shared.MessageData;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import org.tapuachForum.shared.MessageInterface;

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
    private TextArea _body2;

    public MessageTreeItem(MessageInterface msg){
        super(msg.getSubject() + "   -   " + msg.getNickname() + "  -   " + msg.getWriteDate().toString());
        _buttonHPanel = new HorizontalPanel();
        _body = new TextBox();
        _body2 = new TextArea();
        this._ReplyButton = new Button("Add reply");
        this._ReplyButton.setSize("100", "25");
        this._ModifyButton = new Button("Modify message");
        this._ModifyButton.setEnabled(false);
        this._DeleteButton = new Button("Delete message");
        this._DeleteButton.setEnabled(false);
        this._buttonHPanel.add(_ReplyButton);
        this._buttonHPanel.add(_DeleteButton);
        this._buttonHPanel.add(_ModifyButton);
        this._buttonHPanel.setSpacing(5);
        setTitle(msg.getBody());
        setUserObject(msg);
        _body.setText(msg.getBody());
        _body2.setText(msg.getBody());
        _body.setReadOnly(true);
        _body2.setReadOnly(true);
        _body2.setSize("750px","200px");
            if (msg.getModifiedDate().compareTo(msg.getWriteDate()) != 0){
                Label dateLabel = new Label("The message was modified on " + msg.getModifiedDate().toString());
                addItem(dateLabel);
        }
//        addItem(_body);
        addItem(_body2);
        addItem(_buttonHPanel);

    }
    public MessageInterface getMessage() {
        return (MessageInterface) getUserObject();
    }
}
