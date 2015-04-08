package fr.upem.library;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;

import fr.upem.data.Link;
import fr.upem.data.AbstractMediaBuyable;
import fr.upem.data.LinkedList;
import fr.upem.data.MediaBuyableFilter;

public class Library implements Externalizable {
	ArrayList<AbstractMediaBuyable> m_books;
	String m_name;
	
	public Library(String name) throws IllegalArgumentException {
		m_books = new ArrayList<AbstractMediaBuyable>();
		m_name  = name;
	}
	
	public int size(){
		return m_books.size();
	}
	
	public boolean add(AbstractMediaBuyable b){
		return m_books.add(b);
	}
	
	
	public boolean contains(Book b){
		return m_books.contains(b);
	}
	
	public AbstractMediaBuyable remove(int i){
		try{
			return m_books.remove(i);			
		}
		catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override //erase default toString method
	public String toString() {
		StringBuilder res = new StringBuilder("My library");
		res.append("\nName: ").append(m_name+"\n");
		res.append("\nCurrent size: ").append(this.size()+"\n");
		
		for(AbstractMediaBuyable m: m_books)
			res.append(m.toString()).append("\n");
		return res.toString();
	}
	
	public void displayBooks(){
		System.out.println("My library:" + m_name + ", compact display");
		for(AbstractMediaBuyable m: m_books)
			System.out.println(m.title());
	}
	
	public LinkedList<Comic> getBD(){
		LinkedList<Comic> res =  new LinkedList<Comic>();
		
		for(AbstractMediaBuyable m: m_books){
			if(m instanceof Comic)
				res.add((Comic)m);
		}
		return res;
	}
	
	public LinkedList<AbstractMediaBuyable> search(String pattern){
		LinkedList<AbstractMediaBuyable> res = new LinkedList<AbstractMediaBuyable>();
		for(AbstractMediaBuyable m : m_books){
			if(m.title().contains(pattern))
				res.add(m);
		}
		return res;
	}
	
	public void printResultatSearch(String pattern){
		System.out.println("Search: "+pattern);
		for(Link<AbstractMediaBuyable> b: this.search(pattern))
			System.out.println(b.data().toString());
	}
	
	public double averagePrice(){
		double res=0;
		for(AbstractMediaBuyable m: m_books)
			res += m.price();
		
		return res / this.size();
	}
	
	public LinkedList<AbstractMediaBuyable> getElements (MediaBuyableFilter filter){
		LinkedList<AbstractMediaBuyable> res = new LinkedList<AbstractMediaBuyable>();
		for(AbstractMediaBuyable m : m_books){
			if(filter.accept(m))
				res.add(m);
		}
		return res;
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
		
		Library lib = new Library("Chez Austine");
		lib.add(b1);
		lib.add(b2);
		lib.add(b3);
		lib.add(b4);
		lib.add(b5);
		lib.add(b6);
		lib.add(b8);
		lib.add(b7);
		lib.add(c1);
		lib.add(bi1);
		
//		System.out.println(lib);
//		System.out.println(lib.contains(b7));
//		System.out.println(lib.contains(b8));
//		lib.add(b8);
//
//		
//		lib.displayBooks();
//		System.out.println("\nGetBD...");
//		for(Link<Comic> b: lib.getBD()){
//			System.out.println(b.data().title());
//			System.out.println("********************************************");
//		}
//		
//		System.out.println("Average price:" + lib.averagePrice());
//		lib.printResultatSearch("Le");
		
		LinkedList<AbstractMediaBuyable> search = lib.getElements(new MediaBuyableFilter() {
			@Override
			public boolean accept(AbstractMediaBuyable mb) {
				return mb.price()> 50;
			}
		});
		
		System.out.println(search);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
