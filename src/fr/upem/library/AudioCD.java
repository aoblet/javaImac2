package fr.upem.library;

import fr.upem.data.AbstractMediaBuyable;

public class AudioCD extends AbstractMediaBuyable {
	protected int    m_nbSongs;

	public AudioCD(String title, double price){
		super(title, price, 20);
	}
}
