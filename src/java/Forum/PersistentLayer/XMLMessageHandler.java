/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.PersistentLayer;

import Forum.PersistentLayer.Data.MessageData;
import Forum.PersistentLayer.Interfaces.XMLMessageInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Arseny
 */
public class XMLMessageHandler implements XMLMessageInterface {

    private XMLFileHandler xf;

    public XMLMessageHandler(XMLFileHandler xf) {
        this.xf = xf;
    }

     private MessageType findMessage(int messageID) {
                MessageType tryMesaage = null;
                 for (MessageType m : this.xf.getForum().getMessages()) {
                       tryMesaage  =  findMessageRec(messageID, m);
                       if (tryMesaage != null)
                           return tryMesaage;
                 }

                 return null;
        }

     // recursivis function to find Messeage;
        private MessageType findMessageRec(int messageID, MessageType messageAba) {
                 if(messageAba.getMessageId().intValue() == messageID)
                return messageAba;
                 else{
                MessageType tryMesaage = null;
                for (MessageType m : messageAba.getMessage()) {
                  tryMesaage =  findMessageRec(messageID, m);
                  if (tryMesaage != null)
                      return tryMesaage;
                }
                 }
             return null;
            }

    public MessageData getMessage(int messageID)    {
      MessageType m = findMessage(messageID);
      if  (m != null) {
      //  for(MessageType m : this.xf.getForum().getMessages()){
    //            if(m.getMessageId().intValue() == messageID){
                    String nick=m.getCreatedBy();
                    String sub=m.getSubject();
                    String body = m.getBody();
                    Date created=m.getDateAdded().toGregorianCalendar().getTime();
                    Date modified =m.getModifiedDate().toGregorianCalendar().getTime();
                    int indexId = m.getMessageId().intValue();
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

      if (messageID == 0)
      {
          
          for (MessageType m : this.xf.getForum().getMessages()){
              listReplies.add(new Integer(m.messageId.intValue()));
            }
            return (listReplies);
      }
      else{
               MessageType m2 = findMessage(messageID);
               if (m2 != null)
                 for (MessageType m :m2.getMessage()){
                 if (m != null)
                   listReplies.add(new Integer(m.messageId.intValue()));
             }
      }
      
          return (listReplies);
    }
}


 
