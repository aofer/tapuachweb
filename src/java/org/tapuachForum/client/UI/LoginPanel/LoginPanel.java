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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;

/**
 *
 * @author Arseny
 */
public class LoginPanel extends Composite {

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
    private Button _logout;
    private boolean userIsOnline;
    private String userName;

    public LoginPanel() {
        userName = "who?";
        userIsOnline = false;
        _mainPanel = new VerticalPanel();
        _uLabel = new Label("Username:");
        _pLabel = new Label("Password:");
        _info = new Label("");
        _user = new TextBox();
        _pass = new PasswordTextBox();
        _login = new Button("Login");
        _logout = new Button("Logout");
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



        final AsyncCallback<String> callback = new AsyncCallback<String>() {

            public void onSuccess(String result) {

                if (!userIsOnline) {
                    if (result.startsWith("good")) {
                        userName = result.substring(5);
                        _user.setText("");
                        _pass.setText("");
                        //             _info= new Label("  User " + userName + " online");
                        _userp.remove(_uLabel);
                        _userp.remove(_user);
                        _passp.remove(_pLabel);
                        _passp.remove(_pass);
                        _buttp.remove(_reg);
                        _buttp.remove(_login);
                        _buttp.remove(_info);
                        userIsOnline = true;
                        _buttp.add(_logout);
                        _info.setText("  User " + userName + " online");
                        _buttp.add(_info);

                    } else {
                        //      _buttp.remove(_info);
                        _info.setText(result);
                        //     _buttp.add(_info);
                    }

                } else {
                    _buttp.remove(_info);
                    _buttp.remove(_logout);
                    _userp.add(_uLabel);
                    _userp.add(_user);
                    _passp.add(_pLabel);
                    _passp.add(_pass);
                    _buttp.add(_login);
                    _buttp.add(_reg);
                    userIsOnline = false;
                }
            }

            public void onFailure(Throwable caught) {
                if (!userIsOnline) {
                    _buttp.remove(_reg);
                    _buttp.remove(_login);
                    _buttp.add(_logout);
                    _buttp.add(_login);
                } else {
                    _buttp.remove(_logout);
                }
            }
        };



        // Listen for the button clicks
        _login.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //         _buttp.remove(_info);
                _info.setText("   Please wait while connecting to server...");
                //  _buttp.add(_info);
                String username = _user.getText();
                String password = _pass.getText();
                getService().login(username, password, callback);
            }
        });
        _logout.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                _info.setText("  Please wait while disconnecting...");
                //         _buttp.add(_info);
                //          userName;

                getService().logout(userName, callback);
            }
        });

        // Listen for the button clicks
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
}
