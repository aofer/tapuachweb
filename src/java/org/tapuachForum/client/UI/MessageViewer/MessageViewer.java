/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.sql.Ref;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
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
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>(){

            public void onSuccess(Vector<MessageInterface> result) {
                getMessageTree().refreshTree(result);
            }

            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
    }

    public static MyServiceAsync getService() {
    // Create the client proxy. Note that although you are creating the
    // service interface proper, you cast the result to the asynchronous
    // version of the interface. The cast is always safe because the
    // generated proxy implements the asynchronous interface automatically.
    return GWT.create(MyService.class);
    }

    /**
     * @return the _MessageTree
     */
    public MessageTree getMessageTree() {
        return _MessageTree;
    }
}


