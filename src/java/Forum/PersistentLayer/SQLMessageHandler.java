/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.PersistentLayer;

import Forum.PersistentLayer.Data.MessageData;
import Forum.PersistentLayer.Interfaces.XMLMessageInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Arseny
 */
public class SQLMessageHandler implements XMLMessageInterface {









    public SQLMessageHandler() {

    }

     private Message findMessage(int messageID) {
                  Message   oneMessage = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
        Query q = session.createQuery ("from Message as message where message.number is '"+messageID+"'");
        oneMessage = (Message) q.uniqueResult();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
                    // Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    // add logging
                }
                // throw again the first exception
                throw e;
            }
        }
        return oneMessage;
     }


    public MessageData getMessage(int messageID)    {
      Message m = findMessage(messageID);
      if  (m != null) {
      //  for(MessageType m : this.xf.getForum().getMessages()){
    //            if(m.getMessageId().intValue() == messageID){
                    String nick=m.getAuthor();
                    String sub=m.getTitle();
                    String body = m.getBody();
                    Date created=m.getDateOfAdd();
                    Date modified =m.getDateOfEdit();
                    int indexId = m.getNumber();
                    //****************************************//
                    // changed for getIndex   ****************/
                    //the next line is the old constractor;
                    //   return new MessageData(nick, sub, body, created, modified);
                    return new MessageData(nick, sub, body, created, modified,indexId);
   //             }
            }
           return null;
    }

    public List<Integer> getRepliesIds(int messageID) {
        List<Integer> listReplies =  new ArrayList<Integer>();
          List< Message>    MeseggesList = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
        Query q = session.createQuery ("from Message as message where  message.idFather is  '"+messageID+"'");
        MeseggesList = (List<Message>) q.list();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                try {
                    // Second try catch as the rollback could fail as well
                    tx.rollback();
                } catch (HibernateException e1) {
                    // add logging
                }
                // throw again the first exception
                throw e;
            }
        }


      if (MeseggesList != null)
                 for (Message  m :MeseggesList){
                 if (m != null)
                   listReplies.add(new Integer(m.getNumber()));
             }
                  return (listReplies);
      }
}
      

    


 
