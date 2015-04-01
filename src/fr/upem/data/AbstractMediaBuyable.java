package fr.upem.data;

import fr.upem.library.Version;

public abstract class AbstractMediaBuyable implements MediaBuyable, Comparable<AbstractMediaBuyable>{
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
	final protected String m_author;
	protected double m_price;
	protected double m_tva;
	protected Version m_version;
	protected LinkedList<Subpart> m_subparts;

	
	public AbstractMediaBuyable(String author, String title, double price){
		this(author, title, price, 19.6, Version.Physic);
	}
	
	public AbstractMediaBuyable(String author, String title, double price, double tva){
		this(author, title, price, tva, Version.Physic);
	}
	
	public AbstractMediaBuyable(String author, String title, double price, Version version){
		this(author, title, price, 19.6, version);
	}
	
	public AbstractMediaBuyable(String author, String title, double price, double tva, Version version){
		m_author = author;
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
	public String author(){
		return m_author;
	}
	
	@Override
	public void setPrice(double p){
		m_price = p;
	}
	
	public Version version(){
		return m_version;		
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
	
	@Override
	public double getTaxIncludedPrice(){
		return this.price() * (1+(m_tva/100)); 
	}
	
	@Override
	public int hashCode(){
		return title().hashCode() + (int)(this.price()) + m_version.hashCode() * 30; 
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof AbstractMediaBuyable && this.hashCode() == o.hashCode();
	}
	
	@Override
	public int compareTo(AbstractMediaBuyable mb){
		int tmpCompare = mb.title().compareToIgnoreCase(m_title);
		if(tmpCompare == 0){
			tmpCompare = mb.author().compareToIgnoreCase(m_author);
			if(tmpCompare == 0)
				return mb.version().getSuffix().compareToIgnoreCase(m_version.getSuffix());
		}
		return tmpCompare;
	}
}
