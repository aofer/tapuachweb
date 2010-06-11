/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.PersistentLayer.Data;

import java.util.Date;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;


/**
 * Contain all the data that a Domain class Message needed from the PersistentLayer
 */
@Searchable(root = false)
public class MessageData {
@SearchableProperty(name = "author")
    private String _nickname;
@SearchableProperty(name = "subject")
private String _subject;
@SearchableProperty(name = "content")
private String _body;
 @SearchableProperty
private Date _writeDate;
 @SearchableProperty
 private Date _modifiedDate;

 @SearchableId
 private int _id;

 public MessageData(){
     
 }

 public MessageData(String nickname , String subject,String body,Date writeDate,Date modifedDate)
 {
     this._nickname=nickname;
     this._subject=subject;
     this._body=body;
     this._writeDate=writeDate;
     this._modifiedDate=modifedDate;
 }

                     //****************************************//
                    // changed for getIndex   ****************/
 // new constractor
  public MessageData(String nickname , String subject,String body,Date writeDate,Date modifedDate, int indexId)
 {
     this._nickname=nickname;
     this._subject=subject;
     this._body=body;
     this._writeDate=writeDate;
     this._modifiedDate=modifedDate;
     this._id = indexId;
 }

    /**
     * @return the _nickname
     */
    public String getNickname() {
        return _nickname;
    }

    /**
     * @return the _subject
     */
    public String getSubject() {
        return _subject;
    }

    /**
     * @return the _body
     */
    public String getBody() {
        return _body;
    }
    /**
     * @return the _writeDate
     */
    public Date getWriteDate() {
        return _writeDate;
    }

    /**
     * @return the _modifiedDate
     */
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this._subject = subject;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this._body = body;
    }

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @return id to set - should not be used!!! - used only for tests.
     */
    public void setId(int id) {
       this._id = id;
    }
}
