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
      //RootLayoutPanel.get().add(new RegistrationPanel());
        LayoutPanel  lp = new LayoutPanel();
        //Grid _g = new Grid(1,2);
        //_g.setBorderWidth(1);
        //_g.setWidget(0,1));
        LoginPanel l = new LoginPanel();
        l.setStyleName("loginpanel");
        l.setWidth("230px");
        l.setHeight("80px");


        lp.add(l);
        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
        lp.setWidgetTopHeight(l, 5, Unit.PX, 80, Unit.PX);
        MessageViewer m = new MessageViewer();
        m.setSize("1024 px", "400 px");
        ScrollPanel s = new ScrollPanel(m);
        m.setStyleName("messageviewer");
        //m.setWidth("800px");
        //m.setHeight("450px");

        
        lp.add(s);
        //lp.setWidgetLeftRight(s, 0, Unit.PX, , Unit.PX);
        lp.setWidgetTopHeight(s, 100, Unit.PX, 550, Unit.PX);
        RootLayoutPanel.get().add(lp);
        l.getReg().addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                RootPanel.get().getWidget(0).setVisible(false);
                // _mainPanel.setVisible(false);
                RootPanel.get().add(new RegistrationPanel());
            }
        });


        //_g.getCellFormatter().setWidth(0, 0, "400px");
        //_g.getCellFormatter().setWidth(0, 1, "400px");
        //_g.getCellFormatter().setHeight(0, 0, "150px");
         //_g.getCellFormatter().setVerticalAlignment(0,1,HasVerticalAlignment.ALIGN_TOP );
     
     
//      _g.getCellFormatter().setVerticalAlignment(0,1,HasVerticalAlignment.ALIGN_TOP );

    //    _g.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

       // _g.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
       
        //RootLayoutPanel.get().add(d);
        //d.addNorth(new HTML("content"), 2);
        //d.addNorth(new LoginPanel(), 2);
   //     d.add(new MessageViewer());
        //d.addSouth(new MessageViewer(),50);
    }
}
