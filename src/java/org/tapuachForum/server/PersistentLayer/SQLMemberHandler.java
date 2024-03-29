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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Nir
 */
public class SQLMemberHandler implements XMLMemberInterface {

    /**
     *
     * @return
     */
    public SQLMemberHandler() {
    }

    public List<MemberData> geOnlinetMembers() {
        List<MemberData> members = new ArrayList<MemberData>();
        List<Members> MembersList = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Members as members where members.isLogin = true");
            MembersList = (List<Members>) q.list();
            session.close();
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

        if (MembersList != null) {
            for (Members m : MembersList) {
                Date joined = m.getDateOfJoin();
                Date birth = m.getDateOfJoin();
                members.add(new MemberData(m.getUserName(), m.getNickName(), m.getPassword(),
                        m.getFirstName(), m.getLastName(), m.getEmail(), joined, birth, m.isIsLogin()));
            }
        }
        return members;
    }

    public List<MemberData> getMember() {
        List<MemberData> members = new ArrayList<MemberData>();
        List<Members> MembersList = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Members as members");
            MembersList = (List<Members>) q.list();
            session.close();
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

        for (Members m : MembersList) {
            Date joined = m.getDateOfJoin();
            Date birth = m.getDateOfJoin();
            members.add(new MemberData(m.getUserName(), m.getNickName(), m.getPassword(),
                    m.getFirstName(), m.getLastName(), m.getEmail(), joined, birth, m.isIsLogin()));
        }
        return members;
    }

    public MemberData getMember(String userName) {
        Members oneOfMembers = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Members as members where members.userName is '" + userName + "'");
            oneOfMembers = (Members) q.uniqueResult();
            session.close();
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

        if (oneOfMembers != null) {
            Date joined = oneOfMembers.getDateOfJoin();
            Date birth = oneOfMembers.getDataOfBirth();
            return new MemberData(oneOfMembers.getUserName(), oneOfMembers.getNickName(), oneOfMembers.getPassword(),
                    oneOfMembers.getFirstName(), oneOfMembers.getLastName(), oneOfMembers.getEmail(), joined, birth, oneOfMembers.isIsLogin());
        }

        return null;
    }

    public eMemberType getMemberType(String userName) {
        Members oneOfMembers = null;
        Transaction tx = null;
        Session session = SessionFactoryUtil.getInstance().getCurrentSession();
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Members as members where members.userName is '" + userName + "'");
            oneOfMembers = (Members) q.uniqueResult();
            session.close();
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
        if (oneOfMembers != null) {
            if (oneOfMembers.isIsAdmin()) {
                return (eMemberType.Admin);
            } else if (oneOfMembers.isIsModerator()) {
                return (eMemberType.Moderator);
            } else {
                return eMemberType.member;
            }

        }

        return (null);
    }
}


