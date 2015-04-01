package fr.upem.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

import fr.upem.library.Book;
import fr.upem.library.BookIntegral;
import fr.upem.library.Comic;
import fr.upem.library.Version;
 	
public class ShoppingBasket<T extends AbstractMediaBuyable> {
	public TreeMap<T, Integer> m_data;
	public Set<String> m_authors;
	
	public ShoppingBasket(){
		m_data = new TreeMap<T, Integer>(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
		m_authors = new HashSet<String>();
		this.updateAuthors();
	}
	
	public Set<String> getAuthors(){
		return Collections.unmodifiableSet(m_authors);
	}
	
	public TreeMap<T, Integer> getData(){
		return m_data;
	}
	
	public void updateAuthors(){
		m_authors.clear();
		for(Map.Entry<T, Integer> entry: m_data.entrySet()){
			m_authors.add(entry.getKey().author());
		}
	}
	
	public int add(T element){
		Integer newSize = m_data.get(element);
		newSize = (newSize == null) ? 1 : ++newSize;
		
		m_data.put(element, newSize);
		this.updateAuthors();
		return newSize;
	}
	
	public void add(T element, int nb){
		m_data.put(element, nb);
	}
	
	public int remove(T element) throws NoSuchElementException{
		Integer compareTo = m_data.get(element);
		
		if(compareTo == null || compareTo == 0)
			throw new NoSuchElementException();
		
		if(compareTo > 0){
			m_data.put(element, compareTo--);
			if(compareTo == 0){
				m_data.remove(element);
				
			}
		}
		return compareTo;
	}
	
	public void clear(){
		m_data.clear();
	}
	
	public int getSampleNumber(){
		int size = 0;
		for(Map.Entry<T, Integer> entry: m_data.entrySet()){
			size += entry.getValue();
		}
		return size;
	}
	
	public int size(){
		return m_data.size();
	}
	
	public double getGlobalPrice(){
		int size = this.getSampleNumber();
		if(size == 0)
			return 0;
		
		double res = 0;
		for(Map.Entry<T, Integer> entry: m_data.entrySet()){
			res += (entry.getKey().getTaxIncludedPrice() * entry.getValue());
		}
		return res;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		for(Map.Entry<T, Integer> entry: m_data.entrySet()){
			str.append(entry.getKey().title()).append(" : ").append(entry.getValue()).append("\n\n");
		}
		return str.toString();
	}
	
	public static void main(String[] args) {
		Book b1 = new Book("L'écume des jours", "Boris Vian", "AEZD01", 9.99 );
		Book b2 = new Book("James Bond", "Lian Fleming", "AEZ161", 18.49);
		Book b3 = new Book("Les thanatonautes", "Bernard Werber", "AEZDEFD01", 5.99);
		Book b4 = new Book("Les Fourmis", "Bernard Werber", "DSCXZD01", 26.24);
		Book b5 = new Book("La pierre et le Sabre", "Mistoruri Hisoky", "DCSC821", 8.99);
		Book b6 = new Book("Le ciel et mon arbre", "Alain Jones", "SCBRG", 14.99);
		Book b7 = new Book("Le Mossad", "Patrick Smith", "SSCCCV87", 50.99);
		Book b8 = new Book("Le hacking", "Linus Torvald", "SCCCCS", 220.99);
		Comic c1 = new Comic("Histoire du futur", "Robert Heinelen", 6, true);
		BookIntegral bi1 = new BookIntegral("Philip K.Dick", "Go home martians", Version.Physic);
		
		ShoppingBasket<AbstractMediaBuyable> shop = new ShoppingBasket<AbstractMediaBuyable>();
		shop.add(b2);
		shop.add(b1);
		shop.add(b3);
		shop.add(b4);
		shop.add(b5);
		shop.add(b6);
		shop.add(b8);
		shop.add(b7);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(c1);
		shop.add(bi1);
		
		System.out.println(shop.getSampleNumber());
		System.out.println(shop.getGlobalPrice());
		System.out.println(shop.size());
		
		
		System.out.println(shop);
	}
}




















