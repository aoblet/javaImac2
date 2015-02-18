package fr.upem.library;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class BookIntegral extends LinkedList<Book> implements MediaBuyable {
	protected String m_nameSerie;
	protected String m_author;
	
	public BookIntegral(String author, String nameSerie){
		super();
		m_author = author;
		m_nameSerie = nameSerie;
	}
	@Override
	public String title() {
		return m_nameSerie;
	}

	@Override
	public double price() {
		double res = 0;
		for(Book b: this)
			res += b.price();

		return res / this.size();
	}

	@Override
	public double taxIncludedPrice(double taxRate) {
		return this.price() * (1+(taxRate/100));
	}
}
