package fr.upem.data;

public class Link<T> {
	final T m_data;
	Link<T> m_next;
	
	public Link(T data, Link<T> next){
		m_next = next;
		m_data = data;
	}
	
	public Link<T> next(){
		return m_next;
	}
	
	public T data(){
		return m_data;
	}
}
