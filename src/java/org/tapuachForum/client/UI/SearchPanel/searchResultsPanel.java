/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.client.UI.SearchPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;
import java.util.Date;
import java.util.Vector;
import org.tapuachForum.shared.Message;
import org.tapuachForum.shared.MessageData;
import org.tapuachForum.shared.SearchHit;
import org.tapuachForum.shared.MessageInterface;

/**
 *
 * @author Liron Katav
 */
public class searchResultsPanel extends PopupPanel {

    private static final int pageSize = 10;
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

    public searchResultsPanel(SearchHit[] result) {
        super(false, true);


        contentDockPanel = new DockPanel();
        _scrollPanelGrid = new ScrollPanel();
        _lTitle = new Label("Search Results:");
        _resultsTable = new FlexTable();
        Label lAuther = new Label("Auther:");
        Label lSubject = new Label("Subject:");
        Label lContext = new Label("Context:");
        Label lDate = new Label("Date added:");
        Label lScore = new Label("Score:");
        _navigationPanel = new HorizontalPanel();
        _buttonReturn = new Button("Return ");
        _buttonFirstPage = new Button("<<");
        _buttonPrevPage = new Button("<");
        _buttonNextPage = new Button(">");
        _buttonLastPage = new Button(">>");

        this.setGlassEnabled(true);
        this.setWidget(contentDockPanel);

 //       initWidget(contentDockPanel);*************************************************************************************************
        this.setSize("450px", "300px");

        _lTitle.setStyleName("titleStyle");
        _resultsTable.getRowFormatter().setStyleName(0, "labelStyle");

        _resultsTable.getColumnFormatter().setWidth(0, "10px");
        _resultsTable.getColumnFormatter().setWidth(1, "80px");
        _resultsTable.getColumnFormatter().setWidth(2, "100px");
        _resultsTable.getColumnFormatter().setWidth(3, "120px");
        _resultsTable.getColumnFormatter().setWidth(4, "120px");
        _resultsTable.getColumnFormatter().setWidth(5, "70px");
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
        _navigationPanel.setCellHeight(_buttonReturn, "23px");
        _buttonReturn.setSize("70", "23");
        _navigationPanel.setCellVerticalAlignment(_buttonReturn, HasVerticalAlignment.ALIGN_BOTTOM);
        _buttonReturn.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                searchResultsPanel.this.buttonReturnClicked();
            }
        });



        _navigationPanel.add(_buttonFirstPage);
        _navigationPanel.setCellHeight(_buttonFirstPage, "23px");
        _buttonFirstPage.setSize("25", "23");
        _navigationPanel.setCellVerticalAlignment(_buttonFirstPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _buttonFirstPage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                searchResultsPanel.this.buttonFirstPageClicked();
            }
        });
        _navigationPanel.setCellHorizontalAlignment(_buttonFirstPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellWidth(_buttonFirstPage, "30px");


        _navigationPanel.add(_buttonPrevPage);
        _navigationPanel.setCellHeight(_buttonPrevPage, "23px");
        _buttonPrevPage.setSize("20", "23");
        _navigationPanel.setCellVerticalAlignment(_buttonPrevPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _buttonPrevPage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                searchResultsPanel.this.buttonPrevPageClicked();
            }
        });
        _navigationPanel.setCellHorizontalAlignment(_buttonPrevPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellWidth(_buttonPrevPage, "23px");


        _navigationPanel.add(_buttonNextPage);
        _navigationPanel.setCellHeight(_buttonNextPage, "23px");
        _buttonNextPage.setSize("20", "23");
        _navigationPanel.setCellVerticalAlignment(_buttonNextPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _buttonNextPage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                searchResultsPanel.this.buttonNextPageClicked();
            }
        });
        _navigationPanel.setCellHorizontalAlignment(_buttonNextPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellWidth(_buttonNextPage, "23px");


        _navigationPanel.add(_buttonLastPage);
        _navigationPanel.setCellHeight(_buttonLastPage, "23px");
        _buttonLastPage.setSize("25", "23");
        _navigationPanel.setCellVerticalAlignment(_buttonLastPage, HasVerticalAlignment.ALIGN_BOTTOM);
        _buttonLastPage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                searchResultsPanel.this.buttonLastPageClicked();
            }
        });
        _navigationPanel.setCellHorizontalAlignment(_buttonLastPage, HasHorizontalAlignment.ALIGN_RIGHT);
        _navigationPanel.setCellWidth(_buttonLastPage, "28px");


        contentDockPanel.add(_scrollPanelGrid, DockPanel.CENTER);
        contentDockPanel.add(_lTitle, DockPanel.NORTH);
        contentDockPanel.add(_navigationPanel, DockPanel.SOUTH);
        contentDockPanel.setCellHeight(_navigationPanel, "26px");
        contentDockPanel.setCellWidth(_navigationPanel, "100%");
        contentDockPanel.setCellVerticalAlignment(_navigationPanel, HasVerticalAlignment.ALIGN_BOTTOM);
        contentDockPanel.setStyleName("blueBack");

    //        SearchHit[] shArray = new SearchHit[result.size()];
    ///    for  (int i = 0; i <result.size();  i++)
    //        shArray[i] = result.get(i);
   //   refreshResultsPanel ( result);
        Date tDate = new Date();
        if ((result != null) && (result.length > 0))
            addMessageToTable(result[0]);
        else
            addMessageToTable(new SearchHit(new Message(new MessageData("nick cool","hey there","hey body", tDate, tDate)), 15));
    }

    public void refreshResultsPanel(SearchHit[] new_Results) {
        clearTable();
        if ((new_Results == null) || (new_Results.length == 0)) {
            contentDockPanel.remove(_scrollPanelGrid);
            contentDockPanel.add(new Label("There are no results"), DockPanel.CENTER);
        } else {
            contentDockPanel.add(_scrollPanelGrid, DockPanel.CENTER);
            for (int i = 0; i < new_Results.length; i++) {
                addMessageToTable(new_Results[i]);
            }
        }
    }

    /**
     * Adds the given message to the result list
     */
    public void addMessageToTable(SearchHit search_hit) {
        MessageInterface msg = search_hit.getMessage();
        final int row = _resultsTable.getRowCount();
        _resultsTable.setWidget(row, 0, new Label(""));
        Label Authername = new Label(msg.getNickname());
        Label msgSubject = new Label(msg.getSubject());
        Label msgContext = new Label(msg.getBody());
        Label msgDate = new Label(msg.getWriteDate().toString());
        Label msgScore = new Label(Double.toString(search_hit.getScore()));
        _resultsTable.setWidget(row, 1, Authername);
        _resultsTable.setWidget(row, 2, msgSubject);
        _resultsTable.setWidget(row, 3, msgContext);
        _resultsTable.setWidget(row, 4, msgDate);
        _resultsTable.setWidget(row, 5, msgScore);
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
        //clearTable();
       // refreshResultsPanel(null);
    }

    private void buttonReturnClicked() {
       searchResultsPanel.super.hide();
    }

    private void buttonPrevPageClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void buttonLastPageClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void buttonNextPageClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void buttonFirstPageClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void cellClicked(int row, int column) {
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
    }
}

