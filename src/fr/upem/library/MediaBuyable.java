package fr.upem.library;

public interface MediaBuyable {
	public String title();
	public double price();
	public void setPrice(double p);

	public default double taxIncludedPrice(double taxRate){
		return this.price() * (1+(taxRate/100));
	}
}
