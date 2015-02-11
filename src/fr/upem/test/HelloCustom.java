package fr.upem.test;

import java.util.Scanner;

public class HelloCustom {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean success = false;
		
		while(!success){
			System.out.println("Entrez votre nom et prénom:");
			try{
				System.out.println("Bonjour "+ scanner.next()+" " + scanner.next());
				success = true;
			}
			catch(Exception e){
				success = false;
			}	
		}
		scanner.close();
	}
}
