/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import java.util.Date;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.client.UI.ClientUser;

/**
 *
 * @author Nir
 */
public class replyMessageWindow  extends PopupPanel{

        final FormPanel _mainPanel = new FormPanel();



    public replyMessageWindow(final int parentId, final String subject) {
       super(false,true);
        final VerticalPanel _holder;
        final Label _header;
        final Label _LSubject;
        final TextBox _TBSubject;
        final Label _LBody;
        final TextArea _TABody;
        final Button _BSave;
        final Button _Bcancel;
        final HorizontalPanel _buttonsPanel;
        final Label lResult;/////////////////////////////test

        _mainPanel.setAction("/myFormHandler");
        _mainPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        _mainPanel.setMethod(FormPanel.METHOD_POST);

        _holder = new VerticalPanel();
                _holder.setStyleName("blueBack");
        _mainPanel.setWidget(_holder);
        this.setWidget(_mainPanel);
        _header = new Label("Add Reply to subject : " + subject);
        _LSubject = new Label("Subject:");
        _TBSubject = new TextBox();
        _LBody = new Label("body:");
        _TABody = new TextArea();
        lResult = new Label("Please enter the messege details or press cancel to go back to the Forum");
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
        _holder.add(lResult);
       this.setGlassEnabled(true);
        //initWidget(_holder);
        //_pp.center();

        final AsyncCallback<String> callback = new AsyncCallback<String>() {

            public void onSuccess(String result) {
                replyMessageWindow.super.hide();
                LayoutPanel lp = (LayoutPanel) RootLayoutPanel.get().getWidget(0);
                lp.remove(3);
                MessageViewer m = new MessageViewer();
                m.setSize("1024 px", "300 px");
                ScrollPanel s = new ScrollPanel(m);
                s.setHeight("430px");
                m.setStyleName("messageviewer");
                lp.add(s);
                lp.setWidgetTopHeight(s, 130, Unit.PX, 450, Unit.PX);
            }

            public void onFailure(Throwable caught) {
                         _Bcancel.setEnabled(true);
                  _Bcancel.setText("Go Back");
       //           lResult.setText("PROBLEM!! the problem is" +  caught.getMessage());
                lResult.setText("There was a problem to add replay. Please REFRESH the forum and try again.");

            }
        };

        _BSave.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
               _BSave.setEnabled(false);
                _Bcancel.setEnabled(false);
                String subject = _TBSubject.getText();
                String body = _TABody.getText();
                String _nickName = ClientUser.getClient().getNickName();
                lResult.setStyleName("panel");
                lResult.setText("please wait while the server adding your message");
                //getService().myMethod("test", callback);
             getService().addReply(parentId, _nickName, subject, body, callback);
         //    replyMessageWindow.super.hide();

            }
        });


        _Bcancel.addClickHandler(new ClickHandler() {

           public void onClick(ClickEvent event) {
           //     _holder.setVisible(false);
            //RootLayoutPanel.get().remove(1);
            //RootLayoutPanel.get().getWidget(0).setVisible(true);
              replyMessageWindow.super.hide();
            }
        });
        this.center();
    }


    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }
}