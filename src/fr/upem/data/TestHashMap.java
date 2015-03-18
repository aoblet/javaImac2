package fr.upem.data;

import java.util.HashMap;

import fr.upem.library.Book;

public class TestHashMap {
	
	public static void main(String[] args) {
		Book b = new Book("lol", "5");
		Book a = new Book("lol", "5");
		HashMap<Book, Integer> test = new HashMap<Book, Integer>();
		test.put(b,1);
		test.put(b,1);
		test.put(a,2);
		System.out.println(test.size());
	}
	
}
