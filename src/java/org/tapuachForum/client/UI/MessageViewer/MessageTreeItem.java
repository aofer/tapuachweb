/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.tapuachForum.shared.MessageData;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.MessageInterface;

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

    public MessageTreeItem(final MessageInterface msg) {
        super(msg.getSubject() + "   -   " + msg.getNickname() + "  -   " + msg.getWriteDate().toString());
        _buttonHPanel = new HorizontalPanel();
        _body = new TextBox();
        _body2 = new TextArea();
        this._ReplyButton = new Button("Add reply");
        this._ReplyButton.setSize("100", "25");
        this._ModifyButton = new Button("Modify message");
        this._ModifyButton.setEnabled(true);
        this._DeleteButton = new Button("Delete message");
        this._DeleteButton.setEnabled(true);
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
        _body2.setSize("750px", "200px");
        if (msg.getModifiedDate().compareTo(msg.getWriteDate()) != 0) {
            Label dateLabel = new Label("The message was modified on " + msg.getModifiedDate().toString());
            addItem(dateLabel);
        }
//        addItem(_body);
        addItem(_body2);
        addItem(_buttonHPanel);

        _ModifyButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                int num = msg.getIndex();
                String subject = msg.getSubject();
                String nickName = msg.getNickname();
                String body = msg.getBody();
                //      _body2.setVisible(false);
                //      _buttonHPanel.setVisible(false);

           //     RootLayoutPanel.get().getWidget(0).setVisible(false);
             editMessageWindow hr = new editMessageWindow(num, subject, nickName, body);
                //    RootLayoutPanel.get().add(new editMessageWindow(num, subject, nickName, body));

            }
        });


        _ReplyButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                int num = msg.getIndex();
         //       RootLayoutPanel.get().getWidget(0).setVisible(false);
              //  RootLayoutPanel.get().add(new replayMessageWindow(num));
              replyMessageWindow aw =new replyMessageWindow(num,msg.getSubject());
            }
        });

        final AsyncCallback<String> callback = new AsyncCallback<String>() {

            public void onSuccess(String result) {
                if (result.equals("good")) {
                    _body.setText(" MESSAGE AS BEET DELETED!!!");
                } else {
                    _body.setText(" MESSAGE AS not BEET DELETED!!! because " + result);
                }


            }

            public void onFailure(Throwable caught) {
                // lResult.setText("Communication failed");
                //       lResult.setStyleName("redResutl") ;
                _body.setText(" MESSAGE AS BEET DELETED!!! (MAYBE)");

            }
        };

        _DeleteButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                _body.setText("  **** THE MESSEAGE WILL BE DELETE!!!  THERE IS PROBLEM WITH THE COMPASS, SO IT IS STILL NOT WORKING.. BUT IT IS READY...");
                getService().deleteMessage(msg.getIndex(), callback);

            }
        });


    }

    public MessageInterface getMessage() {
        return (MessageInterface) getUserObject();
    }

    public static MyServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.
        return GWT.create(MyService.class);
    }
}
