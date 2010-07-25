/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.SearchPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import java.util.Date;
import org.tapuachForum.client.Events.ApplicationEvent;
import org.tapuachForum.client.Events.ApplicationEventListener;
import org.tapuachForum.client.Events.ApplicationEventListenerCollection;
import org.tapuachForum.client.Events.ApplicationEventSource;
import org.tapuachForum.client.Events.GotomessageEvent;
import org.tapuachForum.client.MyService.Locator;
import org.tapuachForum.client.MyServiceAsync;
import org.tapuachForum.shared.SearchHit;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author Liron Katav
 */
public class searchResultsPanel extends PopupPanel  implements ApplicationEventSource{

    private SearchHit[] new_Results;
    private static final int pageSize = 2;
    private ScrollPanel _scrollPanelGrid;
    private HorizontalPanel _navigationPanel;
    private DockPanel contentDockPanel;
    private Label _lTitle;
    private FlexTable _resultsTable;
    private Button _buttonFirstPage;
    private Button _buttonPrevPage;
    private Button _buttonNextPage;
    private Button _buttonLastPage;
    private Button _buttonReturn;
    private int _selectedRowIndex = -1;
    public int indexOfPages;
    public int numberOfPages;
    private int restOfPages;
    private int[] indexesInt;
    protected ApplicationEventListenerCollection _listeners;


    public searchResultsPanel(final SearchHit[] result) {
        super(false, true);
        new_Results = result;
        contentDockPanel = new DockPanel();
        _scrollPanelGrid = new ScrollPanel();
        //       _lTitle = new Label("Search Results:");
        _resultsTable = new FlexTable();
        Label lAuther = new Label("Author:");
        Label lSubject = new Label("Subject:");
        Label lContext = new Label("Context:");
        Label lDate = new Label("Date added:");
        Label lScore = new Label("Score:");
        _navigationPanel = new HorizontalPanel();
        _buttonReturn = new Button("Return ");
        _buttonFirstPage = new Button("First");
        _buttonPrevPage = new Button("Prev");
        _buttonNextPage = new Button("Next");
        _buttonLastPage = new Button("Last");
        _lTitle = new Label("Search Results:");

        this._listeners = new ApplicationEventListenerCollection();

        this.setGlassEnabled(true);
        this.setWidget(contentDockPanel);

        //        initWidget(contentDockPanel);//*************************************************************************************************
        this.setSize("500px", "350px");
        _resultsTable.getRowFormatter().setStyleName(0, "labelStyle");
        _resultsTable.getColumnFormatter().setWidth(0, "10px");
        _resultsTable.getColumnFormatter().setWidth(1, "80px");
        _resultsTable.getColumnFormatter().setWidth(2, "100px");
        _resultsTable.getColumnFormatter().setWidth(3, "120px");
        _resultsTable.getColumnFormatter().setWidth(4, "120px");
        _resultsTable.getColumnFormatter().setWidth(5, "70px");
        _resultsTable.getColumnFormatter().setWidth(6, "120px");
        _resultsTable.setStyleName("blueBack");
        lAuther.setWidth("80px");
        lSubject.setWidth("100px");
        lContext.setWidth("120px");
        lDate.setWidth("120px");
        lScore.setWidth("70px");
        _resultsTable.setWidget(0, 1, lAuther);
        _resultsTable.setWidget(0, 2, lSubject);
        _resultsTable.setWidget(0, 3, lContext);
        _resultsTable.setWidget(0, 4, lDate);
        _resultsTable.setWidget(0, 5, lScore);
        _scrollPanelGrid.add(_resultsTable);
        _scrollPanelGrid.setStyleName("blueBack");
        this.setGlassEnabled(true);
        
        this._resultsTable.addTableListener(new TableListener() {

            public void onCellClicked(SourcesTableEvents sender, int row, int column) {
                searchResultsPanel.this.cellClicked(row, column);
            }
        });

        _navigationPanel.setSize("100%", "26px");
        _navigationPanel.add(_buttonReturn);
        _navigationPanel.add(_buttonFirstPage);
        _navigationPanel.add(_buttonPrevPage);
        _navigationPanel.add(_buttonNextPage);
        _navigationPanel.add(_buttonLastPage);
        _buttonPrevPage.setEnabled(false);
        _buttonReturn.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                searchResultsPanel.this.buttonReturnClicked();
            }
        });
        _buttonFirstPage.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                searchResultsPanel.this.buttonFirstPageClicked();
            }
        });
        _buttonPrevPage.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                searchResultsPanel.this.buttonPrevPageClicked();
            }
        });
        _buttonNextPage.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                searchResultsPanel.this.buttonNextPageClicked();
            }
        });
        _buttonLastPage.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                searchResultsPanel.this.buttonLastPageClicked();
            }
        });
        _navigationPanel.setCellVerticalAlignment(_buttonReturn, HasVerticalAlignment.ALIGN_BOTTOM);
        _navigationPanel.setCellVerticalAlignment(_buttonFirstPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _navigationPanel.setCellVerticalAlignment(_buttonPrevPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _navigationPanel.setCellVerticalAlignment(_buttonNextPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _navigationPanel.setCellVerticalAlignment(_buttonLastPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _navigationPanel.setCellHorizontalAlignment(_buttonFirstPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellHorizontalAlignment(_buttonPrevPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellHorizontalAlignment(_buttonNextPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellHorizontalAlignment(_buttonLastPage, HasHorizontalAlignment.ALIGN_RIGHT);
        contentDockPanel.add(_lTitle, DockPanel.NORTH);
        contentDockPanel.add(_scrollPanelGrid, DockPanel.NORTH);
        contentDockPanel.add(_navigationPanel, DockPanel.SOUTH);
        contentDockPanel.setCellHeight(_navigationPanel, "26px");
        contentDockPanel.setCellWidth(_navigationPanel, "100%");
        contentDockPanel.setCellVerticalAlignment(_navigationPanel, HasVerticalAlignment.ALIGN_BOTTOM);
        contentDockPanel.setStyleName("blueBack");
        indexOfPages = 0;
        numberOfPages = new_Results.length / pageSize;
        restOfPages = new_Results.length % pageSize;
        if (restOfPages == 0) {
            numberOfPages--;
            restOfPages = pageSize;
        }
        indexesInt = new int[new_Results.length];
        for (int i = 0; i < new_Results.length; i++) {
            indexesInt[i] = new_Results[i].getMessage().getIndex();
        }

        refreshResultsPanel();
//        Date tDate = new Date();
//        if ((result != null) && (result.length > 0))
//            addMessageToTable(result[0]);
//        else
//            addMessageToTable(new SearchHit(new Message(new MessageData("nick cool","hey there","hey body", tDate, tDate)), 15));
    }

    public void refreshResultsPanel() {
        clearTable();
        if ((new_Results == null) || (new_Results.length == 0)) {
            contentDockPanel.remove(_scrollPanelGrid);
            _lTitle.setText("bob");
            //   contentDockPanel.add(new Label("There are no results"), DockPanel.CENTER);
        } else {
            contentDockPanel.add(_scrollPanelGrid, DockPanel.NORTH);
            if (indexOfPages < numberOfPages) {
                for (int i = indexOfPages * pageSize; i < (indexOfPages + 1) * pageSize; i++) {
                    addMessageToTable(new_Results[i], i);
                }
            } else {
                for (int i = indexOfPages * pageSize; i < (indexOfPages * pageSize) + restOfPages; i++) {
                    addMessageToTable(new_Results[i], i);
                }
            }
        }
    }

    /**
     * Adds the given message to the result list
     */
    public void addMessageToTable(SearchHit search_hit, int indexOfMessage) {
        MessageInterface msg = search_hit.getMessage();
        final int row = _resultsTable.getRowCount();
        _resultsTable.setWidget(row, 0, new Label("" + (indexOfMessage + 1)));
        Label Authername = new Label(msg.getNickname());
        Label msgSubject = new Label(msg.getSubject());
        Label msgContext = new Label(msg.getBody());
        Date tWrieDay = msg.getWriteDate();
        String sDate = tWrieDay.toString();
        sDate = sDate.substring(0, 11);
        Label msgDate = new Label(sDate);
        Label msgScore = new Label(Double.toString(search_hit.getScore()));
        Label msgIndex = new Label("" + msg.getIndex());
        _resultsTable.setWidget(row, 1, Authername);
        _resultsTable.setWidget(row, 2, msgSubject);
        _resultsTable.setWidget(row, 3, msgContext);
        _resultsTable.setWidget(row, 4, msgDate);
        _resultsTable.setWidget(row, 5, msgScore);
        _resultsTable.setWidget(row, 6, msgIndex);
    }

    public void clearTable() {
        int toDel = _resultsTable.getRowCount();
        for (int i = toDel - 1; i > 0; i--) {
            _resultsTable.removeRow(i);
        }
    }

    ///for testing the panel
    public void testAdd() {
        final int row = _resultsTable.getRowCount();
        _resultsTable.setWidget(row, 0, new Label(""));
        Label Authername = new Label("liron");
        Label msgSubject = new Label("the subject");
        Label msgContext = new Label("Context");
        Label msgDate = new Label("12/15/2010");
        Label msgScore = new Label(Double.toString(2.5));
        _resultsTable.setWidget(row, 1, Authername);
        _resultsTable.setWidget(row, 2, msgSubject);
        _resultsTable.setWidget(row, 3, msgContext);
        _resultsTable.setWidget(row, 4, msgDate);
        _resultsTable.setWidget(row, 5, msgScore);
    }

    private void testing() {
        testAdd();
        testAdd();
    }

    private void buttonReturnClicked() {
        searchResultsPanel.super.hide();
    }

    private void buttonPrevPageClicked() {
        indexOfPages--;
        if (indexOfPages == 0) {
            _buttonPrevPage.setEnabled(false);
        }
        if (indexOfPages < numberOfPages) {
            _buttonNextPage.setEnabled(true);
        }

        refreshResultsPanel();
    }

    private void buttonLastPageClicked() {
        indexOfPages = numberOfPages;
        if (indexOfPages < numberOfPages) {
            _buttonNextPage.setEnabled(false);
        }
        refreshResultsPanel();
    }

    private void buttonNextPageClicked() {
        indexOfPages++;
        if (indexOfPages == 1) {
            _buttonPrevPage.setEnabled(true);
        }
        if (indexOfPages == numberOfPages) {
            _buttonNextPage.setEnabled(false);
        }
        refreshResultsPanel();
    }

      public static MyServiceAsync getService() {

        return Locator.getInstance();
    }

    private void buttonFirstPageClicked() {
        indexOfPages = 0;
        if (indexOfPages < numberOfPages) {
            _buttonNextPage.setEnabled(true);
        }
        _buttonPrevPage.setEnabled(false);
        refreshResultsPanel();
    }
 /* private void cellClicked(int row, int column) {
        if (_selectedRowIndex == row) {
            _selectedRowIndex = -1;
            _resultsTable.getRowFormatter().removeStyleName(row, "row-selected");
        } else {
            if (_selectedRowIndex != -1) {
                _resultsTable.getRowFormatter().removeStyleName(_selectedRowIndex, "row-selected");
            }
            _selectedRowIndex = row;
            _resultsTable.getRowFormatter().addStyleName(row, "row-selected");
        }
    }*/
    
    private void cellClicked(int row, int column) {
    _lTitle.setText("row is " + row + ". coumn is " + column);
    if ((row > 0) & (row <= pageSize)) {
    int msgIndex = indexesInt[(indexOfPages * pageSize) + row - 1];
    searchResultsPanel.this.fireEvent(new GotomessageEvent(searchResultsPanel.this, msgIndex));
    searchResultsPanel.super.hide();
        }
    }
  public void addListener(ApplicationEventListener listener) {
        this._listeners.add(listener);
    }

    public void removeListener(ApplicationEventListener listener) {
        this._listeners.remove(listener);
    }

    public void clearListeners() {
        this._listeners.clear();
    }
    public void fireEvent(ApplicationEvent event){
        this._listeners.fireEvent(event);
    }
}


