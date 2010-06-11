/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Interfaces.MessageInterface;
import Forum.Exceptions.MessageNotFoundException;
import java.util.List;
import Forum.DomainLayer.Forum;
import Forum.DomainLayer.Logger.TapuachLogger;
import Forum.DomainLayer.Message;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Arseny
 */
public class SearchEngineHandler implements SearchEngineInterface {

    private SearchDataInterface _searchData;

    public SearchEngineHandler() {
        _searchData = new SearchData();
    }

    //should init the search index
    public void init() {
        Forum f = Forum.getInstance();
        Vector<MessageInterface> v = f.viewForum();
        for (MessageInterface m : v) {
            addMessage(m);
        }
    }

    public void addMessage(MessageInterface msg) {
        _searchData.addMessage(msg);
        ArrayList<Message> rep = msg.getReplies();
        if (!rep.isEmpty()) {
            for (Message m : rep) {
                addMessage(m);
            }
        }
    }

    public void updateMessage(MessageInterface msg) {
        _searchData.removeMessage(msg);
        _searchData.addMessage(msg);
    }

    public void removeMessage(MessageInterface msg) {
        _searchData.removeMessage(msg);
    }

    public SearchHit[] searchByAuthor(String username, int from, int to) {
        List<Integer> msgIndexs = this._searchData.getByAuthor(username);
        return getSearchHits(msgIndexs, from, to);
    }


    public SearchHit[] searchByContent(String phrase, int from, int to) {
        String[] body = phrase.split(" ");
        List<Integer> msgIndexs=new LinkedList<Integer>();
        for (int i=0;i<body.length;i++){
// Have been change from "body[0]" to "body[i]" by NIr;
            msgIndexs.addAll( this._searchData.getByContent(body[i]));
        }
        return getSearchHits(msgIndexs, from, to);
    }

    private SearchHit[] getSearchHits(List<Integer> msgIndexs, int from, int to) {
        Forum frm;
        int n;
        SearchHit[] retHits;
        MessageInterface msg;

        if (msgIndexs == null) {
            retHits = new SearchHit[0];
        } else {
            frm = Forum.getInstance();
            n = Math.min(to - from + 1, msgIndexs.size() - from);
            retHits = new SearchHit[n];

            for (int i = 0; i < n; i++) {
                try {
                    msg = frm.getMessage(msgIndexs.get(i + from));
                    retHits[i] = new SearchHit(msg, 0);
                } catch (MessageNotFoundException ex) {
                    TapuachLogger tl = TapuachLogger.getInstance();
                    tl.fine("SearchEngine : searchByAuthor : message number" + ex.toString() + "not found");
                }
            }
        }
        return retHits;
    }
}
