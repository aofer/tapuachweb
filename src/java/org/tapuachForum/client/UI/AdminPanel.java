/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.List;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.eMemberType;

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

        getService().getMembers(new AsyncCallback<List<Member>>() {

            public void onSuccess(List<Member> result) {
                for (Member justMem : result) {
                    if (justMem.getType() == eMemberType.member) {
                        _users.addItem(justMem.getUserName());
                    }
                }
            }

            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        _users.setItemSelected(0, true);


        _close.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                AdminPanel.super.hide();
            }
        });
        this.center();

        _upgrade.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                _close.setEnabled(false);
                _upgrade.setEnabled(false);
                int indexChose = _users.getSelectedIndex();
                String chose = _users.getItemText(indexChose);
                getService().upgradeUser(chose, new AsyncCallback<String>() {

                    public void onSuccess(String result) {
                        _close.setEnabled(true);
                        _upgrade.setEnabled(true);
                    }

                    public void onFailure(Throwable caught) {
                         _close.setEnabled(true);
                        _upgrade.setEnabled(true);
                        _mainPanel.add(new Label("problem: " + caught.getMessage()));

                    }
                });
            }
        });
        this.center();
    }

    public static MyServiceAsync getService() {

        return Locator.getInstance();
    }
}
