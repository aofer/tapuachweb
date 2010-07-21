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
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.SearchPanel.searchPanel;
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
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(MyService.class);
    }

    /**
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        LayoutPanel lp = new LayoutPanel();

        //LOGIN Panel ( number 0)
        LoginPanel l = new LoginPanel();
        lp.add(l);
        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
        lp.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);

        //ONline Panel (number 1)
        OnlinePanel op = new OnlinePanel("Admin,Arseny,bobspong");
        lp.add(op);
        lp.setWidgetTopHeight(op, 533, Unit.PX, 100, Unit.PX);
        lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);

        //   SERACH PANEL      (number 2)
        searchPanel sp = new searchPanel();
        lp.add(sp);
        lp.setWidgetTopHeight(sp, 530, Unit.PX, 80, Unit.PX);
        lp.setWidgetLeftRight(sp, 10, Unit.PX, 500, Unit.PX);

        //  MESSAGES panerl  (number 3)
        MessageViewer m = new MessageViewer();
        m.setSize("1024 px", "300 px");
        ScrollPanel s = new ScrollPanel(m);
        s.setHeight("430px");
        lp.add(s);
        lp.setWidgetTopHeight(s, 100, Unit.PX, 430, Unit.PX);
        m.setStyleName("messageviewer");


        RootLayoutPanel.get().add(lp);


     Window.addWindowClosingHandler(new Window.ClosingHandler() {


            @Override
            public void onWindowClosing(final ClosingEvent event) {
                if (ClientUser.getClient().isLogin()) {
                    event.setMessage(" " + ClientUser.getClient().getNickName()+", you are still login. Please go back and logout.");
                               } else {
                    event.setMessage(" bye bye ");
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
