import java.util.Iterator;

/*
 * Samuel Fisher
 * Program 3
 * Dr. Kiper
 * October 12, 2020
 * create a stockList iterator to move through a stock list
 */
public class StockListIterator implements Iterator<Stock> {

	// Add code to implement an iterator for Stocks.
	// This will be similar to the iterator in OrderedStockList
	// with a few small changes.
	OrderedStockList stocks;
	
	Node nextNode;
	
	public StockListIterator(OrderedStockList stox) {
		nextNode = stox.getFront();
	}
	
	public boolean hasNext() {
		return nextNode != null;
	}
	
	public Stock next() {
		Stock result = null;
		
		if (hasNext()) {
			result = nextNode.getData();
			nextNode = nextNode.getNext();
		}
		
		return result;
	}
	
	
}