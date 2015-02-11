package fr.upem.library;

import javax.naming.SizeLimitExceededException;
import java.util.LinkedList;


public class Library {
	int m_maxCapacity;
	int m_currentIndex;
	MediaBuyable m_books[];
	
	public Library(int maxBooks) throws IllegalArgumentException {
		if(maxBooks < 0)
			throw new IllegalArgumentException("Bad argument in constructor");
		
		m_books = new Book[(m_maxCapacity=maxBooks)];
		m_currentIndex = 0;
	}
	
	public int capacity(){
		return m_maxCapacity;
	}
	
	public void add(Book b) throws SizeLimitExceededException{
		if(m_currentIndex > m_maxCapacity)
			throw new SizeLimitExceededException("Size of library exceeded for book:\n"+b.toString());
		m_books[m_currentIndex++] = b;
	}
	
	public int size(){
		return m_currentIndex;
	}
	
	public boolean contains(Book b){
		for(int i=0; i<m_currentIndex; ++i){
			if(m_books[i].equals(b))
				return true;
		}
		return false;
	}
	
	public void pop(int index) throws IndexOutOfBoundsException{
		if(index <-1 || index > m_currentIndex)
			throw new IndexOutOfBoundsException();
		
		index = index==-1? m_currentIndex : index;
		//TODO:move list splitted
	}
	
	@Override //erase default toString method
	public String toString() {
		StringBuilder res = new StringBuilder("My library");
		res.append("Capacity: ").append(m_maxCapacity);
		res.append("\nCurrent size: ").append(m_currentIndex+"\n");
		
		for(int i=0; i<m_currentIndex; ++i)
			res.append(m_books[i].toString()).append("\n");
		return res.toString();
	}
	
	public void displayBooks(){
		System.out.println("My library, compact display");
		for(int i=0; i<m_currentIndex; ++i)
			System.out.println(m_books[i].title());
	}
	
	public LinkedList<Comic> getBD(){
		LinkedList<Comic> res =  new LinkedList<Comic>();
		
		for(int i=0; i<m_currentIndex; ++i){
			if(m_books[i].getClass().getName() == "Comic")
				res.add((Comic)m_books[i]);
		}
		return res;
	}
	
	public LinkedList<MediaBuyable> search(String pattern){
		LinkedList<MediaBuyable> res = new LinkedList<MediaBuyable>();
		for(int i=0; i<m_currentIndex; ++i){
			if(m_books[i] != null && m_books[i].title().contains(pattern))
				res.add(m_books[i]);
		}
		return res;
	}
	
	public void printResultatSearch(String pattern){
		System.out.println("Search: "+pattern);
		for(MediaBuyable b: this.search(pattern))
			System.out.println(b);
	}
	
	public double averagePrice(){
		if(m_currentIndex == 0)
			return 0;
		
		double res=0;
		for(int i=0; i<m_currentIndex; ++i)
			res += m_books[i].price();
		
		return res / m_currentIndex;
	}
	
	public static void main(String[] args) {
		Book b1 = new Book("L'écume des jours", "Boris Vian", "AEZD01", 9.99);
		Book b2 = new Book("James Bond", "Lian Fleming", "AEZ161", 18.49);
		Book b3 = new Book("Les thanatonautes", "Bernard Werber", "AEZDEFD01", 5.99);
		Book b4 = new Book("Les Fourmis", "Bernard Werber", "DSCXZD01", 26.24);
		Book b5 = new Book("La pierre et le Sabre", "Mistoruri Hisoky", "DCSC821", 8.99);
		Book b6 = new Book("Le ciel et mon arbre", "Alain Jones", "SCBRG", 14.99);
		Book b7 = new Book("Le Mosssad", "Patrick Smith", "SSCCCV87", 50.99);
		Book b8 = new Book("Le hacking", "Linus Torvald", "SCCCCS", 220.99);
		
		Library lib = new Library(10);
		try{
			lib.add(b1);
			lib.add(b2);
			lib.add(b3);
			lib.add(b4);
			lib.add(b5);
			lib.add(b6);
			lib.add(b7);

			System.out.println(lib);
			System.out.println(lib.contains(b7));
			System.out.println(lib.contains(b8));
			lib.add(b8);
		}
		catch(SizeLimitExceededException e){
			e.printStackTrace();
			e.getExplanation();
		}
		
		lib.displayBooks();
		System.out.println("\nGetBD...");
		for(Book b: lib.getBD())
			System.out.println(b.title());
		
		System.out.println("Average price:" + lib.averagePrice());
		
		lib.printResultatSearch("Le");
	}
}
