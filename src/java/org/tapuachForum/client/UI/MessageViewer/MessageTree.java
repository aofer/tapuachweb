/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TreeItem;
import java.util.ArrayList;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.shared.MessageData;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ChangeStatusEvent;
import org.tapuachForum.client.Events.DeleteEvent;
import org.tapuachForum.client.Events.RefreshEvent;
import org.tapuachForum.client.Events.resetButtonsEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
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
    /**
     * goes to first page
     */
    public void firstPage() {
        this._currentPage = 1;
        viewMessages();
    }
    /**
     * goes to last page
     */
    public void lastPage(){
        this._currentPage = this.getMaxPage();
        viewMessages();
    }
    /**
     * finds where the searched message is located in the message vector
     * @param searchIndex - the index returned from search
     */
    public int findIndex(int searchIndex) {
        int res = -1;
        for (int i = 0; i < _messages.size(); i++) {
            if (_messages.elementAt(i).getIndex() == searchIndex) {
                res = i;
                break;
            }
            int tResult = checkMessage(searchIndex, _messages.elementAt(i));
            if (tResult != - 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * check if the message is found down the post tree.
     * @param searchIndex
     * @return
     */
    private int checkMessage(int searchIndex, MessageInterface message) {
        int res = -1;
        for (int i = 0; i < message.getReplies().size(); i++) {
            if (message.getReplies().get(i).getIndex() == searchIndex) {
                res = i;
                break;
            }
            int tResult = checkMessage(searchIndex, message.getReplies().get(i));
            if (tResult != -1) {
                res = tResult;
                break;
            }
        }
        return res;
    }

    /**
     * opens the searched message in the tree
     * @param index -  the index of the search result
     */
    public void gotoMessage(int index) {
        //get the real index inside the vector
        int realIndex = findIndex(index);
        //calculate the destination page where the message is
        int destPage = realIndex / PAGESIZE + 1;
        //goto the desired page
        while (_currentPage != destPage) {
            if (_currentPage > destPage) {
                previousPage();
            } else {
                nextPage();
            }
        }
        //open the searched message
        OpenRepliesRec((MessageTreeItem) _messageTree.getItem(realIndex), index);
    }

    /**
     * opens the path where the searched message is located
     * @param item - the parent item where the searched message is
     * @param searchIndex - the index of the message we are searching for
     * @return - true if the current tree item is at the path
     */
    public boolean OpenRepliesRec(MessageTreeItem item, int searchIndex) {
        MessageInterface message = item.getMessage();
        if (message.getReplies().size() == 0) {
            boolean ans = (searchIndex == message.getIndex());
            if (ans == true) {
                item.setState(ans);
            }
            return ans;
        } else {
            boolean ans = false;
            for (int i = 0; i < item.getChildCount(); i++) {
                MessageTreeItem tItem = (MessageTreeItem) item.getChild(i);
                ans = ans || OpenRepliesRec(tItem, searchIndex);
            }
            if (ans == true) {
                item.setState(true);
            }
            return ans;
        }
    }

    /**
     * view the current page messages in the tree
     */
    public void viewMessages() {
        //first clean the tree
        this._messageTree.clear();
        //add the messages from the vector of messages
        int firstMessageIndex = (this.getCurrentPage() - 1) * PAGESIZE;
        int lastMessageIndex = Math.min(this.getCurrentPage() * PAGESIZE - 1, this._messages.size() - 1);
        for (int i = firstMessageIndex; i <= lastMessageIndex; i++) {
            MessageInterface m = _messages.get(i);
            MessageTreeItem tItem = new MessageTreeItem(m);
            tItem.addListener(new TreeItemListener());
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
            tItem.addListener(new TreeItemListener());
            parent.addItem(tItem);
            addReplies(m.getReplies(), tItem);
        }
    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    public void getMessagesFromServer() {
        getService().viewForum(new AsyncCallback<Vector<MessageInterface>>() {

            public void onFailure(Throwable caught) {
                // set status
                MessageTree.this.fireEvent(new ChangeStatusEvent(MessageTree.this, "Error loading messages from server"));
                MessageTree.this.fireEvent(new resetButtonsEvent(MessageTree.this));
            }

            public void onSuccess(Vector<MessageInterface> result) {
                //    MessageTree.this._messages = result;
                //inverts the message order in a horrible horrible way :)
                MessageTree.this._messages.clear();
                for (int i = result.size() - 1; i >= 0; i--) {
                    MessageTree.this._messages.add(result.get(i));
                }
                viewMessages();
                MessageTree.this.fireEvent(new resetButtonsEvent(MessageTree.this));
            }
        });
    }

    public void refreshTree() {
        getMessagesFromServer();
        //remove next line later
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
        } else {
            maxPage = this._messages.size() / PAGESIZE + 1;
        }
        return maxPage;
    }



    protected class TreeItemListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof ChangeStatusEvent) {
                MessageTree.this._listeners.fireEvent(event);
            } else if (event instanceof RefreshEvent) {
                MessageTree.this._listeners.fireEvent(event);
            } else if (event instanceof DeleteEvent) {
                MessageTreeItem tItem = (MessageTreeItem) event.getSource();
                MessageTree.this._messages.remove(tItem.getMessage());
                MessageTree.this._messageTree.removeItem(tItem);
                viewMessages();

            }
        }
    }
}
