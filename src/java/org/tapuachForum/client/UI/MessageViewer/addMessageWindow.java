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
public class addMessageWindow extends Composite {

    final FormPanel _mainPanel = new FormPanel();

    public addMessageWindow() {
      VerticalPanel _holder;
    final Label _header;
    final Label _LSubject;
    final TextBox _TBSubject;
    final Label _LBody;
    final TextArea _TABody;
    final Button _BSave;
    final Button _Bcancel;
   final HorizontalPanel _buttonsPanel;

          _mainPanel.setAction("/myFormHandler");
    _mainPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
    _mainPanel.setMethod(FormPanel.METHOD_POST);


     _holder = new VerticalPanel();
    _mainPanel.setWidget(_holder);

        _holder = new VerticalPanel();
        _header = new Label("Add message");
        _LSubject = new Label("Subject:");
        _TBSubject = new TextBox();
        _LBody = new Label("body:");
        _TABody = new TextArea();
        _BSave = new Button("Save");
        _Bcancel = new Button("cancel");
        _buttonsPanel = new HorizontalPanel();
        _holder.add(_header);
        _holder.add(_LSubject);
        _holder.add(_TBSubject);
        _holder.add(_LBody);
        _holder.add(_TABody);

        _buttonsPanel.add(_BSave);
        _buttonsPanel.add(_Bcancel);
        _holder.add(_buttonsPanel);
       initWidget(_holder);
    }
}    

