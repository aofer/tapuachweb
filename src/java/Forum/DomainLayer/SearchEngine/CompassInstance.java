/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Forum.DomainLayer.SearchEngine;

import Forum.DomainLayer.Forum;
import java.io.File;
import org.compass.core.Compass;
import org.compass.core.CompassSession;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.CompassConfigurationFactory;

/**
 *
 * @author Arseny
 */
public class CompassInstance {


    private static Compass _compass;
    private static CompassSession _cs;

    /**
     * sort of Singleton design implementation
     * @return Compass instance if exist, or new if it doesn't
     */
    public static CompassSession getInstance() {
        if (_cs == null) {
            _compass = CompassInit();
            _cs = _compass.openSession();

        }
        return _cs;
    }

    public static void close(){
        _cs.close();
    }

    public static CompassSession open(){
        if(_cs.isClosed()){
            _cs = _compass.openSession();

        }
        return _cs;
    }

    /**
     * private constructor for the Singleton design
     */
    private CompassInstance() {
        _compass = null;
        _cs = null;
    }

    private static Compass CompassInit(){
        File file = new File("compassSettings.xml");
        /* The genData directory is where the search engine will save its data */
        CompassConfiguration conf = CompassConfigurationFactory
                .newConfiguration().configure(file);
         //conf.setSetting("compass.engine.store.jdbc.dialect", "Forum.DomainLayer.SearchEngine.MyDriver");
         


        Compass compass = conf.buildCompass();

        return compass;
    }
}
