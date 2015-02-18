package fr.upem.library;

public class AudioCD implements MediaBuyable {
	protected String m_title;
	protected String m_author;
	protected double m_price;
	protected int    m_nbSongs;

	public AudioCD(String title, double price){
		m_title = title;
		m_price = price;
	}
	
	@Override
	public String title() {
		return m_title;
	}

	@Override
	public double price() {
		return m_price;
	}

	@Override
	public double taxIncludedPrice(double taxRate) {
		return m_price * (1+(taxRate/100));
	}
}
