/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.LoginPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.Events.LogoutEvent;
import org.tapuachForum.client.Events.resetButtonsEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.client.manager.LoginManager;
import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author Arseny
 */
public class LogoutPanel extends Pane {

    private VerticalPanel _mainPanel;
    private HorizontalPanel _infop;
    private HorizontalPanel _buttp;
    private Button _logout;
    private Button _administer;

    public LogoutPanel() {
        _mainPanel = new VerticalPanel();
        _logout = new Button("Logout");
        _administer = new Button("Administer");
        _infop = new HorizontalPanel();
        _buttp = new HorizontalPanel();
        _buttp.add(_logout);
        _buttp.add(_administer);
        _administer.setVisible(false);
        _mainPanel.add(_infop);
        _mainPanel.add(_buttp);
        initWidget(_mainPanel);

        _logout.addClickHandler(logoutHandler);

    }
    ClickHandler logoutHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _logout.setEnabled(false);
            _administer.setEnabled(false);
            LogoutPanel.this.fireEvent(new ChangeStatusEvent(LogoutPanel.this, "Please wait while logging out..."));
            getService().logout(LoginManager.getInstance().getAuthentication().getUsername(), logoutCallback);
        }
    };
    final AsyncCallback logoutCallback = new AsyncCallback() {

        public void onSuccess(Object result) {
            LogoutPanel.this.fireEvent(new LogoutEvent(LogoutPanel.this));
            LogoutPanel.this.fireEvent(new ChangeStatusEvent(LogoutPanel.this, "logged out successfully."));

            //      LogoutPanel.this.fireEvent(new resetButtonsEvent(LogoutPanel.this));

        }

        public void onFailure(Throwable caught) {
            LogoutPanel.this.fireEvent(new ChangeStatusEvent(LogoutPanel.this, "logout failed."));
            setButtons();
        }
    };

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    public void setButtons() {
        _logout.setEnabled(true);
    }
}
