/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import org.tapuachForum.shared.MessageData;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.Pane;
import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author amit
 */
public class MessageTree extends Pane {

    private HorizontalPanel _mainPanel;
    private Tree _messageTree;
    private final int PAGESIZE = 3;
    private Vector<MessageInterface> _messages;
    private int _currentPage;

    public MessageTree() {
        super();
        this._messages = new Vector<MessageInterface>();
        this._currentPage = 1;
        this._mainPanel = new HorizontalPanel();
        this._mainPanel.setSize("980x", "320px");
        this._messageTree = new Tree();
        this._mainPanel.add(this._messageTree);
        initWidget(this._mainPanel);
        refreshTree();
        //       testInit();
    }

    public void testInit() {
        MessageData msg = new MessageData("aofer", "testing the forum", "blah blah", new Date(), new Date());
        MessageInterface m1 = new Message(msg);
        MessageTreeItem item1 = new MessageTreeItem(m1);
        this._messageTree.addItem(item1);
        MessageData msg2 = new MessageData("aofer", "testing the forum2222", "Mahabaz baz baz", new Date(), new Date());
        MessageData msg3 = new MessageData("aofer", "testing the forum2233333", "Nahabaz baz baz\n i would like tsdkhgksdhgkjshdjksgdhgdsjkdsjghsdhgsdhgsdhgsdhgsdhdgshdgsjsdgkdsghjdskhskdsgsdgkjsgdjdsgjkhgdskjsgdhsdjkdsjkghsdhgskhgdskjgdsjkksj84578973495789375983475893jksfdkjsdfkjnfsdnkjsdnkjfsdnjksfdnjksnfdskndkjnfdsjkfdnsjksdnfsjdkjfndsknfdsjkfdsnjkdsfnjkfndjksdfnjksdnfdsjkfnjksndkjnfsdkdnfjk####%$%#$^$##$#%#$%%%%%%%%%%%%$$#%$$#$#$#$#$$#$##$$%#$#$%DFFDFDFDFDFdffdgfdgfdgdfdfdf\n\n\ngdsgdsdgj3895o say that \n bufhukdg jkshks dsfjsdkfd dsfkjhsd\n dsgjk\n klshgkj!", new Date(), new Date());
        MessageInterface m2 = new Message(msg2);
        MessageInterface m3 = new Message(msg3);
        MessageTreeItem item2 = new MessageTreeItem(m2);
        MessageTreeItem item3 = new MessageTreeItem(m3);
        item1.addItem(item2);
        item2.addItem(item3);
    }

    public void nextPage() {

        if (this.getCurrentPage() < getMaxPage()) {
            this._currentPage++;
            viewMessages();
        } else {
            //TODO
        }
    }

    public void previousPage() {
        if (this.getCurrentPage() > 0) {
            this._currentPage--;
            viewMessages();
        } else {
            //TODO
        }
    }

    public void gotoMessage(int index) {
        //TODO
    }

    public void viewMessages() {
        //first clean the tree
        this._messageTree.clear();
        //add the messages from the vector of messages
        int firstMessageIndex = (this.getCurrentPage() - 1) * PAGESIZE + 1;
        int lastMessageIndex = Math.min(this.getCurrentPage() * PAGESIZE, this._messages.size() - 1);
        for (int i = firstMessageIndex; i <= lastMessageIndex; i++) {
            MessageInterface m = _messages.get(i);
            MessageTreeItem tItem = new MessageTreeItem(m);
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = m.getReplies();
            addReplies(tReplies, tItem);
        }
    }

    public void viewMessages2() {
        this._messageTree.clear();
        //add the messages from the vector of messages
        for (MessageInterface m : _messages) {
            MessageTreeItem tItem = new MessageTreeItem(m);
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = m.getReplies();
            addReplies(tReplies, tItem);
        }
    }

    /**
     * used for adding replies for each message in the tree
     * used by viewMessages
     * @param replies
     * @param parent
     */
    private void addReplies(ArrayList<Message> replies, MessageTreeItem parent) {
        for (MessageInterface m : replies) {
            MessageTreeItem tItem = new MessageTreeItem(m);
            parent.addItem(tItem);
            addReplies(m.getReplies(), tItem);
        }
    }

    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }

    public void getMessagesFromServer() {
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>() {

            public void onFailure(Throwable caught) {
                // set status
                MessageTree.this.fireEvent(new ChangeStatusEvent(MessageTree.this, "Error loading messages from server"));
            }

            public void onSuccess(Vector<MessageInterface> result) {
                MessageTree.this._messages = result;
                viewMessages();
            }
        });
    }

    public void refreshTree() {
        getMessagesFromServer();
        //remove next line later
        this._currentPage = 1;
        //TODO check the current page if its still valid

    }

    /**
     * @return the _currentPage
     */
    public int getCurrentPage() {
        return _currentPage;
    }

    public int getMaxPage() {
        int maxPage;
        if (this._messages.size() % PAGESIZE == 0) {
            maxPage = this._messages.size() / PAGESIZE;
        }
        else {
            maxPage = this._messages.size() / PAGESIZE + 1;
        }
        return maxPage;
    }
}
