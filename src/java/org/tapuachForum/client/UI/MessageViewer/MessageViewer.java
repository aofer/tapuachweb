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
    private MessageTree _MessageTree;
    private Label _info;
    private ScrollPanel scrollPan;

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
        _NextButton = new Button("Next");
        _PrevButton = new Button("Prev");
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        _toolbar.add(_PrevButton);
        _toolbar.add(_NextButton);
//        _refreshButton.setEnabled(false);
        //     _NextButton.setEnabled(false);
            _PrevButton.setEnabled(false);
        //      _info = new Label("");
        _MessageTree = new MessageTree();
        _info = new Label("");
        _mainPanel.add(_info);
        _mainPanel.add(_toolbar);
        _toolbar.setVisible(true);
        scrollPan = new ScrollPanel(_MessageTree);
        scrollPan.setSize("980 px", "320 px");
        _mainPanel.add(scrollPan);
        initWidget(_mainPanel);
        //      _info.setText("Please wait while downloading mesagses from the server....");
        //     _info.setStyleName("infoText");
        refreshTree();
        checkPrivileges();
        _NextButton.addClickHandler(nextPageHandler);
        _PrevButton.addClickHandler(previousPageHandler);
        _addMessageButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                addMessageWindow aw = new addMessageWindow();
            }
        });
        _refreshButton.addClickHandler(refreshHandler);


    }

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }

    /**
     * @return the _MessageTree
     */
    public MessageTree getMessageTree() {
        return _MessageTree;
    }
    ClickHandler refreshHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            MessageViewer.this._listeners.fireEvent(new ChangeStatusEvent(MessageViewer.this, "refreshing"));
            MessageViewer.this._listeners.fireEvent(new RefreshEvent(MessageViewer.this));
            MessageViewer.this._MessageTree.refreshTree();
        }
    };
    ClickHandler nextPageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.nextPage();
            if (_MessageTree.getCurrentPage() == _MessageTree.getMaxPage()) {
                _NextButton.setEnabled(false);
            }
            if (_MessageTree.getCurrentPage() == 2) {
                _PrevButton.setEnabled(true);
            }
        }
    };
    ClickHandler previousPageHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            MessageViewer.this._MessageTree.previousPage();
            if (_MessageTree.getCurrentPage() == (_MessageTree.getMaxPage() - 1)) {
                _NextButton.setEnabled(true);
            }
            if (_MessageTree.getCurrentPage() == 1) {
                _PrevButton.setEnabled(false);
            }
        }
    };

    public void checkPrivileges() {
        if (LoginManager.getInstance().getAuthentication().getType() != eMemberType.guest) {
            _addMessageButton.setEnabled(true);
        } else {
            _addMessageButton.setEnabled(false);
        }
    }

    public void refreshTree() {
        this._MessageTree.refreshTree();
    }
}


