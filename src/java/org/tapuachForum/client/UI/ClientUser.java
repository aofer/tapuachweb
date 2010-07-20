/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tapuachForum.client.UI;

import java.util.Date;
import org.tapuachForum.shared.Member;
import org.tapuachForum.shared.MemberData;
import org.tapuachForum.shared.eMemberType;
import org.tapuachForum.shared.MemberInterface;

/**
 *
 * @author Nir
 */
public class ClientUser   {
   public MemberInterface mem;
   public boolean isLogin;
   public static ClientUser Client;
    
  public static void setClient(){
      Client = new ClientUser();
  }

   public static ClientUser getClient(){
       return Client;
   }

   public static ClientUser getClient(MemberInterface loginMem){
       Client = new ClientUser(loginMem);
       return Client;
   }

 
  public ClientUser(){
     mem = new Member(new MemberData("guest", "d", "d"), eMemberType.guest);
     isLogin = false;
  }

 public ClientUser(MemberInterface loginMem){
     mem = loginMem;
     isLogin = true;
 }

//    public void editMessage(int messageId, String subject, String body) throws MessageNotFoundException, MessageOwnerException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    public Date getDateJoined() {
        return mem.getDateJoined();
    }

    public Date getDateOfBirth() {
       return mem.getDateOfBirth();
    }

    public String getEmail() {
    return mem.getEmail();
    }

    public String getFirstName() {
       return mem.getFirstName() ;
    }

    public String getLastName() {
     return mem.getLastName() ;
    }

    public String getNickName() {
        return mem.getNickName() ;
    }

    public String getPassword() {
        return mem.getPassword() ;
    }

    public String getUserName() {
        return mem.getUserName() ;
    }

    public void logOut() {
         mem.logOut() ;
    }
    
   public  eMemberType getType() {
        return mem.getType() ;
    }

//    public void writeMessage(String subject, String body) throws MessageNotFoundException, MessageOwnerException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void writeReply(int parentId, String subject, String body) throws MessageNotFoundException, MessageOwnerException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    

    public static boolean isLogin(){
       if (Client == null)
            return false;
        else
            return true;
    }
 }


