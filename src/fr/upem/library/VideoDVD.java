package fr.upem.library;

import fr.upem.data.AbstractMediaBuyable;

public class VideoDVD extends AbstractMediaBuyable{
	protected String m_genre;
	protected int m_nbMinutes;

	public VideoDVD(String title, double price){
		super("", title, price, 20);
	}
}
