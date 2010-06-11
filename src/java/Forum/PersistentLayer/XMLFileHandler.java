/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.PersistentLayer;

import Forum.DomainLayer.Forum;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Arseny
 */
public class XMLFileHandler {

    private ObjectFactory of;
    private JAXBContext jc;
    private ForumType f;
    private String fileName;

    public XMLFileHandler(String fileName) {
        this.of = new ObjectFactory();
        try {
            this.jc = JAXBContext.newInstance("Forum.PersistentLayer");
        } catch (JAXBException ex) {
            Logger.getLogger(XMLFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fileName = fileName;
        this.f = ReadFromXML();
    }

    public ForumType ReadFromXML() {
        ForumType forum = null;
        FileInputStream in = null;
        try {
            Unmarshaller u = jc.createUnmarshaller();
            in = new FileInputStream(this.fileName);
            forum = (ForumType) u.unmarshal(in);
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return forum;
    }

    public void WriteToXML() {
        {
            FileOutputStream out = null;
            try {
                Marshaller m = jc.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                out = new FileOutputStream(fileName);
                m.marshal(f, out);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(XMLFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ForumType getForum() {
        return f;
    }
}
