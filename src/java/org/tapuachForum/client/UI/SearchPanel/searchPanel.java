/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.SearchPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosureEvent;
import com.google.gwt.user.client.ui.DisclosureHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DisclosurePanelImages;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageData;
import org.tapuachForum.shared.SearchHit;


/**
 *
 * @author Liron Katav
 */
public class searchPanel extends Composite {

    private Label _lSearch;
    private TextBox _tSearchBox;
    private Button _bsearchButton;
    private RadioButton _byAuther;
    private RadioButton _byContext;
    private HorizontalPanel _hSearch;
    private HorizontalPanel _hRadiobuttons;
    private VerticalPanel _mainPanel;
    DisclosurePanel _disPanel;
    private Label _info;

    public searchPanel() {
        _lSearch = new Label("search: ");
        _tSearchBox = new TextBox();
        _bsearchButton = new Button("Go");
        _byAuther = new RadioButton("RadioButtonGroup", "By Author");
        _byContext = new RadioButton("RadioButtonGroup", "By Context");
        _hSearch = new HorizontalPanel();
        _hRadiobuttons = new HorizontalPanel();
        _mainPanel = new VerticalPanel();
        _info = new Label ("");
        _hSearch.add(_lSearch);
        _hSearch.add(_tSearchBox);
        _hSearch.add(_bsearchButton);
        _hRadiobuttons.add(_byAuther);
        _hRadiobuttons.add(_byContext);
        _byAuther.setValue(true);
        _mainPanel.add(_hSearch);
        _mainPanel.add(_hRadiobuttons);
        _mainPanel.add(_info);


        _disPanel = new DisclosurePanel("Click Here To search");
        _disPanel.addEventHandler(new DisclosureHandler() {

            public void onClose(DisclosureEvent event) {
                _disPanel.setHeader(new DisclosurePanelHeader(false, "Search"));
            }

            public void onOpen(DisclosureEvent event) {
                _disPanel.setHeader(new DisclosurePanelHeader(true, "Close"));
            }
        });
        _disPanel.add(_mainPanel);
        _disPanel.setWidth("200px");
        _disPanel.setHeight("100px");
        initWidget(_disPanel);



        // Create an asynchronous callback to handle the result.
        final AsyncCallback<SearchHit[]> callback = new AsyncCallback<SearchHit[]>() {

            public void onSuccess(SearchHit[] result) {
                _tSearchBox.setText("pop up should be open");
                searchResultsPanel sRP = new searchResultsPanel(result);
                  sRP.center();
                    _tSearchBox.setText(result.length + result.toString() + " the pop up has been open");
                         _bsearchButton.setEnabled(true);
     //           showSearchResult(result);
            }

            public void onFailure(Throwable caught) {
                     _bsearchButton.setEnabled(true);
                _tSearchBox.setText("problem "+ caught.getMessage());
                throw new UnsupportedOperationException("Not supported yet.");
            }

            private void showSearchResult(SearchHit[] result) {
                searchResultsPanel sRP = new searchResultsPanel(result);

           //     throw new UnsupportedOperationException("Not yet implemented");
            }
        };

        _bsearchButton.addClickHandler(new ClickHandler() {
            // Make remote call. Control flow will continue immediately and later
            // 'callback' will be invoked when the RPC completes.

            public void onClick(ClickEvent event) {
                String searchValue = _tSearchBox.getText();
                Boolean searchBy = _byAuther.getValue();
                _bsearchButton.setEnabled(false);
                if (searchBy == true) {
                    _tSearchBox.setText ("author");
                    _tSearchBox.setText("please wait while seatching for "+searchValue+ " by author");
                    //need to change
                    getService().searchByAuthor(searchValue, 0, 10, callback);
                } else {
                     _tSearchBox.setText ("context");
                    _tSearchBox.setText("please wait while seatching for "+searchValue+ " by context");
                    getService().searchByContext(searchValue, 0, 10, callback);
                }
            }
        });

    }

    public static MyServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.
        return GWT.create(MyService.class);
    }
}

class DisclosurePanelHeader extends HorizontalPanel {

    final DisclosurePanelImages images = (DisclosurePanelImages) GWT.create(DisclosurePanelImages.class);

    public DisclosurePanelHeader(boolean isOpen, String html) {
        add(isOpen ? images.disclosurePanelOpen().createImage()
                : images.disclosurePanelClosed().createImage());
        add(new HTML(html));
    }
}


