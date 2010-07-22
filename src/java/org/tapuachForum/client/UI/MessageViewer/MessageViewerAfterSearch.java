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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;
import org.tapuachForum.client.UI.OnlinePanel.OnlinePanel;
import org.tapuachForum.client.UI.SearchPanel.searchPanel;
import org.tapuachForum.shared.MessageInterface;
import org.tapuachForum.shared.eMemberType;

/**
 *
 * @author amit ofer
 */
public class MessageViewerAfterSearch extends Composite {
    //fields

    private VerticalPanel _mainPanel;
    private HorizontalPanel _toolbar;
    private Button _addMessageButton;
    private Button _refreshButton;
    private Button _registrationButton;
    private Button _refreshUsersButton;
    private MessageTree _MessageTree;
    private HorizontalPanel _treePanel;
    private PopupPanel _addmsg;
    private Label _info;
    private final int numToSearsh;

    public MessageViewerAfterSearch(int numToSearchInit) {
        numToSearsh = numToSearchInit;
        // private Label _info;
        _mainPanel = new VerticalPanel();
        _toolbar = new HorizontalPanel();
        _treePanel = new HorizontalPanel();
        _addMessageButton = new Button("Add message");
        if (ClientUser.getClient() == null)
            ClientUser.setClient();
       if (ClientUser.getClient().getType() == eMemberType.guest)
           _addMessageButton.setEnabled(false);
        _refreshButton = new Button("Refresh");
        _refreshUsersButton = new Button("refresh Users");
        _registrationButton = new Button("Registration");
        _MessageTree = new MessageTree(numToSearsh);
        _info = new Label ("");
        _mainPanel.add(_info);
        _mainPanel.add(_toolbar);
        _toolbar.setVisible(false);
        _mainPanel.add(_treePanel);

        //_mainPanel.setSpacing(0);

        //_addmsg = new PopupPanel(false);
        //_addmsg.add(new addMessageWindow());
        _treePanel.add(_MessageTree);
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        _toolbar.add(_refreshUsersButton);
        //_toolbar.add(_registrationButton);
        //      _toolbar.setSize("800px", "20px");
        //_toolbar.setBorderWidth(1);
        //_treePanel.setBorderWidth(1);
        initWidget(_mainPanel);
        _info.setText("Please wait while downloading mesagses from the server....");
        _info.setStyleName("infoText");
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>() {

            public void onSuccess(Vector<MessageInterface> result) {
                getMessageTree().refreshTreeForSearch(result);
                 _info.setText("");
                 _toolbar.setVisible(true);

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
                //RootLayoutPanel.get().getWidget(0).setVisible(false);
                //RootLayoutPanel.get().add(new addMessageWindow());
                //_addmsg.setGlassEnabled(true);
           addMessageWindow aw = new addMessageWindow();

            }
        });

           _refreshUsersButton.addClickHandler(new ClickHandler() {

    public void onClick(ClickEvent event) {
               LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
              ScrollPanel s = (ScrollPanel)  lp.getWidget(3);

               searchPanel sp =(searchPanel)  lp.getWidget(2);
              lp.remove(3);
              lp.remove (2);
              lp.remove(1);
        s.setHeight("430px");
        OnlinePanel op =new OnlinePanel("Admin,Arseny,bobspong");
        lp.add(op);
        lp.add(sp);
       lp.setWidgetTopHeight(sp,530, Unit.PX, 80, Unit.PX);
        lp.setWidgetLeftRight(sp,10, Unit.PX,500, Unit.PX);
        lp.add(s);

         lp.setWidgetTopHeight(op,533, Unit.PX, 100, Unit.PX);
        lp.setWidgetLeftRight(op, 550, Unit.PX, 40, Unit.PX);
        lp.setWidgetTopHeight(s, 100, Unit.PX, 430, Unit.PX);
            }
        });
        _refreshButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
//                //    _mainPanel.setVisible(false);
//              RootLayoutPanel.get().remove(0);
//                LayoutPanel  lp = new LayoutPanel();
//        LoginPanel l = new LoginPanel();
//        l.setStyleName("loginpanel");
//        l.setWidth("230px");
//        l.setHeight("120px");
//
//
//        lp.add(l);
//        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
//        lp.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);
//        MessageViewer m = new MessageViewer();
//        m.setSize("1024 px", "400 px");
//        ScrollPanel s = new ScrollPanel(m);
//        s.setHeight("430px");
//        m.setStyleName("messageviewer");
//
//
//        lp.add(s);
//        //lp.setWidgetLeftRight(s, 0, Unit.PX, , Unit.PX);
//        lp.setWidgetTopHeight(s, 130, Unit.PX, 550, Unit.PX);
//        //ScrollPanel ms = new ScrollPanel(lp);
//        RootLayoutPanel.get().add(lp);
//                  l.getReg().addClickHandler(new ClickHandler() {
//
//            public void onClick(ClickEvent event) {
//                //RootLayoutPanel.get().getWidget(0).setVisible(false);
//                // _mainPanel.setVisible(false);
//                RootLayoutPanel.get().add(new RegistrationPanel());
//            }
//        });
                                //    _mainPanel.setVisible(false);
        //      RootLayoutPanel.get().getWidget(0);
                LayoutPanel  lp =  (LayoutPanel) RootLayoutPanel.get().getWidget(0);
                lp.remove(3);
//        LoginPanel l = new LoginPanel();
//        l.setStyleName("loginpanel");
//        l.setWidth("230px");
//        l.setHeight("120px");
//
//
//        lp.add(l);
//        lp.setWidgetLeftRight(l, 700, Unit.PX, 20, Unit.PX);
//        lp.setWidgetTopHeight(l, 5, Unit.PX, 125, Unit.PX);
        MessageViewer m = new MessageViewer();
        m.setSize("1024 px", "300 px");
        ScrollPanel s = new ScrollPanel(m);
        s.setHeight("430px");
        m.setStyleName("messageviewer");


        lp.add(s);
        //lp.setWidgetLeftRight(s, 0, Unit.PX, , Unit.PX);
        lp.setWidgetTopHeight(s, 130, Unit.PX, 450, Unit.PX);
        //ScrollPanel ms = new ScrollPanel(lp);
     //   RootLayoutPanel.get().add(lp);

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

