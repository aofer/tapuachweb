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
 * @author amit
 */
public class SearchByAuthorMessage extends ClientMessage{

     /**
     *The nickname of the author who wrote the message.
     */
    private String m_nickname;
    /**
     * hits from index from
     */
    private int m_from;
    /**
     * hits until index to
     */
    private int m_to;

    public SearchByAuthorMessage(String nickname,int from,int to){
        this.m_nickname = nickname;
        this.m_from = from;
        this.m_to = to;
    }

    public ServerResponse doOperation(ForumFascade forum) {
        SearchHit[] tHits = forum.searchByAuthor(m_nickname, m_from, m_to);
               String tForumString = "";
               for(SearchHit h : tHits) {
               tForumString += MessagesParser.Encode(h.getMessage());
               }
                ServerResponse tResponse = new ServerResponse(tForumString, true);
		return tResponse;
    }

}
