import java.util.Comparator;

/*
 * Samuel Fisher
 * Program 3
 * Dr. Kiper
 * October 12, 2020
 * implements a comparator to compare the stocks.
 */
public class StockComparator<T> implements Comparator<Stock> { 
	

	// Add the code for a comparator put the stock objects in order by date, 
	// from early to later. For those stock objects on the same date, 
	// you should put these in order by stock symbol.
	
	public int compare(Stock stox1, Stock stox2) {
		if (stox1.getDate().compareTo(stox2.getDate()) != 0) {
			return stox1.getDate().compareTo(stox2.getDate());
		} else {
			return stox1.getSymbol().compareTo(stox2.getSymbol());
		}
	}
}

	








