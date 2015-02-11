package fr.upem.library;

public class Comic extends Book{
	protected String m_drawer;
	protected boolean m_isColored;
	
	public Comic(String title, String author, String isbn, double price, String drawer, boolean isColored){
		super(title, author, isbn, price);
		m_drawer = drawer;
		m_isColored = isColored;
	}
	
	public Comic(String title, String conceptor, String isbn, double price, boolean isColored){
		this(title, conceptor, isbn, price, conceptor, isColored);
	}
	
	public Comic(String title, String author, double price, String drawer, boolean isColored){
		super(title, author, price); //auto isbn: impossible to call this(..)
		m_isColored = isColored;
		m_drawer = drawer;
	}
	
	public Comic(String title, String conceptor, double price, boolean isColored){
		this(title, conceptor, price, conceptor, isColored);
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder(super.toString());
		res.append("Drawer:\t").append(m_drawer);
		res.append("\nIs colored: ").append(m_isColored);
		res.append("\n");
		return res.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Comic){
			Comic tmp = (Comic)o;
			return super.equals(tmp) && m_drawer.equals(tmp.m_drawer) && m_isColored == tmp.m_isColored;
		}
		return false;
	}
}
