/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ApplicationEventListenerCollection;
import org.tapuachForum.client.Events.ApplicationEventSource;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.RefreshEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.manager.LoginManager;

/**
 *
 * @author amit ofer
 * this class is used for adding new messages,editing messages and adding replies
 */
public class addMessageWindow extends PopupPanel implements ApplicationEventSource {

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
    private ApplicationEventListenerCollection _listeners;

    /**
     * initialize the graphic components
     */
    private void initComponents() {
        this._listeners = new ApplicationEventListenerCollection();
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

    /**
     * constructor for the Add new message window
     */
    public addMessageWindow() {
        super(false, true);
        _parentId = 0;
        _messageId = 0;
        initComponents();
        _BSave.addClickHandler(addMessageHandler);
        _Bcancel.addClickHandler(cancelHandler);
    }

    /**
     * constructor for the addreply window
     * @param parentId - the id of the parent we want to add reply to
     */
    public addMessageWindow(int parentId) {
        super(false, true);
        initComponents();
        _parentId = parentId;
        _messageId = 0;
        this._BSave.addClickHandler(replyHandler);
        _Bcancel.addClickHandler(cancelHandler);
    }

    /**
     * the constructor for the edit message window
     * @param messageId -  the id of the message we edit
     * @param subject - the subject of the message
     * @param body - the body of the message
     */
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
    /**
     * click handler for the cancel button
     */
    private ClickHandler cancelHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            addMessageWindow.super.hide();
        }
    };

    /**
     * getter for the service
     * @return the service
     */
    public static MyServiceAsync getService() {
        return Locator.getInstance();
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
    /**
     * click handler for the edit window save button
     */
    private ClickHandler editHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String subject = _TBSubject.getText();
            String body = _TABody.getText();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server updating  the message.");
            getService().editMessage(LoginManager.getInstance().getAuthentication().getNickname(), _messageId, subject, body, editCallback);
            addMessageWindow.super.hide();
        }
    };
    final AsyncCallback<String> editCallback = new AsyncCallback<String>() {

        public void onSuccess(String result) {
            addMessageWindow.this._listeners.fireEvent(new ChangeStatusEvent(addMessageWindow.this, "Message was edited succesfully."));
            addMessageWindow.this._listeners.fireEvent(new RefreshEvent(addMessageWindow.this));
            //     addMessageWindow.this._listeners.fireEvent(new EditMessageEvent(addMessageWindow.this)));
        }

        public void onFailure(Throwable caught) {
            _Bcancel.setEnabled(true);
            _Bcancel.setText("Go Back");
            lResult.setText("There was a problem to edit the message. Please REFRESH the forum and try again.");

        }
    };
    /**
     * click handler for the add message save button
     */
    ClickHandler addMessageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String tSubject = _TBSubject.getText();
            String body = _TABody.getText();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server is adding your message");
            getService().addMessage(LoginManager.getInstance().getAuthentication().getNickname(), tSubject, body, addMessageCallback);
        }
    };
    final AsyncCallback addMessageCallback = new AsyncCallback() {

        public void onFailure(Throwable caught) {
            _BSave.setEnabled(true);
            _BSave.setText("Try again");
            _Bcancel.setEnabled(true);
            lResult.setStyleName("panel");
            lResult.setText("could not post message, please try again.");

        }

        public void onSuccess(Object result) {
            addMessageWindow.this._listeners.fireEvent(new ChangeStatusEvent(addMessageWindow.this, "Message was added succesfully."));
            addMessageWindow.this._listeners.fireEvent(new RefreshEvent(addMessageWindow.this));
            addMessageWindow.super.hide();
        }
    };
    /**
     * click handler for the reply save button
     */
    private ClickHandler replyHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _BSave.setEnabled(false);
            _Bcancel.setEnabled(false);
            String subject = _TBSubject.getText();
            String body = _TABody.getText();
            String nickName = LoginManager.getInstance().getAuthentication().getNickname();
            lResult.setStyleName("panel");
            lResult.setText("please wait while the server adding your message");
            getService().addReply(_parentId, nickName, subject, body, replyCallback);
        }
    };
    final AsyncCallback<String> replyCallback = new AsyncCallback<String>() {

        public void onSuccess(String result) {
            addMessageWindow.this._listeners.fireEvent(new ChangeStatusEvent(addMessageWindow.this, "Message was added succesfully."));
            addMessageWindow.this._listeners.fireEvent(new RefreshEvent(addMessageWindow.this));
            addMessageWindow.super.hide();
        }

        public void onFailure(Throwable caught) {
            _Bcancel.setEnabled(true);
            _Bcancel.setText("Go Back");
            //           lResult.setText("PROBLEM!! the problem is" +  caught.getMessage());
            lResult.setText("There was a problem to add the reply. Please REFRESH the forum and try again.");

        }
    };
}

