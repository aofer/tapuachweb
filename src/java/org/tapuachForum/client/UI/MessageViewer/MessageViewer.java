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
public class MessageViewer extends Composite {
    //fields

    final private int pageSize = 3;
    private VerticalPanel _mainPanel;
    private HorizontalPanel _toolbar;
        private HorizontalPanel _treebar;
    private Button _addMessageButton;
    private Button _refreshButton;
    private Button _NextButton;
    private Button _PrevButton;
    private Label _indexInfo;
    private MessageTree _MessageTree;
    private Label _info;
    private ScrollPanel scrollPan;
    public int indexOfPages;
    public int numberOfPages;
    private int restOfPages;
    private Vector<MessageInterface> msgVector;

    public MessageViewer() {

        // private Label _info;

        msgVector = null;
        _mainPanel = new VerticalPanel();
        _toolbar = new HorizontalPanel();
        _treebar   = new HorizontalPanel();
        _addMessageButton = new Button("Add message");
        if (ClientUser.getClient() == null) {
            ClientUser.setClient();
        }
        if (ClientUser.getClient().getType() == eMemberType.guest) {
            _addMessageButton.setEnabled(false);
        }
        _refreshButton = new Button("Refresh");
        //       _refreshUsersButton = new Button("refresh Users");
//        _registrationButton = new Button("Registration");
        _NextButton = new Button("Next");
        _PrevButton = new Button("Prev");
        _refreshButton.setEnabled(false);
         _NextButton.setEnabled(false);
          _PrevButton .setEnabled(false);
          _indexInfo = new Label ("");
        _MessageTree = new MessageTree();
        _info = new Label("");
        _mainPanel.add(_toolbar);
        _mainPanel.add(_info);
    //    _toolbar.setVisible(false);
        scrollPan = new ScrollPanel(_MessageTree);
    //    _treebar.add(scrollPan);
        scrollPan.setSize("980 px", "270 px");
        _mainPanel.add(scrollPan);


        //_mainPanel.setSpacing(0);

        //_addmsg = new PopupPanel(false);
        //_addmsg.add(new addMessageWindow());
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        _toolbar.add(_PrevButton);
        _toolbar.add(_NextButton);
        _toolbar.add(_indexInfo);

        //_toolbar.add(_registrationButton);
        //      _toolbar.setSize("800px", "20px");
        //_toolbar.setBorderWidth(1);
        //_treePanel.setBorderWidth(1);
        initWidget(_mainPanel);
        _info.setText("Please wait while downloading mesagses from the server....");
        _info.setStyleName("infoText");
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>() {

            public void onSuccess(Vector<MessageInterface> result) {
                msgVector = result;
                indexOfPages = 0;
                numberOfPages = (result.size() / pageSize);
                restOfPages = result.size() % pageSize;
                _refreshButton.setEnabled(false);
                _PrevButton.setEnabled(false);
                if (indexOfPages < numberOfPages) {
                    _NextButton.setEnabled(true);
                } else {
                    _NextButton.setEnabled(false);
                }
                if (restOfPages == 0) {
                    numberOfPages--;
                    restOfPages= pageSize;
                }
                if (numberOfPages >= 1) {
                    getMessageTree().refreshTreeByIndex(result, 0, pageSize);
                } else {
                    getMessageTree().refreshTreeByIndex(result, 0, restOfPages);
                }
                _info.setText("");
                _toolbar.setVisible(true);
                //    _indexInfo.setText("Presseting page number "+(indexOfPages+1)+" of total "+(numberOfPages+1)+" pages.");
                     _indexInfo.setText(" "+(indexOfPages+1)+"/"+(numberOfPages+1));

            }

            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        _addMessageButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                addMessageWindow aw = new addMessageWindow();

            }
        });
        _refreshButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
                lp.remove(3);
                MessageViewer m = new MessageViewer();
                m.setSize("1024 px", "300 px");
                ScrollPanel s = new ScrollPanel(m);
                s.setHeight("430px");
                m.setStyleName("messageviewer");


                lp.add(s);
                lp.setWidgetTopHeight(s, 130, Unit.PX, 450, Unit.PX);

            }
        });

        _NextButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                MessageViewer.this.buttonNextPageClicked();
            }
        });

        _PrevButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                MessageViewer.this.buttonPrevPageClicked();
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

    private void buttonNextPageClicked() {
        indexOfPages++;
        if (indexOfPages == 1) {
            _PrevButton.setEnabled(true);
        }
        if (indexOfPages == numberOfPages) {
            _NextButton.setEnabled(false);
        }
        refreshTheMessages();
    }

    private void buttonPrevPageClicked() {
        indexOfPages--;
        if (indexOfPages == 0) {
            _PrevButton.setEnabled(false);
        }
        if (indexOfPages < numberOfPages) {
            _NextButton.setEnabled(true);
        }
        refreshTheMessages();
    }

    private void refreshTheMessages() {
           _indexInfo.setText("Pressetinog page number "+(indexOfPages+1)+" of total "+(numberOfPages+1)+" pages.");
     //   _mainPanel.remove(2);
     //  _MessageTree = new MessageTree();
    //   scrollPan = new ScrollPanel(_MessageTree);
    //   _mainPanel.add(scrollPan);
     //   _MessageTree = new MessageTree();
    //    getMessageTree().setVisible(false);
        if (indexOfPages < numberOfPages) {
            getMessageTree().refreshTreeByIndex(msgVector, indexOfPages * pageSize, (indexOfPages + 1) * pageSize);
        } else {
            getMessageTree().refreshTreeByIndex(msgVector, indexOfPages * pageSize, (indexOfPages * pageSize) + restOfPages);
        }
    //  getMessageTree().setVisible(true);
    //  _indexInfo.setText("Presseting page number "+(indexOfPages+1)+" of total "+(numberOfPages+1)+" pages.");
        _indexInfo.setText(" "+(indexOfPages+1)+"/"+(numberOfPages+1));
     }


}


