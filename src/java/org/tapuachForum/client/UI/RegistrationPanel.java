/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import java.util.Date;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
/**
 *
 * @author amit
 */
public class RegistrationPanel  extends DockLayoutPanel{
    DockLayoutPanel thisSuper = this;
    final HorizontalPanel firstNamePanel;
    final HorizontalPanel lastNamePanel;
    final HorizontalPanel emailPanel;
    final HorizontalPanel nicknamePanel;
    final HorizontalPanel usernamePanel;
    final HorizontalPanel passwordPanel;
    final HorizontalPanel rePasswordPanel;
    final HorizontalPanel buttonsPanel;
    final HorizontalPanel birthDayPanel;

    final VerticalPanel vPanel;
    final DockPanel vPanel2;
    //labels
    final Label lFirstName;
    final Label lLastName;
    final Label lEmail;
    final Label lNickName;
    final Label lUsername;
    final Label lPassword;
    final Label lRePassword;
    final Label lBirthDay;
    final Label lResult;/////////////////////////////test
    //text boxes
    final TextBox tFirstName;
    final TextBox tLastName;
    final TextBox tEmail;
    final TextBox tNickName;
    final TextBox tUsername;
    final DateBox tBirthDay;
    final Date tDate;



    final PasswordTextBox tPassword;
    final PasswordTextBox tRePassword;
    final Label lRegisteration;
    final Button bRegister;
    final Button bCancel;
    final Button  bClear;
    //error Labels
    final Label lFirstNameError;
    final Label lLastNameError;
    final Label lemailError;
    final Label lnicknameError;
    final Label lusernameError;
    final Label lpasswordError;
    final Label lrePasswordError;
     final Label lBirthDayError;

    public RegistrationPanel() {
        super(Unit.PX);
        //buttons
        bRegister = new Button("Register");
        bCancel = new Button("Cancel");
        bClear = new Button("clear");
        //panels
        firstNamePanel = new HorizontalPanel();
        lastNamePanel = new HorizontalPanel();
        emailPanel = new HorizontalPanel();
        nicknamePanel = new HorizontalPanel();
        usernamePanel = new HorizontalPanel();
        passwordPanel = new HorizontalPanel();
        rePasswordPanel = new HorizontalPanel();
        buttonsPanel = new HorizontalPanel();
        birthDayPanel = new HorizontalPanel();

        vPanel = new VerticalPanel();


        //firstName
        lFirstName = new Label("First name:");
        lFirstName.setStyleName("autoFormItem-Label");
        lFirstNameError = new Label(" ");
        lFirstNameError.setStyleName("error");
        tFirstName = new TextBox();
        tFirstName.setStyleName("autoFormItem-Textbox");

        //lastName
        lLastName = new Label("Last name:");
        lLastName.setStyleName("autoFormItem-Label");
        lLastNameError = new Label("");
        lLastNameError.setStyleName("error");
        tLastName = new TextBox();
        tLastName.setStyleName("autoFormItem-Textbox");

        //email
        lEmail = new Label("Email:");
        lEmail.setStyleName("autoFormItem-Label");
        lemailError = new Label("");
        lemailError.setStyleName("error");
        tEmail = new TextBox();
        tEmail.setStyleName("autoFormItem-Textbox");
        //nickname
        lNickName = new Label("Nickname:");
        lNickName.setStyleName("autoFormItem-Label");
        lnicknameError = new Label("");
        lnicknameError.setStyleName("error");
        tNickName = new TextBox();
        tNickName.setStyleName("autoFormItem-Textbox");

        //username
        lUsername = new Label("Username:");
        lUsername.setStyleName("autoFormItem-Label");
        lusernameError = new Label("");
        lusernameError.setStyleName("error");
        tUsername = new TextBox();
        tUsername.setStyleName("autoFormItem-Textbox");
        //passwords
        lPassword = new Label("Password:");
        lPassword.setStyleName("autoFormItem-Label");
        tPassword = new PasswordTextBox();
        lpasswordError = new Label("");
        lpasswordError.setStyleName("error");
        tPassword.setStyleName("autoFormItem-Textbox");
        //rePassword
        lRePassword = new Label("Re-enter password:");
        lRePassword.setStyleName("autoFormItem-Label");
        tRePassword = new PasswordTextBox();
        lrePasswordError = new Label("");
        lrePasswordError.setStyleName("error");
          tRePassword.setStyleName("autoFormItem-Textbox");

        lBirthDay = new Label("Birth Day");
        lBirthDay.setStyleName("autoFormItem-Label");
        tBirthDay = new DateBox();
        tBirthDay.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
         tDate = new Date();
         Date tDate2 = new Date();
    // Set the value in the text box when the user selects a date
            tBirthDay.getDatePicker().addValueChangeHandler(new ValueChangeHandler() {
      public void onValueChange(ValueChangeEvent event) {
       Date today = new Date();
          Date date = (Date) event.getValue();
      if (date.compareTo(today) >0)
          lBirthDayError.setText("please re-enter logic birth day. Time traveling is not support yet");
      else{
           lBirthDayError.setText("");
        tBirthDay.setValue(date);
      }

      }
    });
     tBirthDay.getDatePicker().setStyleName("blueBack");
        lBirthDayError = new Label("");
        lBirthDayError.setStyleName("error");


        lResult = new Label("result");
          //header
        lRegisteration = new Label("Registration");
        lRegisteration.setStyleName("headline");
        this.addNorth(lRegisteration, 50);
        this.setStyleName("panel");
        this.setSize("400px","520px");


        firstNamePanel.add(lFirstName);
        firstNamePanel.add(tFirstName);
        firstNamePanel.setStyleName("autoFormItem-Panel");


        lastNamePanel.add(lLastName);
        lastNamePanel.add(tLastName);
        lastNamePanel.setStyleName("autoFormItem-Panel");


        emailPanel.add(lEmail);
        emailPanel.add(tEmail);
        emailPanel.setStyleName("autoFormItem-Panel");

         nicknamePanel.add(lNickName);
          nicknamePanel.add(tNickName);
        nicknamePanel.setStyleName("autoFormItem-Panel");

        usernamePanel.add(lUsername);
        usernamePanel.add(tUsername);
        usernamePanel.setStyleName("autoFormItem-Panel");

        passwordPanel.add(lPassword);
        passwordPanel.add(tPassword);
        passwordPanel.setStyleName("autoFormItem-Panel");

        rePasswordPanel.add(lRePassword);
        rePasswordPanel.add(tRePassword);
        rePasswordPanel.setStyleName("autoFormItem-Panel");

        birthDayPanel.add(lBirthDay);
        birthDayPanel.add(tBirthDay);
        birthDayPanel.setStyleName("autoFormItem-Panel");


        buttonsPanel.add(bCancel);
        buttonsPanel.add(bRegister);
        buttonsPanel.add(bClear);

        vPanel.add(new HTML("<div class='infoProse'>Please enter your details.</div>"));

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

        vPanel.add(lBirthDayError);
        vPanel.add(birthDayPanel);

        vPanel.add(buttonsPanel);
        vPanel.setCellVerticalAlignment(buttonsPanel, HorizontalPanel.ALIGN_MIDDLE );




        vPanel2 = new DockPanel();


        vPanel2.add(vPanel, DockPanel.CENTER);







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
        this.addSouth(lResult,60);
        this.add(vPanel2);

         // Create an asynchronous callback to handle the result.
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
               if (result.contains("was"))
                    lResult.setStyleName("greenBack");
                  else
                        lResult.setStyleName("redBack");
                lResult.setText(result);


            }

            public void onFailure(Throwable caught) {
                lResult.setText("Communication failed");
                    lResult.setStyleName("redResutl") ;

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
                    Date dateBirth = tBirthDay.getValue();
                    clearErrors();
                    lResult.setStyleName("panel");
                    lResult.setText("please wait while the server checking your details.");
                //getService().myMethod("test", callback);
                getService().register(firstname, lastName, email, nickname, username, pass, dateBirth, callback);
                }
            }
        });

         // Listen for the button clicks
        bCancel.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
       //         remove(vPanel);
//                vPanel2.setVisible(false);
//                 vPanel.setVisible(false);
//                 lRegisteration.setVisible(false);
//                 thisSuper.setVisible(false);
                 RootLayoutPanel.get().remove(1);
                 RootLayoutPanel.get().getWidget(0).setVisible(true);
          //      RootLayoutPanel.get().add(new MessageViewer());
            }
        });

        bClear.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                clearFileds();
         //      remove(vPanel);
        //       add(vPanel);
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

     private void clearFileds() {
         tEmail.setText("") ;
         tFirstName.setText("");
         tLastName.setText("");
         tLastName.setText("");
         tFirstName.setText("");
         tLastName.setText("");
         tNickName.setText("");
         tPassword.setText("");
         tRePassword.setText("");
         tUsername.setText("");
         tBirthDay.setValue(null);
     }

          private void clearErrors() {
        lFirstNameError.setText("");
        lLastNameError.setText("");
         lemailError.setText("") ;
         lnicknameError.setText("");
         lpasswordError.setText("");
         lrePasswordError.setText("");
         lusernameError.setText("");
         lBirthDayError.setText("");
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
        if  (p1.length() != 8) {
            lpasswordError.setText( "password should be 8 chars long.");
            ans= false;
        }

        return ans;
    }
}

