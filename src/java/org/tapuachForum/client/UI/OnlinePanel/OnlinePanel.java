/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.OnlinePanel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Arseny
 */
public class OnlinePanel extends Composite {
    
    private VerticalPanel _mainPanel;
    private Label _l;

    public OnlinePanel(String users) {
        _mainPanel = new VerticalPanel();
        _l = new Label("Online users: \n" + users);
        _l.setSize("430px", "50px");
        _mainPanel.setBorderWidth(1);
        _mainPanel.add(_l);
        RootPanel.get().add(_mainPanel);
        initWidget(_mainPanel);
    }


}
