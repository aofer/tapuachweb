/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.*;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
/**
 *
 * @author amit
 */
public class RegistrationPanel  extends DockLayoutPanel{

    final HorizontalPanel firstNamePanel;
    final HorizontalPanel lastNamePanel;
    final HorizontalPanel emailPanel;
    final HorizontalPanel nicknamePanel;
    final HorizontalPanel usernamePanel;
    final HorizontalPanel passwordPanel;
    final HorizontalPanel rePasswordPanel;
    final HorizontalPanel buttonsPanel;
    final VerticalPanel vPanel;
    //labels
    final Label lFirstName;
    final Label lLastName;
    final Label lEmail;
    final Label lNickName;
    final Label lUsername;
    final Label lPassword;
    final Label lRePassword;
    final Label lResult;/////////////////////////////test
    //text boxes
    final TextBox tFirstName;
    final TextBox tLastName;
    final TextBox tEmail;
    final TextBox tNickName;
    final TextBox tUsername;
    final PasswordTextBox tPassword;
    final PasswordTextBox tRePassword;
    final Label lRegisteration;
    final Button bRegister;
    final Button bCancel;
    //error Labels
    final Label lFirstNameError;
    final Label lLastNameError;
    final Label lemailError;
    final Label lnicknameError;
    final Label lusernameError;
    final Label lpasswordError;
    final Label lrePasswordError;

    public RegistrationPanel() {
        super(Unit.PX);
        //buttons
        bRegister = new Button("Register");
        bCancel = new Button("Cancel");
        //panels
        firstNamePanel = new HorizontalPanel();
        lastNamePanel = new HorizontalPanel();
        emailPanel = new HorizontalPanel();
        nicknamePanel = new HorizontalPanel();
        usernamePanel = new HorizontalPanel();
        passwordPanel = new HorizontalPanel();
        rePasswordPanel = new HorizontalPanel();
        buttonsPanel = new HorizontalPanel();
        vPanel = new VerticalPanel();

        //firstName
        lFirstName = new Label("First name:");
        lFirstName.setStyleName("labelStyle");
        lFirstNameError = new Label(" ");
        lFirstNameError.setStyleName("error");
        tFirstName = new TextBox();

        //lastName
        lLastName = new Label("Last name:");
        lLastName.setStyleName("labelStyle");
        lLastNameError = new Label("");
        lLastNameError.setStyleName("error");
        tLastName = new TextBox();

        //email
        lEmail = new Label("Email:");
        lEmail.setStyleName("labelStyle");
        lemailError = new Label("");
        lemailError.setStyleName("error");
        tEmail = new TextBox();

        //nickname
        lNickName = new Label("Nickname:");
        lNickName.setStyleName("labelStyle");
        lnicknameError = new Label("");
        lnicknameError.setStyleName("error");
        tNickName = new TextBox();


        //username
        lUsername = new Label("Username:");
        lUsername.setStyleName("labelStyle");
        lusernameError = new Label("");
        lusernameError.setStyleName("error");
        tUsername = new TextBox();

        //passwords
        lPassword = new Label("Password:");
        lPassword.setStyleName("labelStyle");
        tPassword = new PasswordTextBox();
        lpasswordError = new Label("");
        lpasswordError.setStyleName("error");

        //rePassword
        lRePassword = new Label("Re-enter password:");
        lRePassword.setStyleName("labelStyle");
        tRePassword = new PasswordTextBox();
        lrePasswordError = new Label("");
        lrePasswordError.setStyleName("error");
        lResult = new Label("result");

          //header
        lRegisteration = new Label("Registration");
        lRegisteration.setStyleName("headline");
        this.addNorth(lRegisteration, 50);
        this.setStyleName("panel");
        this.setSize("400px","400px");
       

        firstNamePanel.add(lFirstName);
        firstNamePanel.add(tFirstName);

        lastNamePanel.add(lLastName);
        lastNamePanel.add(tLastName);

        emailPanel.add(lEmail);
        emailPanel.add(tEmail);

         nicknamePanel.add(lNickName);
          nicknamePanel.add(tNickName);

        usernamePanel.add(lUsername);
        usernamePanel.add(tUsername);

        passwordPanel.add(lPassword);
        passwordPanel.add(tPassword);

        rePasswordPanel.add(lRePassword);
        rePasswordPanel.add(tRePassword);

        buttonsPanel.add(bCancel);
        buttonsPanel.add(bRegister);

        vPanel.add(lFirstNameError);
        vPanel.add(firstNamePanel);

         vPanel.add(lLastNameError);
        vPanel.add(lastNamePanel);

        vPanel.add(lemailError);
        vPanel.add(emailPanel);

        vPanel.add(lnicknameError);
        vPanel.add(nicknamePanel);

        vPanel.add(lusernameError);
        vPanel.add(usernamePanel);

        vPanel.add(lpasswordError);
        vPanel.add(passwordPanel);
        vPanel.add(lrePasswordError);
        vPanel.add(rePasswordPanel);

        vPanel.add(buttonsPanel);

       // hPanel.add(vPanelLeft);
        //hPanel.add(vPanelRight);
        //adds to left
     //   grid.setWidget(0, 0, lFirstNameError);
        //grid.setWidget(1, 0, lFirstName);
      /*  grid.setWidget(2, 0, lLastName);
        grid.setWidget(3, 0, lEmail);
        grid.setWidget(4, 0, lNickName);
        grid.setWidget(5, 0, lUsername);
        grid.setWidget(6, 0, lPassword);
        grid.setWidget(7, 0, lRePassword);
        grid.setWidget(8, 0, bCancel);
       // vPanelLeft.add(lFirstName);
        //vPanelLeft.add(lLastName);
       // vPanelLeft.add(lEmail);
        //vPanelLeft.add(lNickName);
        //vPanelLeft.add(lUsername);
        //vPanelLeft.add(lPassword);
        //vPanelLeft.add(lRePassword);
        //vPanelLeft.add(bCancel);
        //adds to right
     //   grid.setWidget(0, 1, tFirstName);
        grid.setWidget(1, 1, tLastName);

        grid.setWidget(2, 1, tEmail);
        grid.setWidget(3, 1, tNickName);
        grid.setWidget(4, 1, tUsername);
        grid.setWidget(5, 1, tPassword);
        grid.setWidget(6, 1, tRePassword);
        grid.setWidget(7, 1, bRegister);

       // vPanelRight.add(tFirstName);
        //vPanelRight.add(tLastName);
        //vPanelRight.add(tEmail);
        //vPanelRight.add(tNickName);
        //vPanelRight.add(tUsername);
        //vPanelRight.add(tPassword);
        //vPanelRight.add(tRePassword);
        //vPanelRight.add(bRegister);
      
        //hPanel.add(grid);*/
        this.addSouth(lResult,50);
        this.add(vPanel);

         // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                  lResult.setText(result);
            }

            public void onFailure(Throwable caught) {
                lResult.setText("Communication failed");
            }
        };
                
        // Listen for the button clicks
        bRegister.addClickHandler(new ClickHandler() {
            // Make remote call. Control flow will continue immediately and later
            // 'callback' will be invoked when the RPC completes.

            public void onClick(ClickEvent event) {
                if (correctInput()) {
                    String firstname = tFirstName.getText();
                    String lastName = tLastName.getText();
                    String email = tEmail.getText();
                    String nickname = tNickName.getText();
                    String username = tUsername.getText();
                    String pass = tPassword.getText();
                    String rePass = tRePassword.getText();
                //getService().myMethod("test", callback);
                getService().register(firstname, lastName, email, nickname, username, pass, callback);
                }
            }
        });

         // Listen for the button clicks
        bCancel.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                remove(vPanel);
            }
        });
    }

      public static MyServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.
        return GWT.create(MyService.class);
    }

      private boolean correctInput() {
          boolean ans = true;
        if (tFirstName.getText().compareTo("") == 0) {
            lFirstNameError.setText( "please enter a first name");
            ans = false;
        }
        if (tLastName.getText().compareTo("") == 0) {
            lLastNameError.setText( "please enter a last name");
            ans= false;
        }

        if (tEmail.getText().compareTo("") == 0) {                 //.matches("[a-zA-Z0-9]+@([a-zA-Z0-9]+)[.]{1}([a-zA-Z.]+)")) {
            lemailError.setText( "wrong email adress");
            ans= false;
        }

        if (tUsername.getText().compareTo("") == 0) {
           lusernameError.setText( "please enter a username");
            ans= false;
        }

        if (tNickName.getText().compareTo("") == 0) {
              lnicknameError.setText( "please enter a nickname");
            ans= false;
        }

          if (tPassword.getText().compareTo("") == 0) {
              lpasswordError.setText( "please enter a password");
            ans= false;
        }
          if (tRePassword.getText().compareTo("") == 0) {
              lrePasswordError.setText( "please re-enter password");
            ans= false;
        }
        String p1 = new String(tPassword.getText());
        String p2 = new String(tRePassword.getText());
        if (p1.compareTo(p2) != 0) {
              lpasswordError.setText( "passwords don't match,please try again");
              lrePasswordError.setText( "");
              tPassword.setText("");
              tRePassword.setText("");
            ans= false;
        }
        return ans;
    }
}

