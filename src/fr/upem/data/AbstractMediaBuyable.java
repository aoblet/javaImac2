package fr.upem.data;

import fr.upem.library.Version;

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
	protected Version m_version;
	protected LinkedList<Subpart> m_subparts;

	
	public AbstractMediaBuyable(String title, double price){
		this(title, price, 19.6, Version.Physic);
	}
	
	public AbstractMediaBuyable(String title, double price, double tva){
		this(title, price, tva, Version.Physic);
	}
	
	public AbstractMediaBuyable(String title, double price, double tva, Version version){
		m_title = title;
		m_price = price;
		m_tva = tva;
		m_version = version;
		m_subparts = new LinkedList<AbstractMediaBuyable.Subpart>();
	}
	
	public void addSubpart(String title){
		m_subparts.add(new Subpart(title));
	}
	
	public LinkedList<Subpart> subParts(){
		return m_subparts;
	}
	
	@Override
	public String title(){
		return m_version.getSuffix()+" "+ m_title;
	}
	
	@Override
	public double price(){
		return m_version.modifyPrice(m_price);
	}
	
	@Override
	public void setPrice(double p){
		m_price = p;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Title: ").append(this.title()).append("\n");
		res.append("Price: ").append(this.price()).append("\n");
		res.append(this.subPartsToString());
		return res.toString();
	}
	
	public String subPartsToString(){
		StringBuilder res = new StringBuilder();
		for(Link<Subpart> s: m_subparts)
			res.append(s.data().title()).append("\n");
		return res.toString();
	}
	public double getTaxIncludedPrice(){
		return this.price() * (1+(m_tva/100)); 
	}
}































