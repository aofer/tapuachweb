/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.TCPCommunicationLayer;

import Forum.DomainLayer.ForumFascade;
import Forum.DomainLayer.Member;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Kipi
 */
public class MembersMessage extends ClientMessage {

    @Override
    public ServerResponse doOperation(ForumFascade forum) {
        List<Member> members=forum.getMembers();
        String str="";
        for (Member m : members) {
            
            str+= m.getUserName() + ";";
        }
        return new ServerResponse(str, true);
    }

}
