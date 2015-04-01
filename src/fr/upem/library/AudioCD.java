package fr.upem.library;

import fr.upem.data.AbstractMediaBuyable;

public class AudioCD extends AbstractMediaBuyable {
	protected int m_nbSongs;

	public AudioCD(String title, double price){
		super("", title, price, 20);
	}
	
	public static void main(String[] args) {
		AudioCD cd = new AudioCD("Nirvana à la plage", 15);
		cd.addSubpart("1: Smells like");
		cd.addSubpart("2: Bloom");
		cd.addSubpart("3: Insecticide");
		cd.addSubpart("4: Lithium");
		System.out.println(cd);
	}
}
