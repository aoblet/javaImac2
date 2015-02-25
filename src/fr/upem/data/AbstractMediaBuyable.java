package fr.upem.data;

import fr.upem.library.MediaBuyable;

public abstract class AbstractMediaBuyable implements MediaBuyable{
	final protected String m_title;
	protected double m_price;
	protected double m_tva;
	
	public AbstractMediaBuyable(String title, double price){
		this(title, price, 19.6);
	}
	
	public AbstractMediaBuyable(String title, double price, double tva){
		m_title = title;
		m_price = price;
		this.m_tva = tva;
	}
	
	@Override
	public String title(){
		return m_title;
	}
	
	@Override
	public double price(){
		return m_price;
	}
	
	@Override
	public void setPrice(double p){
		m_price = p;
	}
	
	public double getTaxIncludedPrice(){
		return this.price() * (1+(m_tva/100)); 
	}
}
