/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.MessageViewer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import java.util.Date;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;

/**
 *
 * @author amit
 */
public class addMessageWindow extends Composite {

    final FormPanel _mainPanel = new FormPanel();

    public addMessageWindow() {
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
        _mainPanel.setWidget(_holder);

        _header = new Label("Add message");
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
        initWidget(_holder);

        final AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {

            public void onSuccess(Integer result) {
                int resultInt = result.intValue();
                lResult.setText("the answer is" + resultInt);
            }

            public void onFailure(Throwable caught) {
            RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);

            }
        };

        _BSave.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                String subject = _TBSubject.getText();
                String body = _TABody.getText();
                String _nickName = "bobspong";

                lResult.setStyleName("panel");
                lResult.setText("please wait while the server adding your message.");
                //getService().myMethod("test", callback);
                getService().addMessage(_nickName, subject, body, callback);

            }
        });


        _Bcancel.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
           //     _holder.setVisible(false);
            RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);
            }
        });
    }

    public addMessageWindow(final int parentId) {
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
        _mainPanel.setWidget(_holder);

        _header = new Label("Add Replay to message number " + parentId);
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
        initWidget(_holder);

        final AsyncCallback<String> callback = new AsyncCallback<String>() {

            public void onSuccess(String result) {
                lResult.setText("the answer is" + result);
            }

            public void onFailure(Throwable caught) {
             //   lResult.setText("Communication failed?");
             //   lResult.setStyleName("badResutl");
            RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);

            }
        };

        _BSave.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                String subject = _TBSubject.getText();
                String body = _TABody.getText();
                String _nickName = "bobspong";

                lResult.setStyleName("panel");
                lResult.setText("please wait while the server adding your message");
                //getService().myMethod("test", callback);
             getService().addReply(parentId, _nickName, subject, body, callback);

            }
        });


        _Bcancel.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
             //   _holder.setVisible(false);
            RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);
            }
        });
    }

      public addMessageWindow(final int messageId ,final String subject,final String nickName,final String body) {
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
        _mainPanel.setWidget(_holder);

        _header = new Label("Add message");
        _LSubject = new Label("Subject:");
        _TBSubject = new TextBox();
        _TBSubject.setText(subject);
        _LBody = new Label("body:");
        _TABody = new TextArea();
        _TABody.setText(body);
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
        initWidget(_holder);

        final AsyncCallback<String> callback = new AsyncCallback<String>() {

            public void onSuccess(String result) {
      //    lResult.setText(result);
                RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);
          //      int resultInt = result.intValue();
           //     lResult.setText("the answer is" + resultInt);
            }

            public void onFailure(Throwable  caught) {
            RootLayoutPanel.get().remove(1);
               RootLayoutPanel.get().getWidget(0).setVisible(true);
           //      lResult.setText("some thing wrong " + messageId+ " "+ subject+" " + body+" " + nickName);

            }
         };

        _BSave.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {

                String subject = _TBSubject.getText();
                String body = _TABody.getText();
                lResult.setStyleName("panel");
                lResult.setText("please wait while the server updating  the message.");
     
                getService().editMessage( nickName, messageId, subject, body, callback);

            }
        });


        _Bcancel.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
           //     _holder.setVisible(false);
            RootLayoutPanel.get().remove(1);
            RootLayoutPanel.get().getWidget(0).setVisible(true);
            }
        });
    }
    public static MyServiceAsync getService() {
        return GWT.create(MyService.class);
    }
}    

