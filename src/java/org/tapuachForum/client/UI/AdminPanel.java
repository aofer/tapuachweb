/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author Arseny
 */
public class AdminPanel extends PopupPanel {

    final FormPanel _mainPanel = new FormPanel();

    public AdminPanel(String users) {
        super(false, true);
        final VerticalPanel _holder;
        final HorizontalPanel _buttonsPanel;
        final ListBox _users;
        final Button _upgrade;
        final Button _close;
        final Label _header;
        _mainPanel.setAction("/myFormHandler");
        _mainPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        _mainPanel.setMethod(FormPanel.METHOD_POST);
        this.setWidget(_mainPanel);
        _holder = new VerticalPanel();
        _holder.setStyleName("blueBack");
        _mainPanel.setWidget(_holder);
     _buttonsPanel = new HorizontalPanel();

        
        _users = new ListBox();
        _users.setSize("100px", "150px");
        _users.addItem("Arseny");//test
        _users.addItem("Bob");//test
        _users.setVisibleItemCount(10);
        _upgrade = new Button("Upgrade");
        _close = new Button("Close");
        _header = new Label("Upgrade Users:");
        _holder.add(_header);
        _holder.add(_users);
        
        _buttonsPanel.add(_upgrade);
        _buttonsPanel.add(_close);
        _holder.add(_buttonsPanel);
        this.setGlassEnabled(true);

        _close.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                AdminPanel.super.hide();
            }
        });
        this.center();
    }
}
