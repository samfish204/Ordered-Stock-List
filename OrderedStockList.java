import java.util.Comparator;
import java.util.Iterator;

/*
 * Samuel Fisher
 * Program 3
 * Dr. Kiper
 * October 12, 2020
 * OrderedStockList class
 * creates a list of stock objects 
 * and orders them based off of the 
 * StockComparator
 * Also adds and iterator and a method to 
 * insert into the stock list. 
 */
public class OrderedStockList {

	private int size;
	private Node front;
	private Node rear;

	private Iterator<Stock> iter;
	private Comparator<Stock> comp;

	public OrderedStockList(Comparator<Stock> compChoice) {
		size = 0;
		front = rear = null;
		comp = compChoice;
	}

	public Node getFront() {
		return front;
	}

	public Node getRear() {
		return rear;
	}

	public int getSize() {
		return size;
	}

	// This method insert the item into the list in the order
	// specified by the Comparator comp
	public void insert(Stock item) {

		Node current = front;
		Node prev = null;

		Node node = new Node(item);
		if (size == 0) {
			front = rear = node;
			size++;
			return;
		}
		if (size == 1) { // prev == null
			if (comp.compare(current.getData(), item) > 0) { // insert before front
				node.setNext(current);
				node.setPrev(null);
				current.setPrev(node);
				front = node;
			} else { // insert after front
				front.setNext(node);
				node.setPrev(front);
				rear = node;
			}
			size++;
			return;
		}
		// list size 2 or bigger
		while (current != null) {
			if (comp.compare(current.getData(), item) < 0) {
				prev = current;
				current = current.getNext();
			} else { // insert before this node
				if (prev == null) {
					node.setNext(front);
					front.setPrev(node);
					front = node;
				} else {
					node.setPrev(prev);
					node.setNext(current);
					prev.setNext(node);
					current.setPrev(node);
				}
				size++;
				return;
			}
		}

		// insert at end of list
		rear.setNext(node);
		node.setPrev(rear);
		rear = node;
		size++;
	}

	// returns whether the stock list is empty or not
	public boolean isEmpty() {
		return size == 0;
	}

	// return size of ordered stock list
	public int size() {
		return size;
	}

	// Override the toString() method to display each stock on a line
	@Override
	public String toString() {
		String result = "";
		iter = iterator();
		
		// Add code to create the string result from this list.
		// Use the iterator iter to traverse the list.
		Stock stox = null;
		
		while (iter.hasNext()) {
			stox = iter.next();
			result += stox.toString() + "\n";
		}
		return result;
	}

	// iterator for stock list
	Iterator<Stock> iterator() {

			// Add the methods needed for a standard iterator
		return new Iterator() {
			Node nextNode = front;
			
			@Override
			public boolean hasNext() {
				return !(nextNode == null);
			}
			
			@Override
			public Object next() {
				Stock result = null;
				String symbol; String name; String date;
				double price; int volume;
				if (hasNext()) {
					//result = nextNode.getData().;
					symbol = nextNode.getData().getSymbol();
					name = nextNode.getData().getStockName();
					date = nextNode.getData().getDate();
					price = nextNode.getData().getClosingPrice();
					volume = nextNode.getData().getVolume();
					result = new Stock(symbol, name, date, price, volume);
					nextNode = nextNode.getNext();
				}
				return result;
			}
		};
	}
}
