/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.LoginEvent;
import org.tapuachForum.client.Events.RefreshEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.SearchPanel.searchPanel;
import org.tapuachForum.client.manager.LoginManager;

/**
 *
 * @author amit
 */
public class mainWindow extends Composite {

    private LayoutPanel _mainPanel;

    public mainWindow() {
        _mainPanel = new LayoutPanel();
        initWidget(_mainPanel);
        final LoginManager loginManager = new LoginManager();
        //status panel
        final StatusPanel status = new StatusPanel();
        _mainPanel.add(status);
        _mainPanel.setWidgetTopHeight(status, 85, Unit.PX, 20, Unit.PX);
        _mainPanel.setWidgetLeftRight(status, 10, Unit.PX, 100, Unit.PX);

        //LOGIN Panel ( number 0)
        final LoginPanel l = new LoginPanel();
        _mainPanel.add(l);
        _mainPanel.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
        _mainPanel.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);
        l.addListener(new ApplicationEventListener() {

            public void handle(ApplicationEvent event) {
                if (event instanceof LoginEvent) {
                    _mainPanel.remove(l);
                    LoginEvent tEvent = (LoginEvent) event;
                    loginManager.setAuthentication(tEvent.getUser());
                    

                }

            }
        });
        //   SERACH PANEL      (number 1)
        searchPanel sp = new searchPanel();
        _mainPanel.add(sp);
        _mainPanel.setWidgetTopHeight(sp, 533, Unit.PX, 100, Unit.PX);
        _mainPanel.setWidgetLeftRight(sp, 10, Unit.PX, 500, Unit.PX);

        //ONline Panel (number 2)
        final OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
        _mainPanel.add(op);
        _mainPanel.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
        _mainPanel.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);
        op.addListener(new ApplicationEventListener() {

            public void handle(ApplicationEvent event) {
                status.SetStatus(event.getDescription());
            }
        });

        //  MESSAGES panerl  (number 3)
        MessageViewer m = new MessageViewer();
        m.setSize("980 px", "320 px");
        //       Scrol_mainPanelanel s = new Scrol_mainPanelanel(m);
        m.setHeight("320px");
        _mainPanel.add(m);
        _mainPanel.setWidgetTopHeight(m, 104, Unit.PX, 430, Unit.PX);
        m.setStyleName("messageviewer");
        m.addListener(new ApplicationEventListener() {

            public void handle(ApplicationEvent event) {
                if (event instanceof RefreshEvent) {
                    op.refreshUsers();
                } else if (event instanceof ChangeStatusEvent) {
                    status.SetStatus(event.getDescription());
                }
            }
        });

 //       RootLayoutPanel.get().add(_mainPanel);


        Window.addWindowClosingHandler(new Window.ClosingHandler() {

            @Override
            public void onWindowClosing(final ClosingEvent event) {
                if (ClientUser.getClient().isLogin()) {
                    event.setMessage(" " + ClientUser.getClient().getNickName() + ", you are still login. Please go back and logout.");
                } else {
                    //              event.setMessage(" bye bye ");
                }
            }
        });
    }

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }
}
