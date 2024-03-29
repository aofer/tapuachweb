/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tapuachForum.server.PersistentLayer;

import org.tapuachForum.shared.MemberData;
import org.tapuachForum.server.PersistentLayer.Interfaces.XMLMemberInterface;
import org.tapuachForum.shared.eMemberType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.swing.text.StyledEditorKit.BoldAction;
//import sun.font.CreatedFontTracker;

/**
 *
 * @author Nir
 */
public class XMLMemberHandler implements XMLMemberInterface {

    /**
     *
     * @return
     */
    private XMLFileHandler xf;

    public XMLMemberHandler(XMLFileHandler xf) {
        this.xf = xf;
    }

    public List<MemberData> getMember() {
        List<MemberData> members = new ArrayList<MemberData>();

        for (MemberType m : this.xf.getForum().getMembers()) {
            Date joined = m.getDateJoined().toGregorianCalendar().getTime();
            Date birth = m.getDateOfBirth().toGregorianCalendar().getTime();
            members.add(new MemberData(m.getUserName(), m.getNickName(), m.getPassword(),
                    m.getFirstName(), m.getLastName(), m.getEMail(), joined, birth));
        }
        return members;
    }

    public MemberData getMember(String userName) {
        for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(userName)) {
                Date joined = m.getDateJoined().toGregorianCalendar().getTime();
                Date birth = m.getDateOfBirth().toGregorianCalendar().getTime();
                return new MemberData(m.getUserName(), m.getNickName(), m.getPassword(),
                        m.getFirstName(), m.getLastName(), m.getEMail(), joined, birth);
            }
        }
        return null;
    }

    public eMemberType getMemberType(String userName) {
        for (MemberType m : this.xf.getForum().getMembers()) {
            if (m.getUserName().equals(userName)) {
                if (m.isAdmin) {
                    return (eMemberType.Admin);
                } else if (m.isModerator) {
                    return (eMemberType.Moderator);
                } else {
                    return eMemberType.member;
                }
            }
        }
        return (null);
    }

    public List<MemberData> geOnlinetMembers() {
        throw new UnsupportedOperationException("Not supported yet.");
        // Bob will be back after he will finish to BUILD the Forum.
    }
}


