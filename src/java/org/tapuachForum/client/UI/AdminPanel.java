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
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.MemberInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author Arseny
 */
public class AdminPanel extends PopupPanel {

    private FormPanel _mainPanel;
    private VerticalPanel _holder;
    private HorizontalPanel _buttonsPanel;
    private ListBox _users;
    private Button _upgrade;
    private Button _close;
    private Label _header;
    private Label _statusLabel;

    public AdminPanel() {
        super(false, true);
        _mainPanel = new FormPanel();
        _upgrade = new Button("Upgrade");
        _close = new Button("Close");
        _header = new Label("Upgrade Users:");
        _buttonsPanel = new HorizontalPanel();
        _holder = new VerticalPanel();
        _users = new ListBox();
        _statusLabel = new Label("Loading...");
        getRegularMembers();

        _mainPanel.setAction("/myFormHandler");
        _mainPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        _mainPanel.setMethod(FormPanel.METHOD_POST);
        this.setWidget(_mainPanel);
        _holder.setStyleName("blueBack");
        _mainPanel.setWidget(_holder);
        _users.setSize("100px", "150px");
        _users.setVisibleItemCount(10);

        _holder.add(_header);
        _holder.add(_users);
        _holder.add(_statusLabel);
        _buttonsPanel.add(_upgrade);
        _buttonsPanel.add(_close);
        _holder.add(_buttonsPanel);
        this.setGlassEnabled(true);

        //   _users.setItemSelected(0, true);


        _close.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                AdminPanel.super.hide();
            }
        });
        _upgrade.addClickHandler(new UpgradeClickHandler());
        this.center();
    }

    public static MyServiceAsync getService() {

        return Locator.getInstance();
    }

    protected class UpgradeClickHandler implements ClickHandler {

        public void onClick(ClickEvent event) {
            _close.setEnabled(false);
            _upgrade.setEnabled(false);
            int indexChose = _users.getSelectedIndex();
            String chose = _users.getItemText(indexChose);
            getService().upgradeUser(chose, new AsyncCallback<String>() {

                public void onSuccess(String result) {
                    _close.setEnabled(true);
                    _upgrade.setEnabled(true);
                    _users.removeItem(_users.getSelectedIndex());
                    fixButtons();
                }

                public void onFailure(Throwable caught) {
                    _close.setEnabled(true);
                    _upgrade.setEnabled(true);
                    _mainPanel.add(new Label("problem: " + caught.getMessage()));
                    fixButtons();

                }
            });
        }
    }

    public void getRegularMembers() {
        getService().getMembers(new AsyncCallback<List<Member>>() {

            public void onFailure(Throwable caught) {
                _statusLabel.setText("Error loading users.");
            }

            public void onSuccess(List<Member> result) {
                for (Member m : result) {
                    if (m.getType() == eMemberType.member) {
                        _users.addItem(m.getUserName());
                        _statusLabel.setText("Done.");
                    }
                }
                fixButtons();
            }
        });
    }

    public void fixButtons() {
        if (_users.getItemCount() == 0) {
            _upgrade.setEnabled(false);
        } else {
            _upgrade.setEnabled(true);
        }
    }
}

