/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.SearchEngine.SearchHit;

/**
 *
 * @author amit ofer
 */
public class SearchByContentMessage extends ClientMessage {

    /**
     * the phrase that the user would like to search
     */
    private String m_phrase;
    /**
     * hits from index from
     */
    private int m_from;
    /**
     * hits until index to
     */
    private int m_to;

    public SearchByContentMessage(String phrase,int from,int to){
        this.m_phrase = phrase;
        this.m_from = from;
        this.m_to = to;
    }

    public ServerResponse doOperation(ForumFascade forum) {
        SearchHit[] tHits = forum.searchByContent(m_phrase, m_from, m_to);
               String tForumString = "";
               for(SearchHit h : tHits) {
               tForumString += MessagesParser.Encode(h.getMessage());
               }
                ServerResponse tResponse = new ServerResponse(tForumString, true);
		return tResponse;

    }

}
