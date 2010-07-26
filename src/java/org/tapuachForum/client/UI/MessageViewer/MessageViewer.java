/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Vector;
import org.tapuachForum.client.Events.*;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.client.manager.LoginManager;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit ofer
 */
public class MessageViewer extends Pane {
    //fields

    private VerticalPanel _mainPanel;
    private HorizontalPanel _toolbar;
    private HorizontalPanel _treebar;
    private Button _addMessageButton;
    private Button _refreshButton;
    private Button _NextButton;
    private Button _PrevButton;
    private Button _FirstButton;
    private Button _LastButton;
    private MessageTree _MessageTree;
    private Label _info;
    private ScrollPanel scrollPan;
    private Label _pageNum;

    public MessageViewer() {
        super();
        _mainPanel = new VerticalPanel();
        _toolbar = new HorizontalPanel();
        _treebar = new HorizontalPanel();
        _addMessageButton = new Button("Add message");
        if (ClientUser.getClient() == null) {
            ClientUser.setClient();
        }
        if (ClientUser.getClient().getType() == eMemberType.guest) {
            _addMessageButton.setEnabled(false);
        }
        _refreshButton = new Button("Refresh");
        _NextButton = new Button(">");
        _PrevButton = new Button("<");
        _FirstButton = new Button("<<");
        _LastButton = new Button(">>");
        _pageNum = new Label("1/1");
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        _toolbar.add(_FirstButton);
        _toolbar.add(_PrevButton);
        _toolbar.add(_NextButton);
        _toolbar.add(_LastButton);
        _toolbar.add(_pageNum);
//        _refreshButton.setEnabled(false);
        _NextButton.setEnabled(false);
        _PrevButton.setEnabled(false);
        _refreshButton.setEnabled(false);
        _LastButton.setEnabled(false);
        _FirstButton.setEnabled(false);
        //      _info = new Label("");
        _MessageTree = new MessageTree();
        _MessageTree.addListener(new MessageTreeListener());
        _info = new Label("");
        _mainPanel.add(_info);
        _mainPanel.add(_toolbar);
        _toolbar.setVisible(true);
        scrollPan = new ScrollPanel(_MessageTree);
        scrollPan.setSize("980 px", "320 px");
        _mainPanel.add(scrollPan);
        initWidget(_mainPanel);
        refreshTree();
        checkPrivileges();
        _NextButton.addClickHandler(nextPageHandler);
        _PrevButton.addClickHandler(previousPageHandler);
        _FirstButton.addClickHandler(new  FirstPageHandler());
        _LastButton.addClickHandler(new  LastPageHandler());
        _addMessageButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                addMessageWindow aw = new addMessageWindow();
                aw.addListener(new addMessageWindowListener());
            }
        });
        _refreshButton.addClickHandler(refreshHandler);


    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    /**
     * @return the _MessageTree
     */
    public MessageTree getMessageTree() {
        return _MessageTree;
    }
    ClickHandler refreshHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            refresh();
        }
    };
    ClickHandler nextPageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.nextPage();
            if (_MessageTree.getCurrentPage() == _MessageTree.getMaxPage()) {
                _NextButton.setEnabled(false);
                _LastButton.setEnabled(false);
            }
            if (_MessageTree.getCurrentPage() == 2) {
                _PrevButton.setEnabled(true);
                _FirstButton.setEnabled(true);
            }
            setPageNum();
        }
    };
    ClickHandler previousPageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.previousPage();
            if (_MessageTree.getCurrentPage() == (_MessageTree.getMaxPage() - 1)) {
                _NextButton.setEnabled(true);
                _LastButton.setEnabled(true);
            }
            if (_MessageTree.getCurrentPage() == 1) {
                _PrevButton.setEnabled(false);
                _FirstButton.setEnabled(false);
            }
            setPageNum();
        }
    };

    public void checkPrivileges() {
        if (LoginManager.getInstance().getAuthentication().getType() != eMemberType.guest) {
            _addMessageButton.setEnabled(true);
        } else if (LoginManager.getInstance().getAuthentication().getType() == eMemberType.guest) {
            _addMessageButton.setEnabled(false);
        }
    }

    public void refreshTree() {
        this._MessageTree.refreshTree();

    }

    protected class MessageTreeListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof ChangeStatusEvent) {
                MessageViewer.this.fireEvent(event);
            } else if (event instanceof resetButtonsEvent) {
                resetButtons();
                MessageViewer.this.fireEvent(new ChangeStatusEvent(MessageViewer.this, "done."));
            } else if (event instanceof RefreshEvent) {
                refresh();
            }
            else if (event instanceof DeleteEvent){
                resetButtons();
            }
        }
    }

    public void resetButtons() {
        if (LoginManager.getInstance().getAuthentication().getType() != eMemberType.guest) {
            _addMessageButton.setEnabled(true);
        }
        else{
            _addMessageButton.setEnabled(false);
        }
        if (_MessageTree.getCurrentPage() > 1) {
            _PrevButton.setEnabled(true);
            _FirstButton.setEnabled(true);
        }
        if (_MessageTree.getCurrentPage() < _MessageTree.getMaxPage()) {
            _NextButton.setEnabled(true);
            _LastButton.setEnabled(true);
        }
        _refreshButton.setEnabled(true);
        setPageNum();
    }

    protected class addMessageWindowListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof RefreshEvent) {
                refresh();
            }
        }
    }

    public void refresh() {
        MessageViewer.this._listeners.fireEvent(new ChangeStatusEvent(MessageViewer.this, "refreshing..."));
        MessageViewer.this._listeners.fireEvent(new RefreshEvent(MessageViewer.this));
        //set all the buttons to be disabled while refreshing
        //       _PrevButton.setEnabled(false);
        //     _NextButton.setEnabled(false);
        _refreshButton.setEnabled(false);
        _addMessageButton.setEnabled(false);
        MessageViewer.this._MessageTree.refreshTree();
    }

    protected class FirstPageHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.firstPage();
            MessageViewer.this._FirstButton.setEnabled(false);
            MessageViewer.this._PrevButton.setEnabled(false);
            if (_MessageTree.getCurrentPage() < (_MessageTree.getMaxPage() )) {
                _NextButton.setEnabled(true);
                _LastButton.setEnabled(true);
            }
            else{
                _NextButton.setEnabled(false);
                _LastButton.setEnabled(false);
            }
            setPageNum();
        }
    }
        protected class LastPageHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.lastPage();
            MessageViewer.this._LastButton.setEnabled(false);
            MessageViewer.this._NextButton.setEnabled(false);
            if (_MessageTree.getCurrentPage() > 1) {
                _PrevButton.setEnabled(true);
                _FirstButton.setEnabled(true);
            }
            else{
                _PrevButton.setEnabled(false);
                _FirstButton.setEnabled(false);
            }
            setPageNum();
        }
    }
        public void setPageNum(){
            this._pageNum.setText(_MessageTree.getCurrentPage() + "/" + _MessageTree.getMaxPage());
        }
        public void gotoMessage(int index){
            this._MessageTree.gotoMessage(index);
            resetButtons();
        }
}


