/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 *
 * @author amit
 */
public class StatusPanel extends Composite {
    
    private SimplePanel _mainPanel;
    private Label _status;
    
    public StatusPanel(){
        _status = new Label("Welcome to Tapuach forum");
        _mainPanel = new SimplePanel();
        _mainPanel.add(_status);
        initWidget(_mainPanel);

    }
    public String getStatus(){
        return this._status.getText();
    }
    public void SetStatus(String newSatatus){
        this._status.setText(newSatatus);
    }
}
