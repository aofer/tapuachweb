package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Interfaces.MessageInterface;

/**
 * A single search hit returned after a search operation.
 * 
 * @author Tomer Heber
 */
public class SearchHit implements Comparable<SearchHit>{
	
	private MessageInterface m_message;
	private double m_score;

	public SearchHit(MessageInterface message, double score) {
		m_message = message;
		m_score = score;
	}
	
	public double getScore() {
		return m_score;
	}
	
	public MessageInterface getMessage() {
		return m_message;
	}
    @Override
        public String toString(){
            return "Score:" + this.m_score + "\n" + this.m_message.toString();
        }

    public int compareTo(SearchHit o) {
        return Double.compare(m_score, o.getScore());
    }
}
