package fr.upem.library;

public class Book implements MediaBuyable{
	final protected String m_title;
	final protected String m_author;
	final protected String m_isbn;
	protected double m_price;
	
	protected static int isbnCounter;
	
	public Book(String title, String author, String isbn, double price){
		this.m_title = title;
		this.m_author = author;
		this.m_isbn = isbn;
		this.m_price = price;
		
		//construct: new Book(.., .., ..,..);
	}
	
	//cannot call default constructor: because default provided by java is no longer available 
	public Book(String title, String author, String isbn){
		this(title, author, isbn, 0);
	}
	
	public Book(String title, String author, double price){
		this(title, author, Book.generateIsbn(), price);
	}
	
	public Book(String title, String isbn){
		this(title, "anonymous", isbn, 0);
	}
	
	protected static String generateIsbn(){
		return "auto-"+Book.isbnCounter++;
	}

	public String author() {
		return m_author;
	}

	public String isbn() {
		return m_isbn;
	}

	public void setPrice(double m_price) {
		this.m_price = m_price;
	}

	public boolean equals(Object o){
		if(o instanceof Book){
			Book tmp = (Book) o;
			return m_author.equals(tmp.m_author) && m_title.equals(tmp.m_title) && m_isbn.equals(tmp.m_isbn) && m_price == tmp.m_price;
		}
		return false;
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Title:\t").append(m_title);
		res.append("\nAuthor:\t").append(m_author);
		res.append("\nISBN:\t").append(m_isbn);
		res.append("\nPrice:\t").append(m_price);
		res.append("\n");
		return res.toString();
	}
	
	@Override
	public String title() {
		return m_title;
	}

	@Override
	public double price() {
		return m_price;
	}

	@Override
	public double getTaxIncludedPrice(double taxRate) {
		return m_price * (1+ (taxRate/100));
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		--Book.isbnCounter;
	}
	
	public static void main(String[] args) {
		Book b1 = new Book("L'écume des jours", "Boris Vian", "AEZD01", 9.99);
		Book b2 = new Book("L'écume des jours", "Boris Vian", "AEZD01", 10.99);
		Book b3 = b1;
		System.out.println(b1);
		System.out.println(b1.equals(b2)); 
		System.out.println(b1.equals(b3)); 
		
		Book b5 = new Book("Mon test","aleex",50);
		System.out.println(b5);
	}
}
