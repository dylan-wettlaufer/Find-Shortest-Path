
/** This class represents a double linked priority queue. The priority queue consists of nodes and are organized in non decreasing order of priority  */

public class DLPriorityQueue <T> implements  PriorityQueueADT<T> {
	
	/** represents the front of the queue */
	private DLinkedNode<T> front;
	/** represents the rear of the queue */
	private DLinkedNode<T> rear;
	/** the number of nodes in the queue */
	private int count;
	
	/** constructor that creates an empty priority queue */
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}
	
	/** adds the node with the given data item and priority into the queue. The position of the node is based off the priority as the queue is sorted in non decreasing order of priority */
	public void add(T dataItem, double priority) {
		
		DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority); // creates a new node given the data item and priority
		
		if (front == null) { // if the queue is empty the front and rear become the new node
			front = newNode;
			rear = newNode;
		}
		// if the new nodes priority is grater than the fronts priority, then the node is somewhere in the middle of the list
		else if (newNode.getPriority() > front.getPriority()) {
			DLinkedNode<T> current = front;
			while (current != null && current.getPriority() < newNode.getPriority()) { // loop to find the node that new node will come before
				current = current.getNext();
			}
			if (current == null) { // if current equals null, then that means the new nodes priority is greater than the rest of the list so the new node becomes the rear
				rear.setNext(newNode);
				newNode.setPrev(rear);
				rear = newNode;
			}
			else { // the new node is placed into the list based off of which node current is
				newNode.setNext(current);
				newNode.setPrev(current.getPrev());
				current.getPrev().setNext(newNode);
				current.setPrev(newNode);		
			}
		}
		
		// if the new node's priority is equal or less then the front, that means the new node is the front of the list
		else if (newNode.getPriority() == front.getPriority() || newNode.getPriority() < front.getPriority()) {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
			
		}
		
		count++; // since a node was added the count of the queue is increased 
	}
	
	/** updates the priority of a given data item in the priority queue. This is done be removing the data item with the new priority and re adding it the queue with its new priority */
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		DLinkedNode<T> current = front;
		DLinkedNode<T> pred = null;
	
		while (current != null && !current.getDataItem().equals(dataItem)) { // loops through the linked list to find the data item
			pred = current;
			current = current.getNext();	
		}
		
		if (current == null) throw new InvalidElementException("Data item not in priority queue"); // if the data item is not in the list, an exception is thrown
		else current.setPriority(newPriority); // sets the specific data items priority to the new priority 
		
		DLinkedNode<T> newNode = current; // new variable to hold the node with the new priority 
		
		if (pred != null) {
			
			if (current.getNext() == null) { // if the node we wont to remove is the rear, then the new rear becomes the node previous to it
				rear = rear.getPrev();
			}
			else { // removes the node in the middle of the list using the current and previous nodes
				pred.setNext(current.getNext());
				current.getNext().setPrev(pred);
			}
			
		}
		// if predecessor is null then current is the front of the list so to remove the node
		else front = front.getNext();
		
		count--; // since a node was removed, the count is decreased by one
		
		add(newNode.getDataItem(), newNode.getPriority()); //adds the node with the new priority to the list, causing the node to be placed in its new correct position based off of its new priority
		
	}
	/** Removes and returns the data item with the smallest priority from the queue */
	public T removeMin() throws EmptyPriorityQueueException {
		
		if (front == null) throw new EmptyPriorityQueueException("Priority queue is empty");
		
		T smallest = front.getDataItem(); // new variable of type T to return the data item that is being removed
		front = front.getNext(); // removes the data item at the front by setting front to the node next to front
		if (front == null) rear = null; // if front equals null then the queue is empty and rear must be set to null as well
		count--; //since a node was removed, the count is decreased by one
		
		return smallest;
	}

	/** returns true if the priority queue is empty */
	public boolean isEmpty() {
		return (count == 0);
	}
	
	/** returns the size of the priority queue */
	public int size() {
		return count;
	}
	
	/** returns a string representation of a priority queue */
	public String toString() {
		String print = "";
		DLinkedNode<T> current = front;
		
		while(current != null) {
			print = print + current.getDataItem(); // adds the data item to the string
			current = current.getNext();
		}
		return print;
	}
	
	/** returns the rear of the linked list */
	public DLinkedNode<T> getRear() {
		return rear;
	}
	
	
	
}
