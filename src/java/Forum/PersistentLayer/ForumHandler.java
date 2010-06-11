/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.PersistentLayer;

import Forum.Exceptions.MessageNotFoundException;
import Forum.Exceptions.UserNotExistException;
import Forum.PersistentLayer.Interfaces.ForumHandlerInterface;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import Forum.Exceptions.*;

/**
 *
 * @author Nir
 */
public class ForumHandler  implements  ForumHandlerInterface{

    /**
     *
     * @return
     */


           private XMLFileHandler xf;

           /**
            *
            * @param xf
            */
           public ForumHandler(XMLFileHandler xf) {
        this.xf = xf;
    }
      	/**
     * set the status of the user to be online
         * @param username
         */
    public void login(String username) {
        for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                m.setStatus(true);
                break;
            }
       }
             xf.WriteToXML();
    }
	/**
     * set the status of the user to be offline
     * @param username a message id */
    public void logoff(String username) {
         for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                m.setStatus(false);
                xf.WriteToXML();
                break;
            }
       }
    }

    // this function is helping to add ONE to the number of messeages in the forum
    private int getCounter(){
        int ans = this.xf.getForum().getMessageCounter().intValue();
        int tmp = ans +1;
        this.xf.getForum().setMessageCounter(BigInteger.valueOf(tmp));
        return ans;
    }
   	/**
     * check if the username already exist
     * @param username
     * @return username password if exists or NULL if not*/
    public String userExist(String username) {
                           for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                return m.getPassword();
            }
        }
          return null;
        }

    // this function giving back member by his userName
    private MemberType findMember(String username){
                         for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                return m;
            }
        }
          return null;
        }

     // this function giving back   Message from the main level of the tree if exist. Null outher wise;
     // it is not in use!
         private MessageType findMessageAtMain(int messageID) {

             for (MessageType m : this.xf.getForum().getMessages()) {
                     System.out.println( messageID);
                   if(m.getMessageId().intValue() == messageID)
                       return m;
                 }

                 return null;
        }


      // this function giving back   Message from the tree if exist. Null outher wise;
       // it is using the next helf-recorsivic function.
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


        /**
     * check if the username already exist
         * @param messageID
         * @return username password if exists or NULL if not*/

        public String getSubject(int messageID)  {
            MessageType Aba = findMessage(messageID);
          if (Aba != null)
            return  Aba.getSubject();
          else
            return ("not such messe");
            }

    	/**
     * add a new member to the forum
         * @param userName
         * @param nickName
         * @param password
         * @param eMail
         * @param firstName
         * @param lastName
         * @param dateOfBirth
         */
    public void register(String userName, String nickName, String password, String eMail, String firstName, String lastName, Date dateOfBirth) {
        try {
            ObjectFactory factory = new ObjectFactory();
            // Create a new member
            MemberType newMember = factory.createMemberType();
            newMember.setUserName(userName);
            newMember.setNickName(nickName);
            newMember.setPassword(password);
            newMember.setEMail(eMail);
            newMember.setFirstName(firstName);
            newMember.setLastName(lastName);
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(dateOfBirth);
            XMLGregorianCalendar xgcal, xgcal2;
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            newMember.setDateOfBirth(xgcal);

            Date nowT = new Date();
            gcal.setTime(nowT);

            xgcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            newMember.setDateJoined(xgcal2);
            //    newMember.setDateOfBirth(null);
            this.xf.getForum().getMembers().add(newMember);

        xf.WriteToXML();
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ForumHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
     * add a new message to the forum
         * @param parentId
         * @param createdBy
         * @param DateAdded
         * @param subject
         * @param body
         */
public void addMessage(int parentId, String createdBy, String subject, String body, Date DateAdded) throws MessageNotFoundException {
        try {
            ObjectFactory factory = new ObjectFactory();
            // Create a new member
            MessageType newMessage = factory.createMessageType();
            newMessage.setParentId(BigInteger.valueOf(parentId));
            newMessage.setCreatedBy(createdBy);
            newMessage.setSubject(subject);
            newMessage.setBody(body);
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(DateAdded);
            XMLGregorianCalendar xgcal;
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            newMessage.setDateAdded(xgcal);
            newMessage.setModifiedDate(xgcal);
            newMessage.setMessageId(BigInteger.valueOf(getCounter()));
            //this.xf.getForum().getMessages().add(newMessage);

                if (parentId == 0)
                    this.xf.getForum().getMessages().add(newMessage);
                else
                {
                   MessageType Aba = findMessage(parentId);
                      if (Aba != null)
                                   Aba.getMessage().add(newMessage);
                      else
                         throw new MessageNotFoundException ("there is not such parent" + parentId);

                }

            

          //  MemberType userWriter = findMember(createdBy);
        //    userWriter.getMessage().add(newMessage);

            xf.WriteToXML();

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ForumHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkNickname(String nickname) {
                           for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getNickName().equals(nickname)) {
                return true;
            }
        }
          return false;
        };

    public boolean checkPassword(String password) {
                            for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getPassword().equals(password)) {
                return true;
            }
        }
          return false;
        };

    public int addMessage(int parentId, String createdBy, String subject, String body, Date DateAdded, Date modifyDate) {
        try {
            ObjectFactory factory = new ObjectFactory();
            // Create a new member
            MessageType newMessage = factory.createMessageType();
            newMessage.setParentId(BigInteger.valueOf(parentId));
            newMessage.setCreatedBy(createdBy);
            newMessage.setSubject(subject);
            newMessage.setBody(body);
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(DateAdded);
            XMLGregorianCalendar xgcal, xgcal2;
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            newMessage.setDateAdded(xgcal);

            gcal.setTime(modifyDate);
             xgcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            newMessage.setModifiedDate(xgcal2);
            BigInteger id = BigInteger.valueOf(getCounter());
            newMessage.setMessageId(id);

          // add messega to MAIN
            if (parentId == 0)
           this.xf.getForum().getMessages().add(newMessage);
          // add messega in TREE
          else{
              MessageType Aba = findMessage(parentId);
          if (Aba != null)
              Aba.getMessage().add(newMessage);
         // didnt find perent, put in MAIN
          else
               this.xf.getForum().getMessages().add(newMessage);
          }

  //               MemberType userWriter = findMember(createdBy);
//            userWriter.getMessage().add(newMessage);
                   xf.WriteToXML();
                           return id.intValue();

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ForumHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String userExists(String username) {
                               for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                return m.getPassword();
            }
        }
          return null;
        };



    public boolean checkUsername(String username) {
                                  for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(username)) {
                return true;
            }
        }
          return false;
        }


    public void editMessage(int messageId, String newSubject, String newBody, Date dateModified) {

        try {
            MessageType msg = findMessage(messageId);
            msg.setSubject(newSubject);
            msg.setBody(newBody);

            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(dateModified);
            XMLGregorianCalendar xgcal;
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            msg.setModifiedDate(xgcal);
              xf.WriteToXML();

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ForumHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     * @param messageId
     * @throws MessageNotFoundException
     */
    public void deleteMessage(int messageId) throws MessageNotFoundException {
            MessageType sonToRemove = findMessage(messageId);
            // find the messeage
             if (sonToRemove != null) {
                 // message is in main level
            if (sonToRemove.getParentId().intValue() == 0){
                this.xf.getForum().getMessages().remove(sonToRemove);
                int nowNum = this.xf.getForum().getMessageCounter().intValue();
            }
          // message is NOT inn main level
            else {
              MessageType Aba = findMessage(sonToRemove.getParentId().intValue());
           // find it somewhere
              if (Aba != null){
                  Aba.getMessage().remove(sonToRemove);
                       int nowNum = this.xf.getForum().getMessageCounter().intValue();
                // here was onde num--
              }
            // didn't find at all. BAD
              else
                    throw new MessageNotFoundException("There is son, but not perent");
           }
             }
             else
                 // didnt find the message
              throw new MessageNotFoundException("There is not son to delete. at no place!");
              xf.WriteToXML();
       }

    /**
     *
     * @param username
     * @throws UserNotExistException
     */
    public void upgradeUser(String username) throws UserNotExistException {
        MemberType member = findMember(username);
        if (member == null)
            throw new UserNotExistException();
        else{
            member.setIsModerator(true);
        xf.WriteToXML();
        }
    }

    /**
     *
     */

    // this is just help funtion. It is taking at exsisting forum and clean it;
    public void initForum(){
               this.xf.getForum().getMembers().removeAll(this.xf.getForum().getMembers());
               this.xf.getForum().getMessages().removeAll(this.xf.getForum().getMessages());
               this.xf.getForum().setForumName("Tapuach");
               this.xf.getForum().setMessageCounter(BigInteger.ONE);
    }
    // this function giving back member by his userName
    public boolean getStatus(String username){
            return    findMember(username).isStatus();
        }

  /* add a Admin to the forum
        * @param userName
        * @param nickName
        * @param password
        * @param eMail
        * @param firstName
        * @param lastName
        * @param dateOfBirth
        */
   public void registerAdmin(String userName, String nickName, String password, String eMail, String firstName, String lastName, Date dateOfBirth) {
       try {
           ObjectFactory factory = new ObjectFactory();
           // Create a new member
           MemberType newMember = factory.createMemberType();
           newMember.setUserName(userName);
           newMember.setNickName(nickName);
           newMember.setPassword(password);
           newMember.setEMail(eMail);
           newMember.setFirstName(firstName);
           newMember.setLastName(lastName);
           GregorianCalendar gcal = new GregorianCalendar();
           gcal.setTime(dateOfBirth);
           XMLGregorianCalendar xgcal, xgcal2;
           xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
           newMember.setDateOfBirth(xgcal);
           newMember.setIsAdmin(true);
           Date nowT = new Date();
           gcal.setTime(nowT);

           xgcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
           newMember.setDateJoined(xgcal2);
           //    newMember.setDateOfBirth(null);
         if (!checkUsername(userName)){
           this.xf.getForum().getMembers().add(newMember);

       xf.WriteToXML();
         }

       } catch (DatatypeConfigurationException ex) {
           Logger.getLogger(ForumHandler.class.getName()).log(Level.SEVERE, null, ex);
       }
   }


}
