package fr.upem.library;

public class VideoDVD implements MediaBuyable{
	protected String m_title;
	protected double m_price;

	public VideoDVD(String title, double price){
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
	public double getTaxIncludedPrice(double taxRate) {
		return m_price * (1+(taxRate/100));
	}

}
