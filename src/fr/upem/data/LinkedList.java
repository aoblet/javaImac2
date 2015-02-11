package fr.upem.data;
import fr.upem.library.*;

public class LinkedList {
	int m_size;
	Link m_firstLink;
	
	public LinkedList(){
		m_size = 0;
		m_firstLink = null;
	}
	
	public void add(MediaBuyable m){
		Link tmp = new Link(m, m_firstLink);
		m_firstLink = tmp;
		++m_size;
	}
	
	public int size(){
		return m_size;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		
		Link tmp = m_firstLink;
		for(int i=0; i<m_size; ++i){
			if(tmp != null && tmp.data() != null)
				res.append(tmp.data().toString()).append("\n");
			tmp = tmp.next();
		}
		return res.toString();
	}
	
	public MediaBuyable get(int i) throws IndexOutOfBoundsException{
		if(i <0 || i>m_size)
			throw new IndexOutOfBoundsException();
		
		Link tmp = m_firstLink;
		for(int j=1; j<=i;++j)
			tmp = tmp.next();
		return tmp != null ? tmp.data() : null; //
	}
	
	public static void main(String [] args){
		Book b1 = new Book("Mon test1", "Alexis Oblet", 25);
		Book b2 = new Book("Mon test2", "Alexis Oblet", 25);
		LinkedList list = new LinkedList();
		list.add(b1);
		list.add(b2);
		list.add(b1);
		
		System.out.println(list);
		System.out.println(list);
		System.out.println(list.size());
	}
}
