package fr.upem.library;

import java.util.LinkedList;

import fr.upem.data.AbstractMediaBuyable;

public class BookIntegral extends AbstractMediaBuyable{
	protected String m_nameSerie;
	protected String m_author;
	protected LinkedList<Book> m_list;
	
	public BookIntegral(String author, String nameSerie){
		super(nameSerie, 0);
		m_author = author;
		m_nameSerie = nameSerie;
	}
	
	public void add(Book b){
		m_list.add(b);
		m_price = this.price();
	}
	
	public void removeAt(int i){
		m_list.remove(i);
		m_price = this.price();
	}

	@Override
	public double price() {
		double res = 0;
		for(Book b: m_list)
			res += b.price();

		return res / m_list.size();
	}
}
