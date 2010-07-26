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
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ApplicationEventListenerCollection;
import org.tapuachForum.client.Events.ApplicationEventSource;
import org.tapuachForum.client.Events.GotomessageEvent;
import org.tapuachForum.client.MyService;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.SearchHit;

/**
 *
 * @author Liron Katav
 */
public class searchPanel extends Composite implements ApplicationEventSource {

    private Label _lSearch;
    private Label _lFrom;
    private Label _lTo;
    private TextBox _tSearchBox;
    private TextBox _tFrom;
    private TextBox _tTo;
    private Button _bsearchButton;
    private RadioButton _byAuther;
    private RadioButton _byContext;
    private HorizontalPanel _hSearch;
    private HorizontalPanel _hRadiobuttons;
    private VerticalPanel _mainPanel;
    private DisclosurePanel _disPanel;
    private Label _info;
    private ApplicationEventListenerCollection _listeners;

    public searchPanel() {
        _listeners = new ApplicationEventListenerCollection();
        _lSearch = new Label("search: ");
        _tSearchBox = new TextBox();
        _lFrom = new Label("from ");
        _lTo = new Label("to ");
        _tFrom = new TextBox();
        _tTo = new TextBox();
        _tFrom.setWidth("20px");
        _tTo.setWidth("20px");
        _bsearchButton = new Button("Go");
        _byAuther = new RadioButton("RadioButtonGroup", "By Author");
        _byContext = new RadioButton("RadioButtonGroup", "By Context");
        _hSearch = new HorizontalPanel();
        _hRadiobuttons = new HorizontalPanel();
        _mainPanel = new VerticalPanel();
        _info = new Label("");
        _hSearch.add(_lSearch);
        _hSearch.add(_tSearchBox);
        _hSearch.add(_lFrom);
        _hSearch.add(_tFrom);
        _hSearch.add(_lTo);
        _hSearch.add(_tTo);
        _hSearch.add(_bsearchButton);
        _hSearch.add(_info);
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
        _disPanel.setWidth("350px");
        _disPanel.setHeight("100px");
        initWidget(_disPanel);

        final AsyncCallback<SearchHit[]> callback = new AsyncCallback<SearchHit[]>() {

            public void onSuccess(SearchHit[] result) {
                
                if (result.length != 0) {
                    searchResultsPanel sRP = new searchResultsPanel(result);
                    sRP.addListener(new SearchResultsListener());
                    sRP.center();
                }
                _info.setText(result.length + " results.");
                _bsearchButton.setEnabled(true);
            }

            public void onFailure(Throwable caught) {
                _bsearchButton.setEnabled(true);
                _info.setText("please try again");
                //_tSearchBox.setText("problem " + caught.getMessage());
            }

            private void showSearchResult(SearchHit[] result) {
                searchResultsPanel sRP = new searchResultsPanel(result);
                sRP.addListener(new SearchResultsListener());
            }
        };
        _bsearchButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                String searchValue = _tSearchBox.getText();
                _tSearchBox.setText("");
                Boolean searchBy = _byAuther.getValue();
                int from;
                int to;
                if (_tFrom.getText().compareTo("") == 0) {
                    from = 0;
                } else {
                    from = Integer.parseInt(_tFrom.getText());
                }
                if (_tTo.getText().compareTo("") == 0) {
                    to = 0;
                } else {
                    to = Integer.parseInt(_tTo.getText());
                }
                _tFrom.setText("");
                _tTo.setText("");

                _bsearchButton.setEnabled(false);
                if (searchBy == true) {
                    _info.setText("author");
                    _info.setText("searching by author....");
                    //need to change
                    getService().searchByAuthor(searchValue, from, to, callback);
                } else {
                    _info.setText("context");
                    _info.setText("searching by context...");
                    getService().searchByContext(searchValue, from, to, callback);
                }
            }
        });
    }

    public static MyServiceAsync getService() {
        return Locator.getInstance();
    }

    public void addListener(ApplicationEventListener listener) {
        _listeners.add(listener);
    }

    public void removeListener(ApplicationEventListener listener) {
        _listeners.remove(listener);
    }

    public void clearListeners() {
        _listeners.clear();
    }

    protected class SearchResultsListener implements ApplicationEventListener {

        public void handle(ApplicationEvent event) {
            if (event instanceof GotomessageEvent) {
                searchPanel.this._listeners.fireEvent(event);
            }
        }
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


