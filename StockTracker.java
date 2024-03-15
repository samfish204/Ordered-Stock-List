import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Samuel Fisher
 * Program 3
 * Dr. Kiper
 * October 12, 2020
 * main method to run stocks through. 
 * add elements into stock list based 
 * on command line arguments.
 * Return minimum price of stock, 
 * return totalValue for a specific day, 
 * return maximum totalValue increase for a stock
 */
public class StockTracker {
	
	public static void main(String[] args) throws FileNotFoundException {

		Comparator<Stock> comp = new StockComparator<Stock>();

		OrderedStockList stocks = new OrderedStockList(comp);
		
		try {
			FileReader fin = new FileReader(args[0]);
			Scanner filePtr = new Scanner(fin);
			filePtr.useDelimiter("[,\\n]"); // delimiters are commas, line feeds

			Scanner scan = new Scanner(System.in);
			// read the file and add values to stocks.
			// Use next() to read the strings, nextDouble() to read the price
			// and nextInt() to read the volume
			double price; int volume;
			String name; String symbol; String date;
		
			Stock stockPtr; 
		
			while(filePtr.hasNextLine() == true) {
				name = filePtr.next();
				symbol = filePtr.next();
				date = filePtr.next();
				price = filePtr.nextDouble();
				volume = filePtr.nextInt();
				stockPtr = new Stock(symbol, name, date, price, volume);
				stocks.insert(stockPtr);
			}
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		System.out.print(stocks.toString());
	}
	
	// This method returns the minimum price for a given stock 
	// over all of there dates
	static double minPrice(OrderedStockList stocks, String symbol) {
		double result = Integer.MAX_VALUE;
		
		Node nodePtr = stocks.getFront();
		Stock stockPtr = nodePtr.getData();
		
		while (nodePtr.getNext() != null) {
			if (stockPtr.getSymbol().compareTo(symbol) == 0) {
				if (stockPtr.getClosingPrice() < result) {
					result = stockPtr.getClosingPrice();
				}
				nodePtr = nodePtr.getNext();
				stockPtr = nodePtr.getData();
			} else {
				nodePtr = nodePtr.getNext();
				stockPtr = nodePtr.getData();
			}
		}
		
		return result;
	}
	
	// This method return the total value of all stocks in this that were sold 
	// on date.  This is the closing price time the volume.
	static double totalValueForDay( OrderedStockList stocks, String date) {
		double result = 0;
		double price = 0;
		int volume = 0;
		
		Node nodePtr = stocks.getFront();
		Stock stockPtr = nodePtr.getData();
		
		while (nodePtr.getNext() != null) {
			if (stockPtr.getDate().compareTo(date) == 0) {
				price = stockPtr.getClosingPrice();
				volume = stockPtr.getVolume();
				
				result += price * volume;
				
				nodePtr = nodePtr.getNext();
				stockPtr = nodePtr.getData();
			} else {
				nodePtr = nodePtr.getNext();
				stockPtr = nodePtr.getData();
			}
		}
		//result = price * volume;
		return result;
	}
	
	// This method finds the maximum increase from one day to the next 
	// for this stock for all of the days in the list
	static double maxIncrease( OrderedStockList stocks, String symbol) {
		double result = Integer.MIN_VALUE;
		
		Node nodePtr = stocks.getFront();
		Node nodePtr2 = nodePtr.getNext();
		Stock stockPtr = nodePtr.getData();
		Stock stockPtr2 = nodePtr2.getData();
		
		while (nodePtr2.getNext() != null) {
			if (stockPtr.getSymbol().contentEquals(symbol)) {
				if (stockPtr2.getSymbol().contentEquals(symbol)) {
					if ((stockPtr2.getClosingPrice() * stockPtr2.getVolume() ) - (stockPtr.getClosingPrice() * stockPtr.getVolume()) > result) {
						result = ( (stockPtr2.getClosingPrice() * stockPtr2.getVolume() ) - (stockPtr.getClosingPrice() * stockPtr.getVolume()) );
					}
					nodePtr = nodePtr.getNext();
					nodePtr2 = nodePtr2.getNext();
					stockPtr = nodePtr.getData();
					stockPtr2 = nodePtr2.getData();
				} else {
					nodePtr2 = nodePtr2.getNext();
					stockPtr2 = nodePtr2.getData();
				}
			} else {
				nodePtr = nodePtr.getNext();
				nodePtr2 = nodePtr2.getNext();
				stockPtr = nodePtr.getData();
				stockPtr2 = nodePtr2.getData();
			}
		}
		return result;
	}
}
