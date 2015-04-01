package fr.upem.library;

import java.util.LinkedList;

import fr.upem.data.AbstractMediaBuyable;

public class BookIntegral extends AbstractMediaBuyable{
	protected String m_nameSerie;
	protected LinkedList<Book> m_list;
	
	public BookIntegral(String author, String nameSerie, Version version){
		super(author, nameSerie, 0, version);
		m_nameSerie = nameSerie;
		m_list = new LinkedList<Book>();
	}
	
	public BookIntegral(String author, String nameSerie){
		super(author, nameSerie, 0);
		m_nameSerie = nameSerie;
		m_list = new LinkedList<Book>();
	}
	
	public void add(Book b){
		m_list.add(b);
		m_price = this.price();
	}
	
	public void removeAt(int i) throws IndexOutOfBoundsException{
		m_list.remove(i);
		m_price = this.price();
	}

	@Override
	public double price() {
		if( m_list.size() == 0)
			return 0;
		
		double res = 0;
		for(Book b: m_list)
			res += b.price();

		return res / m_list.size();
	}
}
