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
import com.google.gwt.user.client.ui.*;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;

/**
 *
 * @author amit
 */
public class addMessageWindow extends PopupPanel {

    // final PopupPanel _pp = new PopupPanel(false);
    private FormPanel _mainPanel = new FormPanel();
    private VerticalPanel _holder;
    private Label _header;
    private Label _LSubject;
    private TextBox _TBSubject;
    private Label _LBody;
    private TextArea _TABody;
    private Button _BSave;
    private Button _Bcancel;
    private HorizontalPanel _buttonsPanel;
    private Label lResult;/////////////////////////////test
    private int _parentId;
    private int _messageId;

    private void initComponents() {
        _mainPanel.setAction("/myFormHandler");
        _mainPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        _mainPanel.setMethod(FormPanel.METHOD_POST);
        _holder = new VerticalPanel();
        _holder.setStyleName("blueBack");
        _mainPanel.setWidget(_holder);
        this.setWidget(_mainPanel);
        _header = new Label("Add message");
        _LSubject = new Label("Subject:");
        _TBSubject = new TextBox();
        _TBSubject.setWidth("500px");
        _LBody = new Label("body:");
        _TABody = new TextArea();
        _TABody.setWidth("500px");
        _TABody.setHeight("200px");
        lResult = new Label("Please enter the messege details or press cancel to go back to the Forum");
        _BSave = new Button("Save");
        _Bcancel = new Button("cancel");
        _buttonsPanel = new HorizontalPanel();
        _holder.add(_header);
        _holder.add(_LSubject);
        _holder.add(_TBSubject);
        _holder.add(_LBody);
        _holder.add(_TABody);
        _buttonsPanel.add(_BSave);
        _buttonsPanel.add(_Bcancel);
        _holder.add(_buttonsPanel);
        _holder.add(lResult);
        this.setGlassEnabled(true);
        this.center();
    }

    public addMessageWindow() {
        super(false, true);
        _parentId = 0;
        _messageId = 0;
        initComponents();
        _BSave.addClickHandler(addMessageHandler);
        _Bcancel.addClickHandler(cancelHandler);
    }

    public addMessageWindow(int parentId) {
        super(false, true);
        initComponents();
        _parentId = parentId;
        _messageId = 0;
        this._BSave.addClickHandler(replyHandler);
        _Bcancel.addClickHandler(cancelHandler);
    }

    public addMessageWindow(int messageId, String subject, String body) {
        super(false, true);
        initComponents();
        _parentId = 0;
        _messageId = messageId;
        this._TBSubject.setText(subject);
        this._TABody.setText(body);
        this._BSave.addClickHandler(editHandler);
        _Bcancel.addClickHandler(cancelHandler);
    }
    private ClickHandler cancelHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            addMessageWindow.super.hide();
        }
    };
    private ClickHandler editHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String subject = _TBSubject.getText();
            String body = _TABody.getText();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server updating  the message.");
            getService().editMessage(ClientUser.getClient().getNickName(), _messageId, subject, body, editCallback);
            addMessageWindow.super.hide();
        }
    };
    private ClickHandler addMessageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String subject = _TBSubject.getText();
            String body = _TABody.getText();
            String _nickName = ClientUser.getClient().getNickName();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server adding your message.");
            getService().addMessage(_nickName, subject, body, callback);
        }
    };
    private ClickHandler replyHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String subject = _TBSubject.getText();
            String body = _TABody.getText();
            String nickName = ClientUser.getClient().getNickName();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server adding your message");
            getService().addReply(_parentId, nickName, subject, body, replyCallback);
        }
    };

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }
    final AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

        public void onSuccess(Integer result) {
            addMessageWindow.super.hide();
            LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
            lp.remove(2);
            lp.remove(3);
            //ONline Panel (number 2)
            OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
            lp.add(op);
            lp.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
            lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);
            //  MESSAGES panerl  (number 3)
            MessageViewer m = new MessageViewer();
            m.setSize("980 px", "320 px");
            m.setHeight("320px");
            lp.add(m);
            lp.setWidgetTopHeight(m, 104, Unit.PX, 430, Unit.PX);
            m.setStyleName("messageviewer");
        }

        public void onFailure(Throwable caught) {
            _Bcancel.setEnabled(true);
            _Bcancel.setText("Go Back");
            lResult.setText("There was a problem to add the message. Please REFRESH the forum and try again.");
        }
    };
    final AsyncCallback<String> replyCallback = new AsyncCallback<String>() {

        public void onSuccess(String result) {
            addMessageWindow.super.hide();
            LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
            lp.remove(2);
            lp.remove(3);
            //ONline Panel (number 2)
            OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
            lp.add(op);
            lp.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
            lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);
            //  MESSAGES panerl  (number 3)
            MessageViewer m = new MessageViewer();
            m.setSize("980 px", "320 px");
            m.setHeight("320px");
            lp.add(m);
            lp.setWidgetTopHeight(m, 104, Unit.PX, 430, Unit.PX);
            m.setStyleName("messageviewer");
        }

        public void onFailure(Throwable caught) {
            _Bcancel.setEnabled(true);
            _Bcancel.setText("Go Back");
            //           lResult.setText("PROBLEM!! the problem is" +  caught.getMessage());
            lResult.setText("There was a problem to add the reply. Please REFRESH the forum and try again.");

        }
    };
    final AsyncCallback<String> editCallback = new AsyncCallback<String>() {

        public void onSuccess(String result) {
            addMessageWindow.super.hide();
            LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
            lp.remove(2);
            lp.remove(3);
            //ONline Panel (number 2)
            OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
            lp.add(op);
            lp.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
            lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);
            //  MESSAGES panerl  (number 3)
            MessageViewer m = new MessageViewer();
            m.setSize("980 px", "320 px");
            m.setHeight("320px");
            lp.add(m);
            lp.setWidgetTopHeight(m, 104, Unit.PX, 430, Unit.PX);
            m.setStyleName("messageviewer");
        }

        public void onFailure(Throwable caught) {
            _Bcancel.setEnabled(true);
            _Bcancel.setText("Go Back");
            lResult.setText("There was a problem to edit the message. Please REFRESH the forum and try again.");

        }
    };
}

