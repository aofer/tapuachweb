/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;
import org.tapuachForum.client.UI.RegistrationPanel;

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
        LayoutPanel  lp = new LayoutPanel();
        LoginPanel l = new LoginPanel();
        l.setStyleName("loginpanel");
        l.setWidth("230px");
        l.setHeight("120px");


        lp.add(l);
        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
        lp.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);
        MessageViewer m = new MessageViewer();
        m.setSize("1024 px", "400 px");
        ScrollPanel s = new ScrollPanel(m);
        s.setHeight("430px");
        m.setStyleName("messageviewer");
        
        
        lp.add(s);
        //lp.setWidgetLeftRight(s, 0, Unit.PX, , Unit.PX);
        lp.setWidgetTopHeight(s, 130, Unit.PX, 550, Unit.PX);
        //ScrollPanel ms = new ScrollPanel(lp);
        RootLayoutPanel.get().add(lp);
        l.getReg().addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //RootLayoutPanel.get().getWidget(0).setVisible(false);
                // _mainPanel.setVisible(false);
                RootLayoutPanel.get().add(new RegistrationPanel());
            }
        });


        
    }
}
