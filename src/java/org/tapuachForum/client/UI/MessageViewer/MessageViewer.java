/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.sql.Ref;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.LoginPanel.LoginPanel;
import org.tapuachForum.client.UI.RegistrationPanel;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author amit ofer
 */
public class MessageViewer extends Composite {
    //fields

    private VerticalPanel _mainPanel;
    private HorizontalPanel _toolbar;
    private Button _addMessageButton;
    private Button _refreshButton;
    private Button _registrationButton;
    private MessageTree _MessageTree;
    private HorizontalPanel _treePanel;

    public MessageViewer() {
        _mainPanel = new VerticalPanel();
        _toolbar = new HorizontalPanel();
        _treePanel = new HorizontalPanel();
        _addMessageButton = new Button("Add message");
        _refreshButton = new Button("refresh");
        _registrationButton = new Button("Registration");
        _MessageTree = new MessageTree();
        _mainPanel.add(_treePanel);
        _mainPanel.add(_toolbar);
        _mainPanel.setSpacing(0);
        _treePanel.add(_MessageTree);
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        //_toolbar.add(_registrationButton);
        //      _toolbar.setSize("800px", "20px");
        _toolbar.setBorderWidth(1);
        _treePanel.setBorderWidth(1);
        initWidget(_mainPanel);
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>() {

            public void onSuccess(Vector<MessageInterface> result) {
                getMessageTree().refreshTree(result);
            }

            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

       /* _registrationButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                RootLayoutPanel.get().getWidget(0).setVisible(false);
                // _mainPanel.setVisible(false);
                RootPanelRootLayoutPanel.get().add(new RegistrationPanel());
            }
        });*/

        _addMessageButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //    _mainPanel.setVisible(false);
                RootLayoutPanel.get().getWidget(0).setVisible(false);
                RootLayoutPanel.get().add(new addMessageWindow());
            }
        });
        _refreshButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                //    _mainPanel.setVisible(false);
                RootLayoutPanel.get().remove(0);
         DockLayoutPanel d = new DockLayoutPanel(Unit.EM);
                LoginPanel l = new LoginPanel();
        d.addNorth(l,5);
        d.add(new ScrollPanel(new MessageViewer()));
        RootLayoutPanel.get().add(d);
             //   RootLayoutPanel.get().add(new MessageViewer());
            }
        });


    }

    public static MyServiceAsync getService() {

        return GWT.create(MyService.class);
    }

    /**
     * @return the _MessageTree
     */
    public MessageTree getMessageTree() {
        return _MessageTree;
    }
}


