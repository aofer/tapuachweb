/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.Interfaces.MemberInterface;
import Forum.DomainLayer.Member;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author amit
 */
public class OnlineMembersMessage extends ClientMessage {

        @Override
    public ServerResponse doOperation(ForumFascade forum) {
        Vector<MemberInterface> members=forum.getOnlineMembers();
        String str="";
        for (Iterator<MemberInterface> it = members.iterator(); it.hasNext();) {
            MemberInterface member = it.next();
            str+= member.getUserName() +" , ";
        }
        if (str.length() > 2){
            str = str.substring(0, str.length() - 2);
        }
        return new ServerResponse(str, true);
    }
}
