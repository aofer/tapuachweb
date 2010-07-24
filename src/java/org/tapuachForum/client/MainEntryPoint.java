/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.UI.mainWindow;


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
        return Locator.getInstance();
    }

    public void onModuleLoad() {
        RootLayoutPanel.get().add(new mainWindow());
    }


}
