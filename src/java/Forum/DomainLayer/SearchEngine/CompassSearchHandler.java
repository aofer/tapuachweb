/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Forum;
import Forum.DomainLayer.Interfaces.MessageInterface;
import Forum.DomainLayer.Logger.TapuachLogger;
import Forum.DomainLayer.Message;
import Forum.Exceptions.MessageNotFoundException;
import Forum.PersistentLayer.Data.MessageData;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;

/**
 *
 * @author Arseny
 * 
 */
public class CompassSearchHandler implements SearchEngineInterface {

    public synchronized void addMessage(MessageInterface msg) {
        if (msg.getBody().equals(""))
            return;
        CompassSession cs = CompassInstance.getInstance();
        cs = CompassInstance.open();
        cs.beginTransaction();
               cs. save(msg);

          cs.commit();
          
    }

    public synchronized void updateMessage(MessageInterface msg) {
                if (msg.getBody().equals(""))
            return;
        CompassSession cs = CompassInstance.getInstance();
        cs = CompassInstance.open();

        cs.beginTransaction();
               cs. save(msg);

          cs.commit();
          
    }

    public synchronized void removeMessage(MessageInterface msg) {
                  CompassSession cs = CompassInstance.getInstance();
                  cs = CompassInstance.open();
        cs.beginTransaction();
               cs. delete(msg);

          cs.commit();
          

    }

    public synchronized SearchHit[] searchByAuthor(String username, int from, int to) {
        CompassSession cs = CompassInstance.getInstance();
        cs = CompassInstance.open();
       cs.beginTransaction();
        CompassHits hits =  cs.find("author:\"" + username  + "\"");
      
        CompassHit[] detachedHits = hits.detach(from, to).getHits();
        SearchHit[] sh = new SearchHit[detachedHits.length];
        for(int i = 0; i<detachedHits.length;i++){
            
                sh[i] = new SearchHit(((MessageInterface)detachedHits[i].data()), detachedHits[i].score());
                     
        }
         cs.commit();
        
        return sh;
    }

    
    public synchronized SearchHit[] searchByContent(String phrase, int from, int to) {
        CompassSession cs = CompassInstance.getInstance();
        cs = CompassInstance.open();
        cs.beginTransaction();
        CompassHits hits =  cs.find("content: " + phrase + " OR subject:" + phrase );
        CompassHit[] detachedHits = hits.detach(from, to).getHits();
        SearchHit[] sh = new SearchHit[detachedHits.length];
        for(int i = 0; i<detachedHits.length;i++){
             sh[i] = new SearchHit(((MessageInterface)detachedHits[i].data()), detachedHits[i].score());
        }
         cs.commit();
        return sh;
    }


}
