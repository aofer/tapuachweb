/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
* @author Liron Katav
 */
public interface MyServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void register(String firstname, String lastName, String email, String nickname, String username, String pass, AsyncCallback<String> callback);


  }
