/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 * @author Arseny
 */
public class AdminPanel extends PopupPanel{

     DockLayoutPanel dp = new DockLayoutPanel(Unit.PX);
    final HorizontalPanel _listPanel;
    final HorizontalPanel _bPanel;
    final Label _lbl;
    final ListBox _users;
    final Button _upgrade;
    final Button _close;

    public AdminPanel(String users){
        super(true);//,true);
        _listPanel = new HorizontalPanel();
        _bPanel = new HorizontalPanel();
        _lbl = new Label("Users:");
        _users = new ListBox();
        _upgrade = new Button("Upgrade");
        _close = new Button("close");
        _listPanel.add(_lbl);
        _listPanel.add(_users);
        _bPanel.add(_upgrade);
        _bPanel.add(_close);
        dp .add(_listPanel);
        dp .add(_bPanel);
       this.setGlassEnabled(true);
       this.add(dp);

        _close.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                AdminPanel.super.hide();
            }
        });
        this.center();
    }

}
