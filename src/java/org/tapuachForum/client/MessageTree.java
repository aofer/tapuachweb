/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import java.util.Date;

/**
 *
 * @author amit
 */
public class MessageTree extends Composite {
    private ScrollPanel _mainPanel;
    private Tree _messageTree;
    public MessageTree(){
        this._mainPanel = new ScrollPanel();
        this._messageTree = new Tree();
        this._mainPanel.add(this._messageTree);
        initWidget(this._mainPanel);

        /*
         * HandlerRegistration registration = supplierReady.addOpenHandler(new
OpenHandler<SupplierWithDetailsModel>() {
	public void onOpen(OpenEvent<SupplierWithDetailsModel> event) {
		registration.removeHandler();

		// do something
	}
});

         */
/*                categoryTree.addTreeListener(new TreeListener() {
        public void onTreeItemSelected(TreeItem item) {
        selectedCategory = ((CategoryTreeItem)item).getCategory();
        updateTasksList();
        }
        public void onTreeItemStateChanged(TreeItem item) {
        }
        });*/
        testInit();
    }

    public void testInit(){
        Message msg = new Message("aofer", "testing the forum", "blah blah",new Date(), new Date());
        MessageTreeItem item1 = new MessageTreeItem(msg);
        this._messageTree.addItem(item1);
        Message msg2 =  new Message("aofer", "testing the forum2222", "Mahabaz baz baz",new Date(), new Date());
        Message msg3 =  new Message("aofer", "testing the forum2233333", "Nahabaz baz baz\n i would like to say that \n bufhukdg jkshks dsfjsdkfd dsfkjhsd\n dsgjk\n klshgkj!",new Date(), new Date());
        MessageTreeItem item2 = new MessageTreeItem(msg2);
        MessageTreeItem item3 = new MessageTreeItem(msg3);
        item1.addItem(item2);
        item2.addItem(item3);
    }
}
