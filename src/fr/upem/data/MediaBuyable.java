package fr.upem.data;

public interface MediaBuyable{
	public String title();
	public double price();
	public void setPrice(double p);
	public double getTaxIncludedPrice();
	public String author();
}
