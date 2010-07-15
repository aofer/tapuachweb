/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.MessageViewer.MessageViewer;

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
        DockLayoutPanel d = new DockLayoutPanel(Unit.EM);
        //Grid _g = new Grid(1,2);
        //_g.setBorderWidth(1);
        //_g.setWidget(0,1));
        d.addNorth(new LoginPanel(),5);
        d.add(new ScrollPanel(new MessageViewer()));
        
        //_g.getCellFormatter().setWidth(0, 0, "400px");
        //_g.getCellFormatter().setWidth(0, 1, "400px");
        //_g.getCellFormatter().setHeight(0, 0, "150px");
         //_g.getCellFormatter().setVerticalAlignment(0,1,HasVerticalAlignment.ALIGN_TOP );
     
     
//      _g.getCellFormatter().setVerticalAlignment(0,1,HasVerticalAlignment.ALIGN_TOP );

    //    _g.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

       // _g.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
        RootLayoutPanel.get().add(d);
        //RootLayoutPanel.get().add(d);
        //d.addNorth(new HTML("content"), 2);
        //d.addNorth(new LoginPanel(), 2);
   //     d.add(new MessageViewer());
        //d.addSouth(new MessageViewer(),50);
    }
}
