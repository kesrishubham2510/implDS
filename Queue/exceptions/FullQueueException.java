package Queue.exceptions;

public class FullQueueException extends RuntimeException{
 public FullQueueException(){
        super("Can't insert element in a Queue that's already full");
    }
}
