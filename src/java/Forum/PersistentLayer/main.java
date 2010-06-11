/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.PersistentLayer;

/**
 *
 * @author Arseny
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.util.GregorianCalendar;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {

			JAXBContext jc = JAXBContext.newInstance("Forum.PersistentLayer");
                        ObjectFactory factory = new ObjectFactory();
			//Unmarshaller u = jc.createUnmarshaller();

			//in = new FileInputStream("tapuachforum.xml");

			// Obtain the data from the XML file.
	ForumType f= factory.createForumType(); //(ForumType)u.unmarshal(in);
			//in.close();

			//System.out.println(f.getForumName());
			

                        // Create a factory.
			


            System.out.println("WriteToXML");
            XMLFileHandler instance = new XMLFileHandler("tapuachforum.xml");

            
            MemberType m1 = factory.createMemberType();
            m1.setUserName("nir");
            m1.setNickName("arsenik");
            m1.setPassword("123456");
            m1.setFirstName("a");
            m1.setLastName("k");
            m1.setEMail("a@a.com");
            GregorianCalendar gcal = new GregorianCalendar();
            XMLGregorianCalendar xgcal;
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            m1.setDateJoined(xgcal);
            m1.setDateOfBirth(xgcal);
                 MemberType m2 = factory.createMemberType();
            m2.setUserName("alex");
            m2.setNickName("superMan");
            m2.setPassword("123456");
            m2.setFirstName("a");
            m2.setLastName("k");
            m2.setEMail("a@a.com");
            m2.setStatus(true);
            MessageType e = factory.createMessageType();
            e.setParentId(BigInteger.valueOf(0));
            e.setMessageId(BigInteger.valueOf(1));
            e.setCreatedBy("nir");
            e.setSubject("hello potatoheads..");
            e.setBody("hello everyone...");
            e.setDateAdded(xgcal);
            e.setModifiedDate(xgcal);
            f.getMembers().add(m1);
               f.getMembers().add(m2);
               f.setMessageCounter(BigInteger.valueOf(1));
            f.getMessages().add(e);
            instance.WriteToXML();


		} catch (JAXBException e) {
			e.printStackTrace();
		}/* catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
                catch (DatatypeConfigurationException ex) {
               //todo
            }
		finally {
			System.exit(0);
		}
	}

}
