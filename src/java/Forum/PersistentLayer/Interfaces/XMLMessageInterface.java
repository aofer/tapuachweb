package Forum.PersistentLayer.Interfaces;
import Forum.PersistentLayer.Data.MessageData;
import java.util.List;

/**
*
* @author Liron Katav
*/
public interface XMLMessageInterface {
	/**
         * getting a messageData object from the persistent layer by giving the messageId of the message we need
         * @param messageID - the id of the message we need
         * @return a message data object holding the data of the message we need
         */
        MessageData getMessage(int messageID);
        /**
         * returns a list containing all the messageIds of the message's direct replies, meaning
         * all of the message's level 1 replies.
         * @param messageID - the id of the message we want to get its replies
         * @return - a list of Integers representing the messageIds of all the first level replies
         */
        List<Integer> getRepliesIds(int messageID);
	}
