/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResetButton;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.Events.LogoutEvent;
import org.tapuachForum.client.Events.RefreshEvent;
import org.tapuachForum.client.Events.resetButtonsEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.LoginPanel.LogoutPanel;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.SearchPanel.searchPanel;
import org.tapuachForum.client.manager.LoginManager;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit
 */
public class mainWindow extends Composite {

    private LayoutPanel _mainPanel;
    private LoginPanel _loginPanel;
    private LogoutPanel _logoutPanel;
    private OnlinePanel _onlinePanel;
    private MessageViewer _messageViewer;
    private StatusPanel _statusPanel;
    private searchPanel _searchPanel;

    public mainWindow() {
        _mainPanel = new LayoutPanel();
        initWidget(_mainPanel);
        //_statusPanel panel
        _statusPanel = new StatusPanel();
        _mainPanel.add(_statusPanel);
        _mainPanel.setWidgetTopHeight(_statusPanel, 85, Unit.PX, 20, Unit.PX);
        _mainPanel.setWidgetLeftRight(_statusPanel, 10, Unit.PX, 100, Unit.PX);

        //logout panel
        _logoutPanel = new LogoutPanel();
        _logoutPanel.addListener(new LogoutPanelListener());

        //LOGIN Panel ( number 0)
        _loginPanel = new LoginPanel();
        _mainPanel.add(_loginPanel);
        _mainPanel.setWidgetLeftRight(_loginPanel, 700, Unit.PX, 20, Unit.PX);
        _mainPanel.setWidgetTopHeight(_loginPanel, 5, Unit.PX, 125, Unit.PX);
        _loginPanel.addListener(new LoginPanelListener());



        //   SERACH PANEL      (number 1)
        _searchPanel = new searchPanel();
        _mainPanel.add(_searchPanel);
        _mainPanel.setWidgetTopHeight(_searchPanel, 533, Unit.PX, 100, Unit.PX);
        _mainPanel.setWidgetLeftRight(_searchPanel, 10, Unit.PX, 500, Unit.PX);

        //ONline Panel (number 2)
        _onlinePanel = new OnlinePanel();
        _mainPanel.add(_onlinePanel);
        _mainPanel.setWidgetTopHeight(_onlinePanel, 533, Unit.PX, 100, Unit.PX);
        _mainPanel.setWidgetLeftRight(_onlinePanel, 550, Unit.PX, 40, Unit.PX);
        _onlinePanel.addListener(new OnlinePanelListener());

        //  MESSAGES panerl  (number 3)
        _messageViewer = new MessageViewer();
        _messageViewer.setSize("980 px", "320 px");
        //       Scrol_mainPanelanel s = new Scrol_mainPanelanel(_messageViewer);
        _messageViewer.setHeight("320px");
        _mainPanel.add(_messageViewer);
        _mainPanel.setWidgetTopHeight(_messageViewer, 104, Unit.PX, 430, Unit.PX);
        _messageViewer.setStyleName("messageviewer");
        _messageViewer.addListener(new MessageViewerListener());

        //       RootLayoutPanel.get().add(_mainPanel);


        Window.addWindowClosingHandler(new Window.ClosingHandler() {

            @Override
            public void onWindowClosing(final ClosingEvent event) {
                if (LoginManager.getInstance().getAuthentication().getType() != eMemberType.guest) {
                    //event.setMessage(" " + ClientUser.getClient().getNickName() + ", you are still login. Please go back and logout.");
                    getService().logout(LoginManager.getInstance().getAuthentication().getUsername(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }

                        public void onSuccess(Object result) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }
                    });
                } else {
                    //              event.setMessage(" bye bye ");
                }
            }
        });
    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    protected class LoginPanelListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof LoginEvent) {
                _mainPanel.remove(_loginPanel);
                _mainPanel.add(_logoutPanel);
                _mainPanel.setWidgetLeftRight(_logoutPanel, 700, Unit.PX, 20, Unit.PX);
                _mainPanel.setWidgetTopHeight(_logoutPanel, 5, Unit.PX, 125, Unit.PX);
                LoginEvent tEvent = (LoginEvent) event;
                LoginManager.getInstance().setAuthentication(tEvent.getUser());
                _logoutPanel.setButtons();
                refresh();

            } else if (event instanceof ChangeStatusEvent) {
                _statusPanel.SetStatus(event.getDescription());
            }

        }
    }

    protected class LogoutPanelListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof LogoutEvent) {
                _mainPanel.remove(_logoutPanel);
                _mainPanel.add(_loginPanel);
                _mainPanel.setWidgetLeftRight(_loginPanel, 700, Unit.PX, 20, Unit.PX);
                _mainPanel.setWidgetTopHeight(_loginPanel, 5, Unit.PX, 125, Unit.PX);
                LoginManager.getInstance().resetAuthentication();
                _loginPanel.clearFields();
                _loginPanel.setButtons();
                refresh();

            } else if (event instanceof ChangeStatusEvent) {
                _statusPanel.SetStatus(event.getDescription());
            } else if (event instanceof resetButtonsEvent) {
                _messageViewer.checkPrivileges();
            }

        }
    }

    protected class OnlinePanelListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof RefreshEvent) {
                _onlinePanel.refreshUsers();
            } else if (event instanceof ChangeStatusEvent) {
                _statusPanel.SetStatus(event.getDescription());
            }
        }
    }

    protected class MessageViewerListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof RefreshEvent) {
                _onlinePanel.refreshUsers();
            } else if (event instanceof ChangeStatusEvent) {
                _statusPanel.SetStatus(event.getDescription());
            }
        }
    }

    private void refresh() {
        this._messageViewer.refreshTree();
        this._onlinePanel.refreshUsers();
        this._messageViewer.checkPrivileges();
    }
}


