/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.LoginPanel;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Arseny
 */
public class LoginPanel  extends Composite {

    private VerticalPanel _mainPanel;
    private HorizontalPanel _userp;
    private HorizontalPanel _passp;
    private Label _uLabel;
    private TextBox _user;
    private Label _pLabel;
    private PasswordTextBox _pass;
    private Button _login;


  public LoginPanel(){
        _mainPanel = new VerticalPanel();
        _uLabel = new Label("Username:");
        _pLabel = new Label("Password:");
        _user = new TextBox();
        _pass = new PasswordTextBox();
        _login = new Button("Login");
        _userp = new HorizontalPanel();
        _userp.add(_uLabel);
        _userp.add(_user);
        _passp = new HorizontalPanel();
        _passp.add(_pLabel);
        _passp.add(_pass);
        _passp.add(_login);
        _mainPanel.add(_userp);
        _mainPanel.add(_passp);
        initWidget(_mainPanel);


  }



}
