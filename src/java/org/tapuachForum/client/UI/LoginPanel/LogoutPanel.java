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
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author Arseny
 */
public class LogoutPanel extends Pane {

    private VerticalPanel _mainPanel;
    private HorizontalPanel _infop;
    private HorizontalPanel _buttp;
    private Label _info;
    private Button _logout;
    private Button _administer;
    private boolean userIsOnline;
    private String userName;

    public LogoutPanel() {
        userIsOnline = false;
        _mainPanel = new VerticalPanel();
        _info = new Label("");
        _logout = new Button("Logout");
        _administer = new Button("Administer");
        _infop = new HorizontalPanel();
        _buttp = new HorizontalPanel();
        _infop.add(_info);
        _buttp.add(_logout);
        _buttp.add(_administer);
        _administer.setVisible(false);
        _mainPanel.add(_infop);
        _mainPanel.add(_buttp);
        initWidget(_mainPanel);

        _logout.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
            _logout.setEnabled(false);
            _administer.setEnabled(false);
             getService().logout(userName,logoutCallback);
            }
        });
      
    }


        final AsyncCallback<MemberInterface> logoutCallback = new AsyncCallback<MemberInterface>() {

            public void onSuccess(MemberInterface result) {
                


                }


            public void onFailure(Throwable caught) {
                if (userIsOnline) {


                    _logout.setEnabled(true);
                    _info.setText(" Problem with logout. Please try again.");
                }


            }
        };

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }

}
