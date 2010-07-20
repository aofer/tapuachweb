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
import java.util.Vector;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyServiceAsync;
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

    public searchPanel() {
        _lSearch = new Label("search: ");
        _tSearchBox = new TextBox();
        _bsearchButton = new Button("Go");
        _byAuther = new RadioButton("RadioButtonGroup", "By Auther");
        _byContext = new RadioButton("RadioButtonGroup", "By Context");
        _hSearch = new HorizontalPanel();
        _hRadiobuttons = new HorizontalPanel();
        _mainPanel = new VerticalPanel();
        _hSearch.add(_lSearch);
        _hSearch.add(_tSearchBox);
        _hSearch.add(_bsearchButton);
        _hRadiobuttons.add(_byAuther);
        _hRadiobuttons.add(_byContext);
        _mainPanel.add(_hSearch);
        _mainPanel.add(_hRadiobuttons);


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
        _disPanel.setWidth("250px");
        _disPanel.setHeight("200px");
        initWidget(_disPanel);



        // Create an asynchronous callback to handle the result.
        final AsyncCallback<Vector<SearchHit>> callback = new AsyncCallback<Vector<SearchHit>>() {

            public void onSuccess(Vector<SearchHit> result) {
                showSearchResult(result);
            }

            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            private void showSearchResult(Vector<SearchHit> result) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
        };

        _bsearchButton.addClickHandler(new ClickHandler() {
            // Make remote call. Control flow will continue immediately and later
            // 'callback' will be invoked when the RPC completes.

            public void onClick(ClickEvent event) {
                String searchValue = _tSearchBox.getText();
                Boolean searchBy = _byAuther.getValue();
                if (searchBy == true) {
                    _tSearchBox.setText("auther");
                    //need to change
                    getService().searchByAuthor(searchValue, 0, 10, callback);
                } else {
                    _tSearchBox.setText("context");
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


