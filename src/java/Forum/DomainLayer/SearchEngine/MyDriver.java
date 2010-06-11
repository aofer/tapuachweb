/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.DomainLayer.SearchEngine;

import org.apache.lucene.store.jdbc.dialect.MySQLDialect;

/**
 *
 * @author Arseny
 */
public class MyDriver extends MySQLDialect {

    @Override
    public String openBlobSelectQuote() {
return "`";
}

    @Override
public String closeBlobSelectQuote() {
return "`";
}

}
