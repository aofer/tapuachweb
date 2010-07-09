/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
    private MessageTree _MessageTree;

    public MessageViewer(){
        _mainPanel = new VerticalPanel();
        _toolbar = new HorizontalPanel();
        _addMessageButton = new Button("Add message");
        _refreshButton = new Button("refresh");
        _MessageTree = new MessageTree();
        _mainPanel.add(_MessageTree);
        _mainPanel.add(_toolbar);
        _toolbar.add(_addMessageButton);
        _toolbar.add(_refreshButton);
        initWidget(_mainPanel);
    }
}


