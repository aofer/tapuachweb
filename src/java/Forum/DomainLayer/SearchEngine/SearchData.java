/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Interfaces.MessageInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kipi
 */
public class SearchData implements SearchDataInterface {

    private HashMap<String, List<Integer>> _words;
    private HashMap<String, List<Integer>> _authors;

    public SearchData() {
        _words = new HashMap<String, List<Integer>>();
        _authors = new HashMap<String, List<Integer>>();
    }

    public void addMessage(MessageInterface msg) {
        String[] body = msg.getBody().split(" ");
        for (int i = 0; i < body.length; i++) {
            addWord(body[i], msg);
        }
        addAuthor(msg.getNickname(), msg);
    }

    public void removeMessage(MessageInterface msg) {
        removeValue(_words, msg.getIndex());
        removeValue(_authors, msg.getIndex());
    }

    public List<Integer> getByContent(String str) {
        return _words.get(str);
    }

    public List<Integer> getByAuthor(String author) {
        return _authors.get(author);
    }

  // that is the new version that Arsenik wrote.
       private void removeValue(HashMap<String, List<Integer>> hash, Integer value) {
         Set<String> ks = hash.keySet();
        for(String s : ks){
            List<Integer> ls = hash.get(s);
            ls.remove(value);
            hash.put(s, ls);
           }
    }

    private void addWord(String word, MessageInterface msg) {
        if (!_words.containsKey(word)) { //there is no such word
            _words.put(word, new ArrayList<Integer>()); //init the list of messages
        }
        _words.get(word).add(msg.getIndex()); //add the messgae index to the given word list
    }

    private void addAuthor(String author, MessageInterface msg) {
        if (!_authors.containsKey(author)) { //there is no such author
            _authors.put(author, new ArrayList<Integer>()); //init the list of messages
        }
        _authors.get(author).add(msg.getIndex()); //add the messgae index to the given authors list
    }
}
