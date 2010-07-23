/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.OnlinePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author Arseny
 */
public class OnlinePanel extends Composite {

    private VerticalPanel _mainPanel;
    private Label _l;

    public OnlinePanel(String users) {
        _mainPanel = new VerticalPanel();
        _l = new Label("Online users: \n (please wait while updating...)");
        _l.setSize("430px", "50px");
        _mainPanel.setBorderWidth(1);
        _mainPanel.add(_l);
        RootPanel.get().add(_mainPanel);
        initWidget(_mainPanel);
        refreshUsers();
    }

    public static MyServiceAsync getService() {

        return GWT.create(MyService.class);
    }

    /**
     * used for refreshing the online users list
     */
    public void refreshUsers() {
        getService().onLineUsers(new AsyncCallback<Vector<MemberInterface>>() {
            public void onSuccess(Vector<MemberInterface> result) {
                String tUsers = "Online users:";
                for (MemberInterface m : result) {
                    tUsers  = tUsers + m.getNickName() + " , ";
                }
                if ( !result.isEmpty()){
                   tUsers = tUsers.substring(0, tUsers.length() - 3);
                }
                _l.setText(tUsers);
            }

            public void onFailure(Throwable caught) {
                _l.setText("Please refresh.");///problem : " + caught.getMessage());
            }
        });
    }
}
