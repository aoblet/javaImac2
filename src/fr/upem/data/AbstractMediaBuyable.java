package fr.upem.data;


public abstract class AbstractMediaBuyable implements MediaBuyable{
	static class Subpart{
		// static intern class cannot access to extern member class, intern class can
		
		private String m_title;
		
		public Subpart(String title){
			m_title = title;
		}
		
		public String title(){
			return m_title;
		}
	}
	
	final protected String m_title;
	protected double m_price;
	protected double m_tva;
	protected LinkedList<Subpart> m_subparts;

	
	public AbstractMediaBuyable(String title, double price){
		this(title, price, 19.6);
	}
	
	public AbstractMediaBuyable(String title, double price, double tva){
		m_title = title;
		m_price = price;
		m_tva = tva;
	}
	
	public void addSubpart(String title){
		m_subparts.add(new Subpart(title));
	}
	
	public LinkedList<Subpart> getSubParts(){
		return m_subparts;
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
