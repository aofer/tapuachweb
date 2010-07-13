/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.user.client.ui.*;

/**
 *
 * @author amit
 */
public class addMessageWindow extends Composite{

    private FormPanel _mainPanel;
    private VerticalPanel _holder;
    private Label _header;
    private Label _LSubject;
    private TextBox _TBSubject;
    private Label _LBody;
    private TextArea _TABody;
    private Button _BSave;
    private Button _Bcancel;
    private HorizontalPanel _buttonsPanel;

    public addMessageWindow(){
        _mainPanel = new FormPanel();
        _holder = new VerticalPanel();
        _header = new Label("Add message");
        _LSubject = new Label("Subject:");
        _TBSubject = new TextBox();
        _LBody = new Label("body:");
        _TABody = new TextArea();
        _BSave = new Button();
        _Bcancel = new Button();
        _mainPanel.add(_holder);
        _holder.add(_header);
        _holder.add(_LSubject);
        _holder.add(_TBSubject);
        _holder.add(_LBody);
        _holder.add(_TABody);
        _holder.add(_buttonsPanel);
        _buttonsPanel.add(_BSave);
        _buttonsPanel.add(_Bcancel);
        initWidget(_mainPanel);


    }
}
