package Queue.exceptions;

public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(){
        super("Can't remove element from empty Queue");
    }
}
