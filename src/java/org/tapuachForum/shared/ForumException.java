package org.tapuachForum.shared;

import java.io.Serializable;



public abstract class ForumException extends Exception implements Serializable{
    private  String message;
    public ForumException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String s) {
        message = s;
    }
}
