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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.AdminPanel;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.RegistrationPanel;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.MemberData;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.eMemberType;

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
    private Button _administer;
    private boolean userIsOnline;
    private String userName;

    public LoginPanel() {
        userIsOnline = false;
        _mainPanel = new VerticalPanel();
        _uLabel = new Label("Username:");
        _pLabel = new Label("Password:");
        _info = new Label("");
        _user = new TextBox();
        _pass = new PasswordTextBox();
        _login = new Button("Login");
        _logout = new Button("Logout");
        _administer = new Button("Administer");
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



        final AsyncCallback<MemberInterface> callback = new AsyncCallback<MemberInterface>() {

            public void onSuccess(MemberInterface result) {
                if (!userIsOnline) {
                    userName = ClientUser.getClient(result).getUserName();
                    if (result.getType() != eMemberType.guest) {
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
                        _info.setText("  User " + userName + " is online");
                        _buttp.add(_info);
                        _buttp.add(_logout);
                        //_buttp.add(_administer);
                        _logout.setEnabled(true);
                        if (userName.compareTo("admin") == 0) {
                            _buttp.add(_administer);
                        }
                        LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
                        lp.remove(3);
                        lp.remove(2);
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
                    } else {
                        //      _buttp.remove(_info);
                        userName = "bob";
                        _login.setEnabled(true);
                        _reg.setEnabled(true);
                        _info.setText(result.getUserName());
                        //     _buttp.add(_info);
                    }

                } else {
                    ClientUser.setClient();
                    _buttp.remove(_info);
                    _buttp.remove(_logout);
                    _buttp.remove(_administer);
                    _userp.add(_uLabel);
                    _userp.add(_user);
                    _passp.add(_pLabel);
                    _passp.add(_pass);
                    _buttp.add(_login);
                    _buttp.add(_reg);
                    _info.setText(userName + " is logged out.");
                    _buttp.add(_info);
                    _login.setEnabled(true);
                    _reg.setEnabled(true);
                    userIsOnline = false;
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
            }

            public void onFailure(Throwable caught) {
                if (!userIsOnline) {
                    _login.setEnabled(true);
                    _reg.setEnabled(true);
                    _info.setText(" Problem with login. Please try again.");
                } else {
                    _logout.setEnabled(true);
                    _info.setText(" Problem with logout. Please try again.");
                }

            }
        };



        // Listen for the button clicks
        _login.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                _login.setEnabled(false);
                _reg.setEnabled(false);
                //         _buttp.remove(_info);
                _info.setText("   Please wait while connecting to server...");
                //  _buttp.add(_info);
                String username = _user.getText();
                userName = username;
                String password = _pass.getText();
                getService().login(username, password, callback);
            }
        });
        _logout.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                _logout.setEnabled(false);
                _info.setText("  Please wait while disconnecting...");
                //         _buttp.add(_info);
                //          userName;

                getService().logout(ClientUser.getClient().getUserName(), callback);
            }
        });

        _reg.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //RootLayoutPanel.get().getWidget(0).setVisible(false);
                // _mainPanel.setVisible(false);
                //RootLayoutPanel.get().add(new RegistrationPanel());

                RegistrationPanel rp = new RegistrationPanel();
                rp.center();
            }
        });

        _administer.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                AdminPanel ap = new AdminPanel((""));
                ap.center();
            }
        });
        // Listen for the button clicks
        this.setStyleName("loginpanel");
        this.setWidth("230px");
        this.setHeight("120px");
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
