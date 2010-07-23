/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import java.util.ArrayList;
import org.tapuachForum.shared.MessageData;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author amit
 */
public class MessageTree extends Composite {

    private HorizontalPanel _mainPanel;
    private Tree _messageTree;
    private final int numFromSearch;

    public MessageTree(int numFromSearchInit) {

        numFromSearch = numFromSearchInit;
              this._mainPanel = new HorizontalPanel();
        this._mainPanel.setSize("980x", "320px");
        this._messageTree = new Tree();
        this._mainPanel.add(this._messageTree);
        initWidget(this._mainPanel);
        testInit();
    }

    public MessageTree() {
        numFromSearch = -18;
        this._mainPanel = new HorizontalPanel();
        this._mainPanel.setSize("980x", "320px");
        this._messageTree = new Tree();
        this._mainPanel.add(this._messageTree);
        initWidget(this._mainPanel);
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

    public void refreshTreeForSearch(Vector<MessageInterface> messages) {
        for (MessageInterface m : messages) {
            MessageTreeItem tItem = new MessageTreeItem(m);
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = m.getReplies();
            addRepliesForSearch(tReplies, tItem, numFromSearch);
        }
    }

    public void refreshTree(Vector<MessageInterface> messages) {
        for (MessageInterface m : messages) {
            MessageTreeItem tItem = new MessageTreeItem(m);
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = m.getReplies();
            addReplies(tReplies, tItem);
        }
    }

    /**
     * used for adding replies for each message in the tree
     * used by refreshTree
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

    private boolean addRepliesForSearch(ArrayList<Message> replies, MessageTreeItem parent, int msgNumber) {
        boolean ans = false;
        for (MessageInterface m : replies) {
            MessageTreeItem tItem = new MessageTreeItem(m);
            parent.addItem(tItem);
            if (addRepliesForSearch(m.getReplies(), tItem, msgNumber)) {
                ans = true;
            }
        }
        if ((parent.getMessage().getIndex() == msgNumber) | ans) {
            ans = true;
            parent.setState(true);
        }
        return ans;
    }

    void refreshTreeByIndex(Vector<MessageInterface> result, int start, int stop) {
        _messageTree.removeItems();
        for (int i = start; i < stop; i++) {
            MessageTreeItem tItem = new MessageTreeItem(result.get(i));
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = result.get(i).getReplies();
            addReplies(tReplies, tItem);
        }
    }

        void refreshTreeByIndexAfterSearch(Vector<MessageInterface> result, int start, int stop,int indexToOpen) {
        _messageTree.removeItems();
        for (int i = start; i < stop; i++) {
            MessageTreeItem tItem = new MessageTreeItem(result.get(i));
            this._messageTree.addItem(tItem);
            ArrayList<Message> tReplies = result.get(i).getReplies();
            addRepliesForSearch(tReplies, tItem,indexToOpen);
           //     addReplies(tReplies, tItem);
        }
    }

        private boolean getIndexOfSuperIndex(ArrayList<Message> replies,  int msgIndex) {
        for (MessageInterface m : replies) {
             if (m.getIndex() == msgIndex)
                return true;
             if (getIndexOfSuperIndex(m.getReplies(),  msgIndex))
                return true;
        }
        return false;
        }

        public int findIndex(Vector<MessageInterface> messages, int indexTofind) {
        for (int i = 0; i < messages.size(); i++){
            if (messages.get(i).getIndex() == indexTofind)
                return i;
            ArrayList<Message> tReplies = messages.get(i).getReplies();
            if (getIndexOfSuperIndex(tReplies,indexTofind))
                return i;
        }
        return 2;
        }


}
