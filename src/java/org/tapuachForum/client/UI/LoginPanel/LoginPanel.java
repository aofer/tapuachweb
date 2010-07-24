/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.LoginPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.AdminPanel;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.client.UI.RegistrationPanel;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author Arseny
 */
public class LoginPanel extends Pane{

    private VerticalPanel _mainPanel;
    private HorizontalPanel _userp;
    private HorizontalPanel _passp;
    private HorizontalPanel _buttp;
    private Label _uLabel;
    private Label _info;
    private TextBox _user;
    private Label _pLabel;
    private PasswordTextBox _pass;
    private Button _login;
    private Button _reg;

    public LoginPanel() {
        _mainPanel = new VerticalPanel();
        _uLabel = new Label("Username:");
        _pLabel = new Label("Password:");
        _info = new Label("");
        _user = new TextBox();
        _pass = new PasswordTextBox();
        _login = new Button("Login");
        _reg = new Button("Register");
        _userp = new HorizontalPanel();
        _userp.add(_uLabel);
        _userp.add(_user);
        _passp = new HorizontalPanel();
        _passp.add(_pLabel);
        _passp.add(_pass);
        _buttp = new HorizontalPanel();
        _buttp.add(_login);
        _buttp.add(_reg);
        _buttp.add(_info);

        _mainPanel.add(_userp);
        _mainPanel.add(_passp);
        _mainPanel.add(_buttp);
        initWidget(_mainPanel);

        this.setStyleName("loginpanel");
        this.setWidth("230px");
        this.setHeight("120px");



        // Listen for the button clicks
        _login.addClickHandler(loginHandler);


        _reg.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //RootLayoutPanel.get().getWidget(0).setVisible(false);
                // _mainPanel.setVisible(false);
                //RootLayoutPanel.get().add(new RegistrationPanel());

                RegistrationPanel rp = new RegistrationPanel();
                rp.center();
            }
        });


    }

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }

    public Button getLogin() {
        return _login;
    }

    public Button getReg() {
        return _reg;
    }
    ClickHandler loginHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            //_login.setEnabled(false);
            //_reg.setEnabled(false);
            // change status
            String username = _user.getText();
            String password = _pass.getText();
            getService().login(username, password, loginCallback);
        }
    };
    
    final AsyncCallback<MemberInterface> loginCallback = new AsyncCallback<MemberInterface>() {

        public void onFailure(Throwable caught) {
            _login.setEnabled(true);
            _reg.setEnabled(true);
          //  _info.setText(" Problem with login. Please try again.");
            //TODO set status
        }

        public void onSuccess(MemberInterface result) {
            LoginPanel.this._listeners.fireEvent(new LoginEvent(LoginPanel.this,result));
        }
    };
        final AsyncCallback<MemberInterface> logoutCallback = new AsyncCallback<MemberInterface>() {

        public void onFailure(Throwable caught) {

        }

        public void onSuccess(MemberInterface result) {

        }
    };
}
