package fr.upem.library;

public interface MediaBuyable {
	public String title();
	public double price();
	public double getTaxIncludedPrice(double taxRate);
}
