package fr.upem.data;
import java.util.Iterator;

import fr.upem.library.*;

public class LinkedList implements Iterable<Link>{
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
	
	public Link first(){
		return m_firstLink;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		
		Link tmp = m_firstLink;
		for(int i=0; i<m_size; ++i){
			if(tmp != null){
				if(tmp.data() != null)
					res.append(tmp.data().toString()).append("\n");
				tmp = tmp.next();
			}
		}
		return res.toString();
	}
	
	public MediaBuyable get(int i) throws IndexOutOfBoundsException{
		if(i <0 || i>m_size)
			throw new IndexOutOfBoundsException();
		
		Link tmp = m_firstLink;
		for(int j=1; j<=i;++j)
			tmp = tmp.next();
		return tmp != null ? tmp.data() : null;
	}
	
	@Override
	public Iterator<Link> iterator() {
		Iterator<Link> it = new Iterator<Link>() {
			
			private Link m_tmp = new Link(null, LinkedList.this.m_firstLink);
			
			@Override
			public boolean hasNext() {
				return !(m_tmp.next() == null || m_tmp.next().data() == null);
			}

			@Override
			public Link next() {
				if(m_tmp != null && m_tmp.next() != null)
					return (m_tmp = m_tmp.next());
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
			}
		};
		return it;
	}
	
	public Link pop(){
		if(m_firstLink != null){
			Link tmp = m_firstLink;
			m_firstLink = m_firstLink.next();
			m_size--;
			return tmp;
		}
		return null;
	}
	
	
	public void removeAt(int i) throws IndexOutOfBoundsException{
		if(i < 0 || i>m_size)
			throw new IndexOutOfBoundsException();
		
		Link tmp = null;
		for(int j=0; j<i; ++j)
			tmp = tmp.next();
		
		if(tmp != null){
			tmp.m_next = tmp.m_next.m_next;
			--m_size;
		}
		else{
			if(m_firstLink != null)
				m_firstLink = m_firstLink.next();
		}
		
	}
	
	public void insert(int i) throws IndexOutOfBoundsException{
		if(i < 0 || i>m_size)
			throw new IndexOutOfBoundsException();
	}
	
	public void addSorted(MediaBuyable b){
		Link tmp = m_firstLink;
		
		for(Link l: this){
			if(l.data().title().compareTo(b.title()) == -1){
				break;
			}
			tmp = l;
		}
		
	}

	public static void main(String [] args){
		Book b1 = new Book("Mon test1", "Alexis Oblet", 25);
		Book b2 = new Book("Mon test2", "Alexis Oblet", 35);
		LinkedList list = new LinkedList();
		list.add(b1);
		list.add(b2);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		list.add(b1);
		
		for(Link b: list)
			System.out.println(b.data().price());
	}
}
