/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.manager;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author amit
 */
public interface TreeManager {

    void refreshTree( AsyncCallback<Void> callback);

}
