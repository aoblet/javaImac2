package fr.upem.data;

public interface MediaBuyableFilter{
	/** Returns true if the mediaBuyable is accepted by the filter */
	public boolean accept(MediaBuyable mb);
}