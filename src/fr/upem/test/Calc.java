package fr.upem.test;

import java.util.Scanner; //get from standard input

public class Calc {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try{
			System.out.println("Addition de deux entiers:\nSaisir deux entiers");
			int value  = scanner.nextInt();
			int value2 = scanner.nextInt();
			System.out.println(value+"+"+value2+"="+(value+value2));
			System.out.println(value+"-"+value2+"="+(value-value2));
			System.out.println(value+"%"+value2+"="+(value%value2));
			if(value2 != 0)
				System.out.println(value+"/"+value2+"="+((double)(value)/value2));
			else
				System.out.println("Division par zéro impossible");
		}
		catch(Exception e){
			System.out.println("Problem occurs "+ e.getMessage());
		}
		scanner.close();
	}
}
