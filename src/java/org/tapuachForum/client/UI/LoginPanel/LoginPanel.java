/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.LoginPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.client.UI.RegistrationPanel;
import org.tapuachForum.shared.*;

/**
 *
 * @author Arseny
 */
public class LoginPanel extends Pane {

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
        return Locator.getInstance();
    }

    public Button getLogin() {
        return _login;
    }

    public Button getReg() {
        return _reg;
    }
    ClickHandler loginHandler = new ClickHandler() {

        public void onClick(ClickEvent event) {
            _login.setEnabled(false);
            _reg.setEnabled(false);
            _user.setEnabled(false);
            _pass.setEnabled(false);
            // change status
            LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "Please wait while the server is trying to login..."));
            String username = _user.getText();
            String password = _pass.getText();
            try {
                getService().login(username, password, loginCallback);
            } catch (NoSuchUserException ex) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "No such user, Please try again."));
                setButtons();
            } catch (WrongPasswordException ex) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "Wrong password, Please try again."));
                setButtons();
            } catch (UserLoggedException ex) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "User is already logged in."));
                setButtons();
            }
        }
    };
    final AsyncCallback<MemberInterface> loginCallback = new AsyncCallback<MemberInterface>() {

        public void onFailure(Throwable caught) {
            setButtons();
            if (caught instanceof UserLoggedException) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "User is already logged in."));
            } else if (caught instanceof UserNotExistException) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "User does not exist, please try a a different username."));
            } else if (caught instanceof WrongPasswordException) {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "Wrong password, please re-enter your password."));
            } else {
                LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "Failed to login, please try again."));
                //  _info.setText(" Problem with login. Please try again.");
            }
        }

        public void onSuccess(MemberInterface result) {
            LoginPanel.this._listeners.fireEvent(new LoginEvent(LoginPanel.this, result));
            LoginPanel.this.fireEvent(new ChangeStatusEvent(LoginPanel.this, "Login successful."));
        }
    };

    public void setButtons() {
        _login.setEnabled(true);
        _reg.setEnabled(true);
        _user.setEnabled(true);
        _pass.setEnabled(true);
    }

    public void clearFields() {
        this._user.setText("");
        this._pass.setText("");
    }
}
