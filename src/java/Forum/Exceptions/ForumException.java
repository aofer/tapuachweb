package Forum.Exceptions;
public abstract class ForumException extends Exception{
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
