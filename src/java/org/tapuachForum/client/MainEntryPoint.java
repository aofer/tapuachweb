/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.MessageViewer.AfterSearch;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.SearchPanel.searchPanel;
import org.tapuachForum.client.UI.StatusPanel;
import org.tapuachForum.shared.MemberInterface;

/**
 * Main entry point.
 *
 * @author Liron Katav
 */
public class MainEntryPoint implements EntryPoint {

    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }

    /**
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        LayoutPanel lp = new LayoutPanel();
        //status panel
        StatusPanel status = new StatusPanel();
        lp.add(status);
        lp.setWidgetTopHeight(status, 85, Unit.PX, 20, Unit.PX);
        lp.setWidgetLeftRight(status, 10, Unit.PX, 100, Unit.PX);

        //LOGIN Panel ( number 0)
        LoginPanel l = new LoginPanel();
        lp.add(l);
        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
        lp.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);


        //   SERACH PANEL      (number 1)
        searchPanel sp = new searchPanel();
        lp.add(sp);
        lp.setWidgetTopHeight(sp, 533, Unit.PX, 100, Unit.PX);
        lp.setWidgetLeftRight(sp, 10, Unit.PX, 500, Unit.PX);

        //ONline Panel (number 2)
        final OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
        lp.add(op);
        lp.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
        lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);

        //  MESSAGES panerl  (number 3)
        MessageViewer m = new MessageViewer();
        m.setSize("980 px", "320 px");
        //       ScrollPanel s = new ScrollPanel(m);
        m.setHeight("320px");
        lp.add(m);
        lp.setWidgetTopHeight(m, 104, Unit.PX, 430, Unit.PX);
        m.setStyleName("messageviewer");
        m.addListener(new ApplicationEventListener() {

            public void handle(ApplicationEvent event) {
                op.refreshUsers();
            }
        });

        RootLayoutPanel.get().add(lp);


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

        //        Window.addWindowClosingHandler(new Window.ClosingHandler() {
//
//            @Override
//            public void onWindowClosing(final ClosingEvent event) {
//                if (ClientUser.getClient().isLogin()) {
//                    event.setMessage(" please wait whilte logging out " + ClientUser.getClient().getNickName()+" ...");
//                    getService().logout(ClientUser.getClient().getUserName(), new AsyncCallback<MemberInterface>() {
//
//                        public void onFailure(Throwable caught) {
//                            event.setMessage("user logout was fail. " + caught.getMessage());
//                        }
//
//                        public void onSuccess(MemberInterface result) {
//                            event.setMessage("user logout was succes. You can Exit now.");
//                        }
//                    });
//                } else {
//                    event.setMessage(" bye bye ");
//                }
//            }
//        });




    }
}
