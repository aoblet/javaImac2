package fr.upem.data;
import fr.upem.library.MediaBuyable;

public class Link {
	final MediaBuyable m_data;
	Link m_next;
	
	public Link(MediaBuyable data, Link next){
		m_next = next;
		m_data = data;
	}
	
	public Link next(){
		return m_next;
	}
	
	public MediaBuyable data(){
		return m_data;
	}
}
