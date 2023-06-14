package Queue;

import Queue.exceptions.EmptyQueueException;
import Queue.exceptions.FullQueueException;

/*
  * Interface for queue data structure
 
  * It is a FIFO
  * insertion takes place at end
  * deletion or removal of the element takes place from front
*/

public interface IQueue{
    
    void enQueue(int element) throws FullQueueException;
    int deQueue() throws EmptyQueueException;

    int front();
    int rear();
    boolean isEmpty();
    int size();
}