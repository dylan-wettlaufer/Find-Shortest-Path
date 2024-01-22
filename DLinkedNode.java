/** This class represents a node in a double linked list. Each node has a data item, a priority, and a next and previous pointer */

public class DLinkedNode <T> {
	/** Data item of the node */
	private T dataItem;
	/**Priority of the node*/
	private double priority;
	/** next and previous pointers of the node */
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;
	
	/** constructor that sets the data item and priority as the parameters. */
	public DLinkedNode(T data, double prio) {
		dataItem = data;
		priority = prio;
		next = null;
		prev = null;
	}
	
	/** Constructor that creates a node with default data item and priority */
	public DLinkedNode() {
		dataItem = null;
		priority = 0;
		next = null;
		prev = null;
	}
	
	/** getter that returns the priority */
	public double getPriority() {
		return priority;
	}
	
	/** getter that returns the data item */
	public T getDataItem() {
		return dataItem;
	}
	
	/** getter that returns the next node in the linked list */
	public DLinkedNode<T> getNext() {
		return next;
	}
	
	/** getter that returns the previous node in the linked list */
	public DLinkedNode<T> getPrev() {
		return prev;
	}
	
	/** Setter that sets a new value for the node's priority */
	public void setPriority(double prio) {
		priority = prio;
	}
	
	/** setter that sets a new value for the nodes data item */
	public void setDataItem(T data) {
		dataItem = data;
	}
	
	/** setter that sets the node that is next  */
	public void setNext(DLinkedNode<T> next) {
		this.next = next;
	}
	
	/** setter that sets the node that is previous */
	public void setPrev(DLinkedNode<T> prev) {
		this.prev = prev;
	}
	

}
